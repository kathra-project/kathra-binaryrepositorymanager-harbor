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
// package org.kathra.binaryrepositorymanager.controller;

// import org.junit.jupiter.api.*;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.mockito.junit.jupiter.MockitoSettings;
// import org.mockito.quality.Strictness;

// import org.kathra.binaryrepositorymanager.model.BinaryRepository;
// import org.kathra.binaryrepositorymanager.model.BinaryRepositoryMetadata;
// import org.kathra.core.model.Membership;
// import org.kathra.utils.ApiResponse;
// import org.kathra.utils.serialization.GsonUtils;

// import static org.junit.Assert.assertThat;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.hamcrest.CoreMatchers.containsString;
// import static org.hamcrest.core.Is.is;

// import java.util.HashMap;
// import com.mashape.unirest.http.JsonNode;

// @ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
// class BinaryRepositoryManagerControllerTest {
  
//   /**
//   * Local Variables
//   */
//   private BinaryRepositoryManagerController underTest;
  
//   /**
//   * Mocks
//   */
//   @Mock
//   private HarborClient harborClient;
//   private String harborUrl = "https://registry.hub.docker.com/api";
  
//   /**
//   * Context initialization
//   */
//   @BeforeAll
//   static void setUp() throws Exception {
    
//   }
  
//   /**
//   * Mocks behaviour initialization
//   */
//   @BeforeEach
//   void setUpEach() throws Exception {
//     harborClient = Mockito.mock(HarborClient.class);
//     Mockito.reset(harborClient);
//     underTest = new BinaryRepositoryManagerController(harborClient, harborUrl);
//   }
  
//   /**
//   * After tests 
//   */
//   @AfterAll
//   static void tearDown() {
//   }
  
//   @Test
//   public void given_nominal_args_when_creating_repository_then_works() throws Exception {
//     String endpointUrl = String.join("/", harborUrl, "projects");
    
//     BinaryRepository repoToCreate = mockRepoToCreate("myRepo");
//     String jsonStringRepo = mockTransformedRepoToCreate(repoToCreate);

//     ApiResponse<String> addRepoResponse = new ApiResponse<String>(201, null, "");
    
//     Mockito.when(harborClient.addRepository(jsonStringRepo, endpointUrl)).thenReturn(addRepoResponse);
    
//     assertThat("The response HTTP Status Code is 201(Created)", underTest.addBinaryRepository(repoToCreate).getStatusCode(), is(201));
//     assertThat("The response informs the resource have been created", underTest.addBinaryRepository(repoToCreate).getData(), is(""));
//   }

//   @Test
//   public void given_nominal_args_when_creating_repository_then_already_exists() throws Exception {
//     String endpointUrl = String.join("/", harborUrl, "projects");
    
//     BinaryRepository repoToCreate = mockRepoToCreate("myRepo");
//     String jsonStringRepo = mockTransformedRepoToCreate(repoToCreate);

//     ApiResponse<String> addRepoResponse = new ApiResponse<String>(409, null, "\n");
    
//     Mockito.when(harborClient.addRepository(jsonStringRepo, endpointUrl)).thenReturn(addRepoResponse);
    
//     assertThat("The response HTTP Status Code is 409(Conflict)", underTest.addBinaryRepository(repoToCreate).getStatusCode(), is(409));
//     assertThat("The response informs the resource to create already exists", underTest.addBinaryRepository(repoToCreate).getData(), is("\n"));
//   }

//   @Test
//   public void given_bad_request_when_creating_repository_then_fails() throws Exception {
//     String endpointUrl = String.join("/", harborUrl, "projects");
    
//     BinaryRepository repoToCreate = mockRepoToCreate("");
//     String jsonStringRepo = mockTransformedRepoToCreate(repoToCreate);

//     ApiResponse<String> addRepoResponse = new ApiResponse<String>(400, null, "invalid request: Project name is illegal in length. (greater than 255 or less than 2)\n");
    
//     Mockito.when(harborClient.addRepository(jsonStringRepo, endpointUrl)).thenReturn(addRepoResponse);
    
//     assertThat("The response HTTP Status Code is 400(Bad Request)", underTest.addBinaryRepository(repoToCreate).getStatusCode(), is(400));
//     assertThat("The response informs the resource request if malformed (repo name)", underTest.addBinaryRepository(repoToCreate).getData(), containsString("invalid request"));
//   }
  
//   @Test
//   public void given_nominal_args_when_creating_repository_membership_then_works() throws Exception {
//     // Typical response when getting repositories from harbor
//     JsonNode jsonReposResponse = new JsonNode("[{\"creation_time\":\"2018-11-12T16:07:33Z\",\"update_time\":\"2018-11-12T16:07:33Z\",\"togglable\":true,\"current_user_role_id\":1,\"metadata\":{\"prevent_vulnerable_images_from_running\":\"false\",\"public\":\"false\",\"automatically_scan_images_on_push\":\"true\",\"prevent_vulnerable_images_from_running_severity\":\"medium\",\"enable_content_trust\":\"false\"},\"deleted\":false,\"owner_name\":\"\",\"project_id\":13,\"owner_id\":1,\"name\":\"myRepo\",\"repo_count\":0,\"chart_count\":0},{\"creation_time\":\"2018-11-12T16:07:29Z\",\"update_time\":\"2018-11-12T16:07:29Z\",\"togglable\":true,\"current_user_role_id\":1,\"metadata\":{\"prevent_vulnerable_images_from_running\":\"false\",\"public\":\"false\",\"automatically_scan_images_on_push\":\"true\",\"prevent_vulnerable_images_from_running_severity\":\"medium\",\"enable_content_trust\":\"false\"},\"deleted\":false,\"owner_name\":\"\",\"project_id\":5,\"owner_id\":1,\"name\":\"sce\",\"repo_count\":0,\"chart_count\":0}]");
//     // Typical response when getting user groups from harbor
//     JsonNode jsonUserGroupsResponse = new JsonNode("[{\"group_name\":\"amc\",\"ldap_group_dn\":\"cn=projet amc,ou=groupes globaux,ou=groupes,ou=nano-innov,dc=irt-systemx,dc=local\",\"id\":1,\"group_type\":1},{\"group_name\":\"sva\",\"ldap_group_dn\":\"cn=projet sva,ou=groupes globaux,ou=groupes,ou=nano-innov,dc=irt-systemx,dc=local\",\"id\":2,\"group_type\":1}]");
    
//     String reposEndpointUrl = String.join("/", harborUrl, "projects");
//     String usergroupsEndpointUrl = String.join("/", harborUrl, "usergroups");
//     String membershipEndpointUrl = String.join("/", harborUrl, "projects", "13" /* AMC Project project_id */, "members");
    
//     // Typical typed payload fetched from the API call
//     Membership repoGroupMembershipToCreate = mockGroupMembershipToCreate("myRepo");
//     Membership repoUserMembershipToCreate = mockUserMembershipToCreate("myRepo");
//     // Simplified payload to give to Harbor
//     String jsonTransformedGroupMembershipToCreate = mockTransformedMembershipToCreate(repoGroupMembershipToCreate);
//     String jsonTransformedUserMembershipToCreate = mockTransformedMembershipToCreate(repoUserMembershipToCreate);
    
//     // Expected HarborClient responses to the tested controller
//     ApiResponse<JsonNode> getReposResponse = new ApiResponse<JsonNode>(200, null, jsonReposResponse);
//     ApiResponse<JsonNode> getUserGroupsResponse = new ApiResponse<JsonNode>(200, null, jsonUserGroupsResponse);
//     ApiResponse<String> addRepoMembershipResponse = new ApiResponse<String>(201, null, "");
    
//     // Defining behaviours 
//     Mockito.when(harborClient.getRepositories(reposEndpointUrl)).thenReturn(getReposResponse);
//     Mockito.when(harborClient.getGroups(usergroupsEndpointUrl)).thenReturn(getUserGroupsResponse);
//     Mockito.when(harborClient.addRepositoryMembership(jsonTransformedGroupMembershipToCreate, membershipEndpointUrl)).thenReturn(addRepoMembershipResponse);
//     Mockito.when(harborClient.addRepositoryMembership(jsonTransformedUserMembershipToCreate, membershipEndpointUrl)).thenReturn(addRepoMembershipResponse);
  
//     assertThat("The response HTTP Status Code is 201(Created)", underTest.addBinaryRepositoryMembership("myRepo", repoGroupMembershipToCreate).getStatusCode(), is(201));
//     assertThat("The response informs the resource have been created", underTest.addBinaryRepositoryMembership("myRepo", repoGroupMembershipToCreate).getData(), is(""));
    
//     assertThat("The response HTTP Status Code is 201(Created)", underTest.addBinaryRepositoryMembership("myRepo", repoUserMembershipToCreate).getStatusCode(), is(201));
//     assertThat("The response informs the resource have been created", underTest.addBinaryRepositoryMembership("myRepo", repoUserMembershipToCreate).getData(), is(""));
//   }

//   @Test
//   public void given_nominal_args_when_creating_repository_membership_then_already_exists() throws Exception {
//     // Typical response when getting repositories from harbor
//     JsonNode jsonReposResponse = new JsonNode("[{\"creation_time\":\"2018-11-12T16:07:33Z\",\"update_time\":\"2018-11-12T16:07:33Z\",\"togglable\":true,\"current_user_role_id\":1,\"metadata\":{\"prevent_vulnerable_images_from_running\":\"false\",\"public\":\"false\",\"automatically_scan_images_on_push\":\"true\",\"prevent_vulnerable_images_from_running_severity\":\"medium\",\"enable_content_trust\":\"false\"},\"deleted\":false,\"owner_name\":\"\",\"project_id\":13,\"owner_id\":1,\"name\":\"myRepo\",\"repo_count\":0,\"chart_count\":0},{\"creation_time\":\"2018-11-12T16:07:29Z\",\"update_time\":\"2018-11-12T16:07:29Z\",\"togglable\":true,\"current_user_role_id\":1,\"metadata\":{\"prevent_vulnerable_images_from_running\":\"false\",\"public\":\"false\",\"automatically_scan_images_on_push\":\"true\",\"prevent_vulnerable_images_from_running_severity\":\"medium\",\"enable_content_trust\":\"false\"},\"deleted\":false,\"owner_name\":\"\",\"project_id\":5,\"owner_id\":1,\"name\":\"sce\",\"repo_count\":0,\"chart_count\":0}]");
//     // Typical response when getting user groups from harbor
//     JsonNode jsonUserGroupsResponse = new JsonNode("[{\"group_name\":\"amc\",\"ldap_group_dn\":\"cn=projet amc,ou=groupes globaux,ou=groupes,ou=nano-innov,dc=irt-systemx,dc=local\",\"id\":1,\"group_type\":1},{\"group_name\":\"sva\",\"ldap_group_dn\":\"cn=projet sva,ou=groupes globaux,ou=groupes,ou=nano-innov,dc=irt-systemx,dc=local\",\"id\":2,\"group_type\":1}]");
    
//     String reposEndpointUrl = String.join("/", harborUrl, "projects");
//     String usergroupsEndpointUrl = String.join("/", harborUrl, "usergroups");
//     String membershipEndpointUrl = String.join("/", harborUrl, "projects", "13" /* AMC Project project_id */, "members");
    
//     // Typical typed payload fetched from the API call
//     Membership repoGroupMembershipToCreate = mockGroupMembershipToCreate("myRepo");
//     Membership repoUserMembershipToCreate = mockUserMembershipToCreate("myRepo");
//     // Simplified payload to give to Harbor
//     String jsonTransformedGroupMembershipToCreate = mockTransformedMembershipToCreate(repoGroupMembershipToCreate);
//     String jsonTransformedUserMembershipToCreate = mockTransformedMembershipToCreate(repoUserMembershipToCreate);
    
//     // Expected HarborClient responses to the tested controller
//     ApiResponse<JsonNode> getReposResponse = new ApiResponse<JsonNode>(200, null, jsonReposResponse);
//     ApiResponse<JsonNode> getUserGroupsResponse = new ApiResponse<JsonNode>(200, null, jsonUserGroupsResponse);
//     ApiResponse<String> addRepoMembershipResponse = new ApiResponse<String>(409, null, "Failed to add project member, already exist LDAP group or project member, groupMemberID:0\n");
    
//     // Defining behaviours 
//     Mockito.when(harborClient.getRepositories(reposEndpointUrl)).thenReturn(getReposResponse);
//     Mockito.when(harborClient.getGroups(usergroupsEndpointUrl)).thenReturn(getUserGroupsResponse);
//     Mockito.when(harborClient.addRepositoryMembership(jsonTransformedGroupMembershipToCreate, membershipEndpointUrl)).thenReturn(addRepoMembershipResponse);
//     Mockito.when(harborClient.addRepositoryMembership(jsonTransformedUserMembershipToCreate, membershipEndpointUrl)).thenReturn(addRepoMembershipResponse);
  
//     assertThat("The response HTTP Status Code is 409(Conflict)", underTest.addBinaryRepositoryMembership("myRepo", repoGroupMembershipToCreate).getStatusCode(), is(409));
//     assertThat("The response informs the resource have been created", underTest.addBinaryRepositoryMembership("myRepo", repoGroupMembershipToCreate).getData(), containsString("Failed to add project member, already exist LDAP group or project member"));
    
//     assertThat("The response HTTP Status Code is 409(Conflict)", underTest.addBinaryRepositoryMembership("myRepo", repoUserMembershipToCreate).getStatusCode(), is(409));
//     assertThat("The response informs the resource have been created", underTest.addBinaryRepositoryMembership("myRepo", repoUserMembershipToCreate).getData(), containsString("Failed to add project member, already exist LDAP group or project member"));
//   }

//   @Test
//   public void given_bad_request_when_creating_repository_membership_then_fails() throws Exception {
//     // Typical response when getting repositories from harbor
//     JsonNode jsonReposResponse = new JsonNode("[{\"creation_time\":\"2018-11-12T16:07:33Z\",\"update_time\":\"2018-11-12T16:07:33Z\",\"togglable\":true,\"current_user_role_id\":1,\"metadata\":{\"prevent_vulnerable_images_from_running\":\"false\",\"public\":\"false\",\"automatically_scan_images_on_push\":\"true\",\"prevent_vulnerable_images_from_running_severity\":\"medium\",\"enable_content_trust\":\"false\"},\"deleted\":false,\"owner_name\":\"\",\"project_id\":13,\"owner_id\":1,\"name\":\"myRepo\",\"repo_count\":0,\"chart_count\":0},{\"creation_time\":\"2018-11-12T16:07:29Z\",\"update_time\":\"2018-11-12T16:07:29Z\",\"togglable\":true,\"current_user_role_id\":1,\"metadata\":{\"prevent_vulnerable_images_from_running\":\"false\",\"public\":\"false\",\"automatically_scan_images_on_push\":\"true\",\"prevent_vulnerable_images_from_running_severity\":\"medium\",\"enable_content_trust\":\"false\"},\"deleted\":false,\"owner_name\":\"\",\"project_id\":5,\"owner_id\":1,\"name\":\"sce\",\"repo_count\":0,\"chart_count\":0}]");
//     // Typical response when getting user groups from harbor
//     JsonNode jsonUserGroupsResponse = new JsonNode("[{\"group_name\":\"amc\",\"ldap_group_dn\":\"cn=projet amc,ou=groupes globaux,ou=groupes,ou=nano-innov,dc=irt-systemx,dc=local\",\"id\":1,\"group_type\":1},{\"group_name\":\"sva\",\"ldap_group_dn\":\"cn=projet sva,ou=groupes globaux,ou=groupes,ou=nano-innov,dc=irt-systemx,dc=local\",\"id\":2,\"group_type\":1}]");
    
//     String reposEndpointUrl = String.join("/", harborUrl, "projects");
//     String usergroupsEndpointUrl = String.join("/", harborUrl, "usergroups");
//     String membershipEndpointUrl = String.join("/", harborUrl, "projects", "13" /* AMC Project project_id */, "members");
    
//     // Typical typed payload fetched from the API call, altered to simulate a bad request
//     Membership repoGroupMembershipToCreate = mockGroupMembershipToCreate("myRepo");
//     repoGroupMembershipToCreate.setRole(null);
//     Membership repoUserMembershipToCreate = mockUserMembershipToCreate("myRepo");
//     repoUserMembershipToCreate.setMemberName("");
//     // Simplified payload to give to Harbor
//     String jsonTransformedGroupMembershipToCreate = mockTransformedMembershipToCreate(repoGroupMembershipToCreate);
//     String jsonTransformedUserMembershipToCreate = mockTransformedMembershipToCreate(repoUserMembershipToCreate);
    
//     // Expected HarborClient responses to the tested controller
//     ApiResponse<JsonNode> getReposResponse = new ApiResponse<JsonNode>(200, null, jsonReposResponse);
//     ApiResponse<JsonNode> getUserGroupsResponse = new ApiResponse<JsonNode>(200, null, jsonUserGroupsResponse);
//     ApiResponse<String> addRepoMembershipResponse = new ApiResponse<String>(400, null, "\n");
    
//     // Defining behaviours 
//     Mockito.when(harborClient.getRepositories(reposEndpointUrl)).thenReturn(getReposResponse);
//     Mockito.when(harborClient.getGroups(usergroupsEndpointUrl)).thenReturn(getUserGroupsResponse);
//     Mockito.when(harborClient.addRepositoryMembership(jsonTransformedGroupMembershipToCreate, membershipEndpointUrl)).thenReturn(addRepoMembershipResponse);
//     Mockito.when(harborClient.addRepositoryMembership(jsonTransformedUserMembershipToCreate, membershipEndpointUrl)).thenReturn(addRepoMembershipResponse);
  
//     assertThat("The response HTTP Status Code is 400(Bad request)", underTest.addBinaryRepositoryMembership("myRepo", repoGroupMembershipToCreate).getStatusCode(), is(400));
//     assertThat("The response informs the request failed", underTest.addBinaryRepositoryMembership("myRepo", repoGroupMembershipToCreate).getData(), is("Bad request"));
    
//     assertThat("The response HTTP Status Code is 400(Bad request)", underTest.addBinaryRepositoryMembership("myRepo", repoUserMembershipToCreate).getStatusCode(), is(400));
//     assertThat("The response informs the request failed", underTest.addBinaryRepositoryMembership("myRepo", repoUserMembershipToCreate).getData(), is("\n"));
//   }

//   private BinaryRepository mockRepoToCreate(String repoName){
//     BinaryRepository repo = new BinaryRepository();
//     BinaryRepositoryMetadata meta = new BinaryRepositoryMetadata(){{ 
//       setIsPublic("false");
//       setScanImagesOnPush("true");
//       setContentTrustEnabled("false");
//       setPreventVulnerableFromRunning("false");
//       setVulnerableThreshold(VulnerableThresholdEnum.MEDIUM);
//     }};
//     repo.setName(repoName);
//     repo.setMetadata(meta);

//     return repo;
//   }

//   private String mockTransformedRepoToCreate(BinaryRepository repo){
//     return GsonUtils.toJson(repo).replace("name", "project_name");
//   }

//   private Membership mockUserMembershipToCreate(String repoName){
//     Membership membership = new Membership(){{
//       setPath(repoName);
//       setMemberName("jenkins.harbor");
//       setMemberType(MemberTypeEnum.USER);
//       setRole(RoleEnum.CONTRIBUTOR);
//     }};

//     return membership;
//   }

//   private Membership mockGroupMembershipToCreate(String repoName){
//     Membership membership = new Membership(){{
//       setPath(repoName);
//       setMemberName("amc");
//       setMemberType(MemberTypeEnum.GROUP);
//       setRole(RoleEnum.CONTRIBUTOR);
//     }};

//     return membership;
//   }

//   private String mockTransformedMembershipToCreate(Membership membership){
//     HashMap<String, Object> member = new HashMap<String, Object>();
//     member.put(membership.getMemberType().getValue().equals("user") ? "username" : "id", membership.getMemberType().getValue().equals("user") ? membership.getMemberName() : 1 /* AMC Usergroup id */);

//     HashMap<String, Object> mapTransformedMembership = new HashMap<String, Object>();
//     mapTransformedMembership.put("role_id", membership.getRole() != null ? membership.getRole().ordinal() : "");
//     mapTransformedMembership.put("member_" + membership.getMemberType(), member);

//     return GsonUtils.toJson(mapTransformedMembership);
//   }
// }
