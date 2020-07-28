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
 * ScannerRegistrationSettings
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-20T18:34:04.523Z")
public class ScannerRegistrationSettings {
  @SerializedName("name")
  private String name = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("auth")
  private String auth = "";

  @SerializedName("access_credential")
  private String accessCredential = null;

  public ScannerRegistrationSettings name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of this registration
   * @return name
  **/
  @ApiModelProperty(example = "Clair", value = "The name of this registration")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ScannerRegistrationSettings url(String url) {
    this.url = url;
    return this;
  }

   /**
   * A base URL of the scanner adapter.
   * @return url
  **/
  @ApiModelProperty(example = "http://harbor-scanner-clair:8080", value = "A base URL of the scanner adapter.")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ScannerRegistrationSettings auth(String auth) {
    this.auth = auth;
    return this;
  }

   /**
   * Specify what authentication approach is adopted for the HTTP communications. Supported types Basic\&quot;, \&quot;Bearer\&quot; and api key header \&quot;X-ScannerAdapter-API-Key\&quot; 
   * @return auth
  **/
  @ApiModelProperty(value = "Specify what authentication approach is adopted for the HTTP communications. Supported types Basic\", \"Bearer\" and api key header \"X-ScannerAdapter-API-Key\" ")
  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public ScannerRegistrationSettings accessCredential(String accessCredential) {
    this.accessCredential = accessCredential;
    return this;
  }

   /**
   * An optional value of the HTTP Authorization header sent with each request to the Scanner Adapter API. 
   * @return accessCredential
  **/
  @ApiModelProperty(example = "Bearer: JWTTOKENGOESHERE", value = "An optional value of the HTTP Authorization header sent with each request to the Scanner Adapter API. ")
  public String getAccessCredential() {
    return accessCredential;
  }

  public void setAccessCredential(String accessCredential) {
    this.accessCredential = accessCredential;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScannerRegistrationSettings scannerRegistrationSettings = (ScannerRegistrationSettings) o;
    return Objects.equals(this.name, scannerRegistrationSettings.name) &&
        Objects.equals(this.url, scannerRegistrationSettings.url) &&
        Objects.equals(this.auth, scannerRegistrationSettings.auth) &&
        Objects.equals(this.accessCredential, scannerRegistrationSettings.accessCredential);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, url, auth, accessCredential);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScannerRegistrationSettings {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    auth: ").append(toIndentedString(auth)).append("\n");
    sb.append("    accessCredential: ").append(toIndentedString(accessCredential)).append("\n");
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

