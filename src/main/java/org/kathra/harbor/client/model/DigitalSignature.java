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
 * The signature of the chart
 */
@ApiModel(description = "The signature of the chart")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-20T18:34:04.523Z")
public class DigitalSignature {
  @SerializedName("signed")
  private Boolean signed = null;

  @SerializedName("prov_file")
  private String provFile = null;

  public DigitalSignature signed(Boolean signed) {
    this.signed = signed;
    return this;
  }

   /**
   * A flag to indicate if the chart is signed
   * @return signed
  **/
  @ApiModelProperty(value = "A flag to indicate if the chart is signed")
  public Boolean isSigned() {
    return signed;
  }

  public void setSigned(Boolean signed) {
    this.signed = signed;
  }

  public DigitalSignature provFile(String provFile) {
    this.provFile = provFile;
    return this;
  }

   /**
   * The URL of the provance file
   * @return provFile
  **/
  @ApiModelProperty(value = "The URL of the provance file")
  public String getProvFile() {
    return provFile;
  }

  public void setProvFile(String provFile) {
    this.provFile = provFile;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DigitalSignature digitalSignature = (DigitalSignature) o;
    return Objects.equals(this.signed, digitalSignature.signed) &&
        Objects.equals(this.provFile, digitalSignature.provFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signed, provFile);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DigitalSignature {\n");
    
    sb.append("    signed: ").append(toIndentedString(signed)).append("\n");
    sb.append("    provFile: ").append(toIndentedString(provFile)).append("\n");
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

