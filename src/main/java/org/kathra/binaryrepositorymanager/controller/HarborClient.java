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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.json.JSONArray;
import org.json.JSONObject;

import org.kathra.utils.ApiResponse;

public class HarborClient {

  public String password;
  public String username;

  public HarborClient(String username, String password) {
    this.username = username;
    this.password = password;
  }
  
  public ApiResponse<String> addRepository(String repoName, String jsonStringRepo, String endpointURL) throws Exception {
    HttpResponse<String> resp = Unirest.post(endpointURL)
      .basicAuth(this.username, this.password)
      .header("Accept", "application/json")
      .header("Content-type", "application/json")
      .body(jsonStringRepo)
      .asString();
    
    // Get created repo full object from Harbor & return it in the ApiReponse body
    JSONArray jsonRepos = this.getRepositories(endpointURL).getData().getArray();
    JSONObject createdRepo = null;
    for(int i=0; i<jsonRepos.length(); i++){
      if(jsonRepos.getJSONObject(i).getString("name").equals(repoName)) {
        createdRepo = jsonRepos.getJSONObject(i);
      }
    }
    
    return new ApiResponse<String>(resp.getStatus(), null, createdRepo.toString());
  }

  public ApiResponse<String> addRepositoryMembership(String jsonStringRepoMembership, String endpointURL) throws Exception {
    HttpResponse<String> resp = Unirest.post(endpointURL)
      .basicAuth(this.username, this.password)
      .header("Accept", "application/json")
      .header("Content-type", "application/json")
      .body(jsonStringRepoMembership)
      .asString();
    return new ApiResponse<String>(resp.getStatus(), null, resp.getBody());
  }
  
  public ApiResponse<JsonNode> getRepositories(String endpointURL) throws Exception {
    HttpResponse<JsonNode> resp = Unirest.get(endpointURL)
      .basicAuth(this.username, this.password)
      .header("Accept", "application/json")
      .header("Content-type", "application/json")
      .asJson();
    return new ApiResponse<JsonNode>(resp.getStatus(), null, resp.getBody());
  }

  public ApiResponse<JsonNode> getGroups(String endpointURL) throws Exception {
    HttpResponse<JsonNode> resp = Unirest.get(endpointURL)
      .basicAuth(this.username, this.password)
      .header("Accept", "application/json")
      .header("Content-type", "application/json")
      .asJson();
    return new ApiResponse<JsonNode>(resp.getStatus(), null, resp.getBody());
  }
}
