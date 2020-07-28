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
 * PingRegistry
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-20T18:34:04.523Z")
public class PingRegistry {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("credential_type")
  private String credentialType = null;

  @SerializedName("access_key")
  private String accessKey = null;

  @SerializedName("access_secret")
  private String accessSecret = null;

  @SerializedName("insecure")
  private Boolean insecure = null;

  public PingRegistry id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The ID of the registry
   * @return id
  **/
  @ApiModelProperty(value = "The ID of the registry")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PingRegistry type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Type of the registry, e.g. &#39;harbor&#39;.
   * @return type
  **/
  @ApiModelProperty(value = "Type of the registry, e.g. 'harbor'.")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public PingRegistry url(String url) {
    this.url = url;
    return this;
  }

   /**
   * The registry address URL string.
   * @return url
  **/
  @ApiModelProperty(value = "The registry address URL string.")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public PingRegistry credentialType(String credentialType) {
    this.credentialType = credentialType;
    return this;
  }

   /**
   * Credential type of the registry, e.g. &#39;basic&#39;.
   * @return credentialType
  **/
  @ApiModelProperty(value = "Credential type of the registry, e.g. 'basic'.")
  public String getCredentialType() {
    return credentialType;
  }

  public void setCredentialType(String credentialType) {
    this.credentialType = credentialType;
  }

  public PingRegistry accessKey(String accessKey) {
    this.accessKey = accessKey;
    return this;
  }

   /**
   * The registry access key.
   * @return accessKey
  **/
  @ApiModelProperty(value = "The registry access key.")
  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public PingRegistry accessSecret(String accessSecret) {
    this.accessSecret = accessSecret;
    return this;
  }

   /**
   * The registry access secret.
   * @return accessSecret
  **/
  @ApiModelProperty(value = "The registry access secret.")
  public String getAccessSecret() {
    return accessSecret;
  }

  public void setAccessSecret(String accessSecret) {
    this.accessSecret = accessSecret;
  }

  public PingRegistry insecure(Boolean insecure) {
    this.insecure = insecure;
    return this;
  }

   /**
   * Whether or not the certificate will be verified when Harbor tries to access the server.
   * @return insecure
  **/
  @ApiModelProperty(value = "Whether or not the certificate will be verified when Harbor tries to access the server.")
  public Boolean isInsecure() {
    return insecure;
  }

  public void setInsecure(Boolean insecure) {
    this.insecure = insecure;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PingRegistry pingRegistry = (PingRegistry) o;
    return Objects.equals(this.id, pingRegistry.id) &&
        Objects.equals(this.type, pingRegistry.type) &&
        Objects.equals(this.url, pingRegistry.url) &&
        Objects.equals(this.credentialType, pingRegistry.credentialType) &&
        Objects.equals(this.accessKey, pingRegistry.accessKey) &&
        Objects.equals(this.accessSecret, pingRegistry.accessSecret) &&
        Objects.equals(this.insecure, pingRegistry.insecure);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, url, credentialType, accessKey, accessSecret, insecure);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PingRegistry {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    credentialType: ").append(toIndentedString(credentialType)).append("\n");
    sb.append("    accessKey: ").append(toIndentedString(accessKey)).append("\n");
    sb.append("    accessSecret: ").append(toIndentedString(accessSecret)).append("\n");
    sb.append("    insecure: ").append(toIndentedString(insecure)).append("\n");
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

