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

/**
 * RobotAccountPostRep
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-01-05T17:56:47.048Z")
public class RobotAccountPostRep {
  @SerializedName("name")
  private String name = null;

  @SerializedName("token")
  private String token = null;

  public RobotAccountPostRep name(String name) {
    this.name = name;
    return this;
  }

   /**
   * the name of robot account
   * @return name
  **/
  @ApiModelProperty(value = "the name of robot account")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RobotAccountPostRep token(String token) {
    this.token = token;
    return this;
  }

   /**
   * the token of robot account
   * @return token
  **/
  @ApiModelProperty(value = "the token of robot account")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RobotAccountPostRep robotAccountPostRep = (RobotAccountPostRep) o;
    return Objects.equals(this.name, robotAccountPostRep.name) &&
        Objects.equals(this.token, robotAccountPostRep.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, token);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RobotAccountPostRep {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

