package org.kathra.binaryrepositorymanager.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kathra.binaryrepositorymanager.Config;
import org.kathra.binaryrepositorymanager.model.Credential;
import org.kathra.binaryrepositorymanager.security.KeycloackSession;
import org.kathra.core.model.User;
import org.kathra.resourcemanager.client.GroupsClient;
import org.kathra.resourcemanager.client.UsersClient;
import org.kathra.utils.ApiException;
import org.kathra.utils.KathraSessionManager;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UserRepositoryActivateToken implements Processor {

    private final static Logger logger = Logger.getLogger(UserRepositoryActivateToken.class.getName());
    private final Map<String, Credential> userSecretCli = new ConcurrentHashMap<>();

    private final GroupsClient groupsClient;
    private final UsersClient usersClient;
    private final HarborClient harborClient;
    private final static String SCRIPT="activateToken.sh";
    private final String harborUrl;
    private final String keycloakHost;

    private static UserRepositoryActivateToken instance;

    public static UserRepositoryActivateToken getInstance() {
        if (instance == null) {
            Config config = new Config();
            KathraSessionManager sessionManager = new KeycloackSession(new User().name(config.getLoginKeycloak()).password(config.getPasswordKeycloak()));
            GroupsClient groupsClient = new GroupsClient(config.getResourceManager(), sessionManager);
            UsersClient usersClient = new UsersClient(config.getResourceManager(), sessionManager);
            HarborClient harborClient = new HarborClient(config.getHarborUrl(), config.getHarborUsername(), config.getHarborPassword());
            instance = new UserRepositoryActivateToken(groupsClient, usersClient, harborClient, config.getHarborUrl(), config.getKeycloakHost());
        }
        return instance;
    }

    public UserRepositoryActivateToken(GroupsClient groupsClient, UsersClient usersClient, HarborClient harborClient, String harborUrl, String keycloakHost) {
        this.groupsClient = groupsClient;
        this.usersClient = usersClient;
        this.harborClient = harborClient;
        this.keycloakHost = keycloakHost;
        this.harborUrl = harborUrl;
    }

    public void execute() throws ApiException, IOException {
        copyScriptIntoFs("/tmp/"+SCRIPT);
        logger.info("execute");
        groupsClient.getGroups()
                    .parallelStream()
                    .map(g -> g.getTechnicalUser())
                    .filter(Objects::nonNull)
                    .forEach(u -> execute(u));
    }

    public void execute(User user) {
        logger.info("execute for user "+ user.getId());
        try {
            User userWidthDetails = usersClient.getUser(user.getId());

            if (StringUtils.isEmpty(userWidthDetails.getPassword())) {
                throw new IllegalStateException("Not password defined for user:" + userWidthDetails.getName());
            } else if (!initConnexionHarbor(harborUrl, keycloakHost, userWidthDetails.getName(), userWidthDetails.getPassword())) {
                throw new IllegalStateException("Unable to connect to Harbor with OIDC");
            }

            String secretCli = userWidthDetails.getMetadata() != null ? (String) userWidthDetails.getMetadata().get("HARBOR_SECRET_CLI") : null;
            if (StringUtils.isEmpty(secretCli)) {
                logger.info("HARBOR_SECRET_CLI undefined for user:"+ userWidthDetails.getName());
                secretCli = harborClient.generateKey(userWidthDetails);
                usersClient.updateUserAttributes(user.getId(), new User().putMetadataItem("HARBOR_SECRET_CLI", secretCli));
            }

            if (userSecretCli.containsKey(user.getId())) {
                userSecretCli.replace(userWidthDetails.getId(), new Credential().username(userWidthDetails.getName()).password(secretCli));
            } else {
                userSecretCli.put(userWidthDetails.getId(), new Credential().username(userWidthDetails.getName()).password(secretCli));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyScriptIntoFs(String path) throws IOException {
        File file = new File(path);
        if (file.isFile())
            return;
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(SCRIPT);
        FileUtils.copyURLToFile(resource, file);
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        Files.setPosixFilePermissions(file.toPath(), perms);
    }

    private boolean initConnexionHarbor(String harborUrl, String keycloakHost, String username, String password) throws IOException {
        String[] cmd = new String[]{"/bin/bash", "/tmp/"+SCRIPT, harborUrl, keycloakHost, username, password};
        logger.info("initConnexionHarbor:"+String.join(",", cmd));
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(cmd);


        try (InputStream stdIn = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdIn);
            BufferedReader br = new BufferedReader(isr)) {

            int exitVal = 0;
            try {
                exitVal = proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String line;
            while ((line = br.readLine()) != null)
                logger.info(line);

            logger.info("initConnexionHarbor:" + String.join(",", cmd) + " exit value:" + exitVal);
            return exitVal == 0;
        }
    }


    @Override
    public void process(Exchange exchange) throws Exception {
        execute();
    }

    public Map<String, Credential> getUserSecretCli() {
        return userSecretCli;
    }
}
