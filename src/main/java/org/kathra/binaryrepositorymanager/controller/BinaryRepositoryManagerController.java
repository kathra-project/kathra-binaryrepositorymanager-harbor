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

import org.kathra.binaryrepositorymanager.Config;
import org.kathra.binaryrepositorymanager.model.Credential;
import org.kathra.binaryrepositorymanager.service.BinaryRepositoryManagerService;
import org.kathra.binaryrepositorymanager.service.HarborClient;
import org.kathra.binaryrepositorymanager.service.UserRepositoryActivateToken;
import org.kathra.core.model.BinaryRepository;
import org.kathra.core.model.Membership;
import org.kathra.utils.KathraException;
import org.kathra.utils.annotations.Eager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named("BinaryRepositoryManagerController")
@ApplicationScoped
@Eager
public class BinaryRepositoryManagerController implements BinaryRepositoryManagerService {

  private Config config;
  private HarborClient harborClient;
  private UserRepositoryActivateToken userRepositoryActivateToken;
  private Credential adminCredential;


  public BinaryRepositoryManagerController() {
    this.config = new Config();
    this.adminCredential = new Credential().username(config.getHarborUsername()).password(config.getHarborPassword());
    this.harborClient = new HarborClient(config.getHarborUrl(), config.getHarborUsername(), config.getHarborPassword());
    this.userRepositoryActivateToken = UserRepositoryActivateToken.getInstance();
  }
  public BinaryRepositoryManagerController(Config config, HarborClient client, UserRepositoryActivateToken userRepositoryActivateToken) {
    this.harborClient = client;
    this.userRepositoryActivateToken = userRepositoryActivateToken;
    this.config = config;
    this.adminCredential = new Credential().username(config.getHarborUsername()).password(config.getHarborPassword());
  }
  
  /**
  * Add a containers repository
  * 
  * @param binaryRepository Containers repository object to be created (required)
  * @return BinaryRepository
  * HttpCodes : 201 Created, 400 Bad request, 401 Unauthorized, 409 Conflict(Already existing), 500 Internal Server Error
  */
  public BinaryRepository addBinaryRepository(BinaryRepository binaryRepository) throws Exception {
    BinaryRepository binaryRepositoryCreated = harborClient.addRepository(binaryRepository);
    return binaryRepository.url(binaryRepositoryCreated.getUrl()).provider(binaryRepositoryCreated.getProvider()).providerId(binaryRepositoryCreated.getProviderId());
  }
  
  /**
  * Add a containers repository membership for a user or group
  * 
  * @param containersRepoId The id of the containers repository to retrieve (required)
  * @param binaryRepositoryMembership Membership object to add to the containers repository (required)
  * @return Membership
  */

  public void addBinaryRepositoryMembership(final String containersRepoId, final Membership binaryRepositoryMembership) throws Exception {
    harborClient.addRepositoryMembership(containersRepoId, binaryRepositoryMembership);
  }

  /**
  * Retrieve a list of existing containers repositories for authenticated user
  * 
  * @return List<BinaryRepository>
  */
  public List<BinaryRepository> getBinaryRepositories() throws Exception {
    return harborClient.getRepositories();
  }

  /**
  * Retrieve a list of users and groups membership values for the specified containers repository
  * 
  * @param containersRepoId The id of the containers repository to retrieve (required)
  * @return List<Membership>
  */
  public List<Membership> getBinaryRepositoryMembership(String containersRepoId) throws Exception {
    return harborClient.getMemberships(containersRepoId);
  }

  public void setHarborClient(HarborClient client){
    this.harborClient = client;
  }

  @Override
  public Credential credentialsIdGet(String id) {
    return this.userRepositoryActivateToken.getUserSecretCli().getOrDefault(id, adminCredential);
  }

  @Override
  public BinaryRepository getBinaryRepository(String binaryRepoId) throws Exception {
    return getBinaryRepositories().parallelStream()
            .filter(b -> b.getProviderId().equals(binaryRepoId)).findFirst()
            .orElseThrow(() -> new KathraException("Unable to find binaryRepository", null, KathraException.ErrorCode.NOT_FOUND));
  }

  @Override
  public BinaryRepository updateBinaryRepository(String binaryRepoId, BinaryRepository binaryRepository) throws Exception {
    //TODO: Implement this method
    throw new UnsupportedOperationException("No implementation could be found for the requested operation.");
  }

  @Override
  public BinaryRepository updateBinaryRepositoryAttributes(String binaryRepoId, BinaryRepository binaryRepository) throws Exception {
    //TODO: Implement this method
    throw new UnsupportedOperationException("No implementation could be found for the requested operation.");
  }
}
