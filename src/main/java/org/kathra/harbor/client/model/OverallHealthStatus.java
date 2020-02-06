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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * The system health status
 */
@ApiModel(description = "The system health status")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-01-05T17:56:47.048Z")
public class OverallHealthStatus {
  @SerializedName("status")
  private String status = null;

  @SerializedName("components")
  private List<ComponentHealthStatus> components = null;

  public OverallHealthStatus status(String status) {
    this.status = status;
    return this;
  }

   /**
   * The overall health status. It is \&quot;healthy\&quot; only when all the components&#39; status are \&quot;healthy\&quot;
   * @return status
  **/
  @ApiModelProperty(value = "The overall health status. It is \"healthy\" only when all the components' status are \"healthy\"")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public OverallHealthStatus components(List<ComponentHealthStatus> components) {
    this.components = components;
    return this;
  }

  public OverallHealthStatus addComponentsItem(ComponentHealthStatus componentsItem) {
    if (this.components == null) {
      this.components = new ArrayList<ComponentHealthStatus>();
    }
    this.components.add(componentsItem);
    return this;
  }

   /**
   * Get components
   * @return components
  **/
  @ApiModelProperty(value = "")
  public List<ComponentHealthStatus> getComponents() {
    return components;
  }

  public void setComponents(List<ComponentHealthStatus> components) {
    this.components = components;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OverallHealthStatus overallHealthStatus = (OverallHealthStatus) o;
    return Objects.equals(this.status, overallHealthStatus.status) &&
        Objects.equals(this.components, overallHealthStatus.components);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, components);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OverallHealthStatus {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    components: ").append(toIndentedString(components)).append("\n");
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
