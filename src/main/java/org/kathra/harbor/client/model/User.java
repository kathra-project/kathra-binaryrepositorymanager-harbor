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
 * User
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-20T18:34:04.523Z")
public class User {
  @SerializedName("user_id")
  private Integer userId = null;

  @SerializedName("username")
  private String username = null;

  @SerializedName("email")
  private String email = null;

  @SerializedName("password")
  private String password = null;

  @SerializedName("realname")
  private String realname = null;

  @SerializedName("comment")
  private String comment = null;

  @SerializedName("deleted")
  private Boolean deleted = null;

  @SerializedName("role_name")
  private String roleName = null;

  @SerializedName("role_id")
  private Integer roleId = null;

  @SerializedName("has_admin_role")
  private Boolean hasAdminRole = null;

  @SerializedName("reset_uuid")
  private String resetUuid = null;

  @SerializedName("Salt")
  private String salt = null;

  @SerializedName("creation_time")
  private String creationTime = null;

  @SerializedName("update_time")
  private String updateTime = null;

  public User userId(Integer userId) {
    this.userId = userId;
    return this;
  }

   /**
   * The ID of the user.
   * @return userId
  **/
  @ApiModelProperty(value = "The ID of the user.")
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public User username(String username) {
    this.username = username;
    return this;
  }

   /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(value = "")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(value = "")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User realname(String realname) {
    this.realname = realname;
    return this;
  }

   /**
   * Get realname
   * @return realname
  **/
  @ApiModelProperty(value = "")
  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }

  public User comment(String comment) {
    this.comment = comment;
    return this;
  }

   /**
   * Get comment
   * @return comment
  **/
  @ApiModelProperty(value = "")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public User deleted(Boolean deleted) {
    this.deleted = deleted;
    return this;
  }

   /**
   * Get deleted
   * @return deleted
  **/
  @ApiModelProperty(value = "")
  public Boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public User roleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

   /**
   * Get roleName
   * @return roleName
  **/
  @ApiModelProperty(value = "")
  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public User roleId(Integer roleId) {
    this.roleId = roleId;
    return this;
  }

   /**
   * Get roleId
   * @return roleId
  **/
  @ApiModelProperty(value = "")
  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public User hasAdminRole(Boolean hasAdminRole) {
    this.hasAdminRole = hasAdminRole;
    return this;
  }

   /**
   * Get hasAdminRole
   * @return hasAdminRole
  **/
  @ApiModelProperty(value = "")
  public Boolean isHasAdminRole() {
    return hasAdminRole;
  }

  public void setHasAdminRole(Boolean hasAdminRole) {
    this.hasAdminRole = hasAdminRole;
  }

  public User resetUuid(String resetUuid) {
    this.resetUuid = resetUuid;
    return this;
  }

   /**
   * Get resetUuid
   * @return resetUuid
  **/
  @ApiModelProperty(value = "")
  public String getResetUuid() {
    return resetUuid;
  }

  public void setResetUuid(String resetUuid) {
    this.resetUuid = resetUuid;
  }

  public User salt(String salt) {
    this.salt = salt;
    return this;
  }

   /**
   * Get salt
   * @return salt
  **/
  @ApiModelProperty(value = "")
  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public User creationTime(String creationTime) {
    this.creationTime = creationTime;
    return this;
  }

   /**
   * Get creationTime
   * @return creationTime
  **/
  @ApiModelProperty(value = "")
  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }

  public User updateTime(String updateTime) {
    this.updateTime = updateTime;
    return this;
  }

   /**
   * Get updateTime
   * @return updateTime
  **/
  @ApiModelProperty(value = "")
  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.userId, user.userId) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.realname, user.realname) &&
        Objects.equals(this.comment, user.comment) &&
        Objects.equals(this.deleted, user.deleted) &&
        Objects.equals(this.roleName, user.roleName) &&
        Objects.equals(this.roleId, user.roleId) &&
        Objects.equals(this.hasAdminRole, user.hasAdminRole) &&
        Objects.equals(this.resetUuid, user.resetUuid) &&
        Objects.equals(this.salt, user.salt) &&
        Objects.equals(this.creationTime, user.creationTime) &&
        Objects.equals(this.updateTime, user.updateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, username, email, password, realname, comment, deleted, roleName, roleId, hasAdminRole, resetUuid, salt, creationTime, updateTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    realname: ").append(toIndentedString(realname)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    deleted: ").append(toIndentedString(deleted)).append("\n");
    sb.append("    roleName: ").append(toIndentedString(roleName)).append("\n");
    sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
    sb.append("    hasAdminRole: ").append(toIndentedString(hasAdminRole)).append("\n");
    sb.append("    resetUuid: ").append(toIndentedString(resetUuid)).append("\n");
    sb.append("    salt: ").append(toIndentedString(salt)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
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

