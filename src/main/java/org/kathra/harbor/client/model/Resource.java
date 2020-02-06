/*
 * Harbor API
 * These APIs provide services for manipulating Harbor project.
 *
 * OpenAPI spec version: 1.7.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.kathra.harbor.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Resource
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-01-05T17:56:47.048Z")
public class Resource {
  @SerializedName("replication_policies")
  private List<ReplicationPolicy> replicationPolicies = null;

  public Resource replicationPolicies(List<ReplicationPolicy> replicationPolicies) {
    this.replicationPolicies = replicationPolicies;
    return this;
  }

  public Resource addReplicationPoliciesItem(ReplicationPolicy replicationPoliciesItem) {
    if (this.replicationPolicies == null) {
      this.replicationPolicies = new ArrayList<ReplicationPolicy>();
    }
    this.replicationPolicies.add(replicationPoliciesItem);
    return this;
  }

   /**
   * The replication policy list.
   * @return replicationPolicies
  **/
  @ApiModelProperty(value = "The replication policy list.")
  public List<ReplicationPolicy> getReplicationPolicies() {
    return replicationPolicies;
  }

  public void setReplicationPolicies(List<ReplicationPolicy> replicationPolicies) {
    this.replicationPolicies = replicationPolicies;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resource resource = (Resource) o;
    return Objects.equals(this.replicationPolicies, resource.replicationPolicies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(replicationPolicies);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Resource {\n");
    
    sb.append("    replicationPolicies: ").append(toIndentedString(replicationPolicies)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
