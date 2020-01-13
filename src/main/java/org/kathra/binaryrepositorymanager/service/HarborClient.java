/* 
 * Copyright 2019 The Kathra Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *
 *    IRT SystemX (https://www.kathra.org/)    
 *
 */
package org.kathra.binaryrepositorymanager.service;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.kathra.core.model.BinaryRepository;
import org.kathra.core.model.Membership;
import org.kathra.harbor.client.ApiClient;
import org.kathra.harbor.client.ApiException;
import org.kathra.harbor.client.ApiResponse;
import org.kathra.harbor.client.Configuration;
import org.kathra.harbor.client.api.ProductsApi;
import org.kathra.harbor.client.auth.HttpBasicAuth;
import org.kathra.harbor.client.model.*;
import org.kathra.utils.KathraException;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import static org.kathra.core.model.Membership.RoleEnum;
import static org.kathra.core.model.Membership.RoleEnum.*;

public class HarborClient {

  public String server;
  public String password;
  public String username;
  private ApiClient defaultClient;
  private ProductsApi productsApi = new ProductsApi();

  public HarborClient(String server, String username, String password) {
    this.username = username;
    this.password = password;
    this.server = server;

    defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath(server+"/api");
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername(this.username);
    basicAuth.setPassword(this.password);
    productsApi.setApiClient(defaultClient);
  }

  public BinaryRepository addRepository(BinaryRepository binaryRepository) throws Exception {
    if (binaryRepository.getGroup() == null || StringUtils.isEmpty(binaryRepository.getGroup().getName())) {
      throw new KathraException("Group's name undefined", null, KathraException.ErrorCode.BAD_REQUEST);
    }
    // Use group name as project's name
    String projectName = binaryRepository.getGroup().getName().toLowerCase();
    Optional<Project> existing = getProjectFromName(projectName);
    if (existing.isEmpty()) {
      ProjectReq project = new ProjectReq();
      project.setProjectName(projectName);
      productsApi.projectsPostWithHttpInfo(project);
    }
    return map(getProjectFromName(projectName).orElseThrow(() -> new IllegalStateException("Project should be created")), BinaryRepository.TypeEnum.HELM.equals(binaryRepository.getType()));
  }

  private Optional<Project> getProjectFromName(String name) throws ApiException {
      return productsApi.projectsGet(null, null, null, 1, 10).stream().filter(p -> p.getName().equals(name)).findFirst();
  }

  public List<BinaryRepository> getRepositories() throws ApiException {
    List<Project> projects = productsApi.projectsGet(null, null, null, 1, 1000);
    List<BinaryRepository> registries = projects.parallelStream().map(p -> map(p, false)).collect(Collectors.toList());
    List<BinaryRepository> chartsMuseum = projects.parallelStream().map(p -> map(p, true)).collect(Collectors.toList());
    List allBinaryRepositories = new ArrayList();
    allBinaryRepositories.addAll(registries);
    allBinaryRepositories.addAll(chartsMuseum);
    return allBinaryRepositories;
  }

  private BinaryRepository map(Project project, boolean isChartMuseum) {
    String url = server.replaceAll("http://", "").replaceAll("https://","")+"/"+project.getName();
    if (isChartMuseum) {
      url = "https://"+server.replaceAll("http://", "").replaceAll("https://","")+"/chartrepo/"+project.getName();
    }
    return new BinaryRepository() .url(url)
                                  .type(isChartMuseum ? BinaryRepository.TypeEnum.DOCKER_IMAGE :BinaryRepository.TypeEnum.HELM)
                                  .providerId(project.getProjectId().toString())
                                  .provider("harbor");
  }

  public void addRepositoryMembership(String providerId, Membership binaryRepositoryMembership) throws ApiException, KathraException {

    List<ProjectMemberEntity> membersExistings = productsApi.projectsProjectIdMembersGet(Long.valueOf(providerId), null);
    if (binaryRepositoryMembership.getMemberType() == null) {
      throw new KathraException("membertype undefined", null, KathraException.ErrorCode.BAD_REQUEST);
    }
    if (binaryRepositoryMembership.getRole() == null) {
      throw new KathraException("role undefined", null, KathraException.ErrorCode.BAD_REQUEST);
    }

    String memberName = binaryRepositoryMembership.getMemberName();
    Optional<ProjectMemberEntity> membershipFound;
    ProjectMember projectMember;
    switch (binaryRepositoryMembership.getMemberType()) {
      case USER:
        Optional<User> userFound = getUsers().parallelStream().filter(u -> u.getUsername().equals(memberName)).findFirst();
        if (userFound.isEmpty())
          return;

        membershipFound = membersExistings.parallelStream().filter(p -> p.getEntityType().equals("u") && p.getEntityId().equals(userFound.get().getUserId())).findFirst();
        if (membershipFound.isPresent() && membershipFound.get().getRoleId().equals(getMembershipRoleFromRoleEnum(binaryRepositoryMembership.getRole())))
            throw new KathraException("User '"+memberName+"' not found in Harbor", null, KathraException.ErrorCode.NOT_FOUND);

        if (membershipFound.isPresent())
          productsApi.projectsProjectIdMembersMidDelete(Long.valueOf(providerId), Long.valueOf(membershipFound.get().getId()));

        projectMember = new ProjectMember().memberUser(new UserEntity().userId(userFound.get().getUserId())).roleId(getMembershipRoleFromRoleEnum(binaryRepositoryMembership.getRole()));

        break;
      case GROUP:
        Optional<UserGroup> groupFound = getGroupsHarbor().parallelStream().filter(u -> u.getGroupName().equals(memberName)).findFirst();
        if (groupFound.isEmpty())
          throw new KathraException("Group '"+memberName+"' not found in Harbor", null, KathraException.ErrorCode.NOT_FOUND);

        membershipFound = membersExistings.parallelStream().filter(p -> p.getEntityType().equals("g") && p.getEntityId().equals(groupFound.get().getGroupName())).findFirst();
        if (membershipFound.isPresent() && membershipFound.get().getRoleId().equals(getMembershipRoleFromRoleEnum(binaryRepositoryMembership.getRole())))
          return;
        if (membershipFound.isPresent())
          productsApi.projectsProjectIdMembersMidDelete(Long.valueOf(providerId), Long.valueOf(membershipFound.get().getId()));

        projectMember = new ProjectMember().memberGroup(new UserGroup().id(groupFound.get().getId())).roleId(getMembershipRoleFromRoleEnum(binaryRepositoryMembership.getRole()));
      break;
      default:
        throw new IllegalStateException("Unexpected value: " + binaryRepositoryMembership.getMemberType());
    }
    productsApi.projectsProjectIdMembersPost(Long.valueOf(providerId), projectMember);
  }

  private Collection<UserGroup> getGroupsHarbor() throws ApiException {
    return productsApi.usergroupsGet();
  }

  public List<Membership> getMemberships(String providerId) throws ApiException {
    List<ProjectMemberEntity> membersExistings = productsApi.projectsProjectIdMembersGet(Long.valueOf(providerId), null);
    return membersExistings.stream().map(m -> map(m)).collect(Collectors.toList());
  }

  private Membership map(ProjectMemberEntity e) {
    return new Membership().memberName(e.getEntityName()).role(getRoleEnumFromRoleId(e.getRoleName())).path(e.getProjectId()+"");
  }

    private int getMembershipRoleFromRoleEnum(RoleEnum role) {
        switch(role) {
            case MANAGER:
                return 1;
            case GUEST:
                return 3;
            case CONTRIBUTOR:
                return 2;
            default:
                throw new NotImplementedException("Role "+role.getValue()+" not managed");
        }
    }

  private RoleEnum getRoleEnumFromRoleId(String roleName) {
    switch(roleName) {
      case "projectAdmin":
      case "master":
        return MANAGER;
      case "guest":
        return GUEST;
      case "developer":
        return CONTRIBUTOR;
      default:
        throw new NotImplementedException("Role "+roleName+" not managed");
    }
  }

  private List<User> getUsers() throws ApiException {
    return productsApi.usersGet(null,null,null, 1000);
  }

  public ProductsApi getProductsApi() {
    return productsApi;
  }

  public void setProductsApi(ProductsApi productsApi) {
    this.productsApi = productsApi;
  }

  public String generateKey(org.kathra.core.model.User userWidthDetails) throws ApiException {
    Optional<User> userFound = getUsers().parallelStream().filter(u -> u.getUsername().equals(userWidthDetails.getName())).findFirst();
    if (!userFound.isPresent())
      throw new IllegalStateException("User '"+userWidthDetails.getName()+"' not found in Harbor");
    ApiResponse<InlineResponse200> secret = this.productsApi.usersUserIdGenCliSecretPostWithHttpInfo(userFound.get().getUserId());
    return secret.getData().getSecret();
  }

}
