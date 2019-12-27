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
package org.kathra.binaryrepositorymanager.controller;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

import org.kathra.binaryrepositorymanager.Config;
import org.kathra.binaryrepositorymanager.model.ContainersRepository;
import org.kathra.binaryrepositorymanager.model.ContainersRepositoryMetadata;
import org.kathra.binaryrepositorymanager.model.Credential;
import org.kathra.binaryrepositorymanager.service.BinaryrepositorymanagerService;
import org.kathra.core.model.BinaryRepository;
import org.kathra.core.model.Membership;
import org.kathra.utils.ApiException;
import org.kathra.utils.ApiResponse;
import org.kathra.utils.annotations.Eager;
import org.kathra.utils.serialization.GsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import com.mashape.unirest.http.JsonNode;

@Named("BinaryRepositoryManagerController")
@ApplicationScoped
@Eager
public class BinaryRepositoryManagerController implements BinaryrepositorymanagerService {
  
  private Config config;
  private String harborUrl;
  private HarborClient harborClient;

  public BinaryRepositoryManagerController() {
    config = new Config();
    this.harborUrl = config.getHarborUrl();
    this.harborClient = new HarborClient(config.getHarborUsername(), config.getHarborPassword());
  }
  public BinaryRepositoryManagerController(HarborClient client, String url) {
    this.harborClient = client;
    this.harborUrl = url;
  }
  
  /**
  * Add a containers repository
  * 
  * @param containersRepository Containers repository object to be created (required)
  * @return ContainersRepository
  * HttpCodes : 201 Created, 400 Bad request, 401 Unauthorized, 409 Conflict(Already existing), 500 Internal Server Error
  */
  public ContainersRepository addContainersRepository(ContainersRepository containersRepository) throws Exception {
    String endpointUrl = String.join("/", this.harborUrl, "projects");
    // Genericized "project_name" to "name" in the API, so we have to replace it here to match Harbor API 
    String jsonStringRepo = GsonUtils.toJson(containersRepository).replace("name", "project_name");
    
    ApiResponse<String> stringResponse = this.harborClient.addRepository(containersRepository.getName(), jsonStringRepo, endpointUrl);
    // Cast response to Repository
    String repoString = stringResponse.getData()
      .replace("project_id", "id")
      .replace("owner_id", "ownerId")
      .replace("repo_count", "repoCount")
      .replace("creation_time", "creationTime")
      .replace("update_time", "updateTime");

    if(stringResponse.getStatusCode() == 400){
      throw new ApiException(400, "Bad request");
    }
    else if(stringResponse.getStatusCode() == 401) {
      throw new ApiException(401, "Unauthorized");
    }
    else if(stringResponse.getStatusCode() == 409) {
      throw new ApiException(409, "Resource already exists", null, repoString);
    }
    else if(stringResponse.getStatusCode() == 500) {
      throw new ApiException(500, "Internal Server Error");
    }
    
    ContainersRepository repo = GsonUtils.gson.fromJson(repoString, ContainersRepository.class);
    return repo;
  }
  
  /**
  * Add a containers repository membership for a user or group
  * 
  * @param containersRepoId The id of the containers repository to retrieve (required)
  * @param containersRepositoryMembership Membership object to add to the containers repository (required)
  * @return Membership
  */
  public Membership addContainersRepositoryMembership(final String containersRepoId, final Membership containersRepositoryMembership) throws Exception {
    
    if(containersRepositoryMembership.getMemberType() == null ||
       containersRepositoryMembership.getRole() == null){
      
      throw new ApiException(400, "Bad request");
    }
    
    String projectsEndpoint = String.join("/", this.harborUrl, "projects");
    
    ApiResponse<JsonNode> projects = this.harborClient.getRepositories(projectsEndpoint);
    
    JSONArray projectsArray = projects.getData().getArray();
    JSONObject targetProject = null;
    JSONObject targetGroup = null;

    for(int i=0; i<projectsArray.length(); i++){
      if(String.valueOf(projectsArray.getJSONObject(i).get("name")).equals(containersRepoId)) {
        targetProject = projectsArray.getJSONObject(i);
        break;
      }
    };
    
    if(targetProject != null){
      
      /**
       * /!\ WARNING
       * Harbor behaves differently if the group to add to membership already exists
       * This implementation takes care of this behaviour, but WILL NOT WORK if the group does not exist.
       */
      if(containersRepositoryMembership.getMemberType().getValue().equals("group")){
        String groupsEndpoint = String.join("/", this.harborUrl, "usergroups");
        
        ApiResponse<JsonNode> groups = this.harborClient.getGroups(groupsEndpoint);

        JSONArray groupsArray = groups.getData().getArray();
        for(int i=0; i<groupsArray.length(); i++){
          if(String.valueOf(groupsArray.getJSONObject(i).get("group_name")).equals(containersRepositoryMembership.getMemberName())) {
            targetGroup = groupsArray.getJSONObject(i);
            break;
          }
        };
      }
      
      String targetType;
      String endpointUrl = String.join("/", this.harborUrl, "projects", targetProject.get("project_id").toString(), "members");
      HashMap<String, Object> target = new HashMap<String, Object>();

      /**
       * /!\ WARNING
       * Harbor behaves differently if the group to add to membership already exists
       * This implementation takes care of this behaviour , but WILL NOT WORK if the group does not exist.
       */
      if(targetGroup != null) {
        targetType = "id";
        target.put(targetType, targetGroup.getInt("id"));
      }
      else {
        targetType = "username";
        target.put(targetType, containersRepositoryMembership.getMemberName());
      }

      HashMap<String, Object> harborMembership = new HashMap<String, Object>();
      // Role: GUEST:0, CONTRIBUTOR:1, MANAGER:2
      harborMembership.put("role_id", containersRepositoryMembership.getRole().ordinal());
      harborMembership.put("member_" + containersRepositoryMembership.getMemberType(), target);
      String jsonObject = GsonUtils.toJson(harborMembership);
      
      ApiResponse<String> stringResponse = this.harborClient.addRepositoryMembership(jsonObject, endpointUrl);
      
      if(stringResponse.getStatusCode() == 401) {
        throw new ApiException(401, "Unauthorized");
      }
      else if(stringResponse.getStatusCode() == 409) {
        throw new ApiException(409, "Membership already exists", null, GsonUtils.toJson(containersRepositoryMembership));
      }
      else if(stringResponse.getStatusCode() == 500) {
        throw new ApiException(500, "Internal Server Error");
      }
      
      Membership member = GsonUtils.gson.fromJson(stringResponse.getData(), Membership.class);
      return member;
    }
    else {
      throw new ApiException(404, "Repository not found");
    }
  }
  
  /**
  * Retrieve a list of existing containers repositories for authenticated user
  * 
  * @return List<ContainersRepository>
  */
  public List<ContainersRepository> getContainersRepositories() throws Exception {
    String projectsEndpoint = String.join("/", this.harborUrl, "projects");
    
    ApiResponse<JsonNode> projects = this.harborClient.getRepositories(projectsEndpoint);
    
    List<ContainersRepository> repos = new ArrayList<ContainersRepository>();
    JSONArray projectsArray = projects.getData().getArray();
    for(int i=0; i<projectsArray.length(); i++){
      if(!projectsArray.getJSONObject(i).get("name").equals("library")){
        ContainersRepository repo = new ContainersRepository();
        ContainersRepositoryMetadata meta = new ContainersRepositoryMetadata();
        JSONObject currentRepo = projectsArray.getJSONObject(i);
        JSONObject repoMetadatas = currentRepo.getJSONObject("metadata");
        meta.setContentTrustEnabled(
          ifObjHasKeyGetValueOrDefault(repoMetadatas, "enable_content_trust", "false")
        );
        meta.setIsPublic(
          ifObjHasKeyGetValueOrDefault(repoMetadatas, "public", "false")
        );
        meta.setScanImagesOnPush(
          ifObjHasKeyGetValueOrDefault(repoMetadatas, "automatically_scan_images_on_push", "false")
        );
        meta.setVulnerableThreshold(ContainersRepositoryMetadata.VulnerableThresholdEnum.fromValue(
          ifObjHasKeyGetValueOrDefault(repoMetadatas, "prevent_vulnerable_images_from_running_severity", "HIGH"))
        );
        meta.setPreventVulnerableFromRunning(
          ifObjHasKeyGetValueOrDefault(repoMetadatas, "prevent_vulnerable_images_from_running", "false")
        );
      
        repo.setName(currentRepo.get("name").toString());
        repo.setMetadata(meta);

        repos.add(repo);
      }
    };

    return repos;
  }
  
  /**
  * Retrieve a specific containers repository
  * 
  * @param containersRepoId The id of the containers repository to retrieve (required)
  * @return ContainersRepository
  */
  public ContainersRepository getContainersRepository(String containersRepoId) throws Exception {
    //TODO: Implement this method
    throw new UnsupportedOperationException("No implementation could be found for the requested operation.");
  }
  
  /**
  * Retrieve a list of users and groups membership values for the specified containers repository
  * 
  * @param containersRepoId The id of the containers repository to retrieve (required)
  * @return List<Membership>
  */
  public List<Membership> getContainersRepositoryMembership(String containersRepoId) throws Exception {
    //TODO: Implement this method
    throw new UnsupportedOperationException("No implementation could be found for the requested operation.");
  }
  
  /**
  * Fully update a registered containers repository
  * 
  * @param containersRepoId The id of the containers repository to replace (required)
  * @param containersRepository Containers repository object to use to replace existing resource (required)
  * @return ContainersRepository
  */
  public ContainersRepository updateContainersRepository(String containersRepoId, ContainersRepository containersRepository) throws Exception {
    //TODO: Implement this method
    throw new UnsupportedOperationException("No implementation could be found for the requested operation.");
  }
  
  /**
  * Partially update a registered containers repository
  * 
  * @param containersRepoId The id of the containers repository to partially update (required)
  * @param containersRepository Containers repository object to use to patch existing resource (required)
  * @return ApiResponse
  */
  public ApiResponse updateContainersRepositoryAttributes(String containersRepoId, ContainersRepository containersRepository) throws Exception {
    //TODO: Implement this method
    throw new UnsupportedOperationException("No implementation could be found for the requested operation.");
  }
  
  private String ifObjHasKeyGetValueOrDefault(JSONObject obj, String key, String defaultValue){
    if(obj.has(key)) return obj.getString(key);
    else return defaultValue;
  }

  public void setHarborClient(HarborClient client){
    this.harborClient = client;
  }
  public void setHarborUrl(String url){
    this.harborUrl = url;
  }

  @Override
  public ApiResponse addBinaryRepository(BinaryRepository binaryRepository) throws Exception {
    return null;
  }

  @Override
  public ApiResponse addBinaryRepositoryMembership(String binaryRepoId, Membership binaryRepositoryMembership) throws Exception {
    return null;
  }

  @Override
  public Credential credentialsIdGet(String id) throws Exception {
    return null;
  }

  @Override
  public List<BinaryRepository> getBinaryRepositories() throws Exception {
    return null;
  }

  @Override
  public BinaryRepository getBinaryRepository(String binaryRepoId) throws Exception {
    return null;
  }

  @Override
  public List<Membership> getBinaryRepositoryMembership(String binaryRepoId) throws Exception {
    return null;
  }

  @Override
  public ApiResponse updateBinaryRepository(String binaryRepoId, BinaryRepository binaryRepository) throws Exception {
    return null;
  }

  @Override
  public ApiResponse updateBinaryRepositoryAttributes(String binaryRepoId, BinaryRepository binaryRepository) throws Exception {
    return null;
  }
}
