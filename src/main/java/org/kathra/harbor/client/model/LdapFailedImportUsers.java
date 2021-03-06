/*
 * Harbor API
 * These APIs provide services for manipulating Harbor project.
 *
 * OpenAPI spec version: 1.10.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.kathra.harbor.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * LdapFailedImportUsers
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-20T18:34:04.523Z")
public class LdapFailedImportUsers {
  @SerializedName("ldap_uid")
  private String ldapUid = null;

  @SerializedName("error")
  private String error = null;

  public LdapFailedImportUsers ldapUid(String ldapUid) {
    this.ldapUid = ldapUid;
    return this;
  }

   /**
   * the uid can&#39;t add to system.
   * @return ldapUid
  **/
  @ApiModelProperty(value = "the uid can't add to system.")
  public String getLdapUid() {
    return ldapUid;
  }

  public void setLdapUid(String ldapUid) {
    this.ldapUid = ldapUid;
  }

  public LdapFailedImportUsers error(String error) {
    this.error = error;
    return this;
  }

   /**
   * fail reason.
   * @return error
  **/
  @ApiModelProperty(value = "fail reason.")
  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LdapFailedImportUsers ldapFailedImportUsers = (LdapFailedImportUsers) o;
    return Objects.equals(this.ldapUid, ldapFailedImportUsers.ldapUid) &&
        Objects.equals(this.error, ldapFailedImportUsers.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ldapUid, error);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LdapFailedImportUsers {\n");
    
    sb.append("    ldapUid: ").append(toIndentedString(ldapUid)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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

