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
import org.kathra.harbor.client.model.VulnerabilitySummary;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * The summary for the native report
 */
@ApiModel(description = "The summary for the native report")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-20T18:34:04.523Z")
public class NativeReportSummary {
  @SerializedName("report_id")
  private String reportId = null;

  @SerializedName("scan_status")
  private String scanStatus = null;

  @SerializedName("severity")
  private String severity = null;

  @SerializedName("duration")
  private Long duration = null;

  @SerializedName("summary")
  private VulnerabilitySummary summary = null;

  @SerializedName("start_time")
  private OffsetDateTime startTime = null;

  @SerializedName("end_time")
  private OffsetDateTime endTime = null;

  public NativeReportSummary reportId(String reportId) {
    this.reportId = reportId;
    return this;
  }

   /**
   * id of the native scan report
   * @return reportId
  **/
  @ApiModelProperty(example = "5f62c830-f996-11e9-957f-0242c0a89008", value = "id of the native scan report")
  public String getReportId() {
    return reportId;
  }

  public void setReportId(String reportId) {
    this.reportId = reportId;
  }

  public NativeReportSummary scanStatus(String scanStatus) {
    this.scanStatus = scanStatus;
    return this;
  }

   /**
   * The status of the report generating process
   * @return scanStatus
  **/
  @ApiModelProperty(example = "Success", value = "The status of the report generating process")
  public String getScanStatus() {
    return scanStatus;
  }

  public void setScanStatus(String scanStatus) {
    this.scanStatus = scanStatus;
  }

  public NativeReportSummary severity(String severity) {
    this.severity = severity;
    return this;
  }

   /**
   * The overall severity
   * @return severity
  **/
  @ApiModelProperty(example = "High", value = "The overall severity")
  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public NativeReportSummary duration(Long duration) {
    this.duration = duration;
    return this;
  }

   /**
   * The seconds spent for generating the report
   * @return duration
  **/
  @ApiModelProperty(example = "300", value = "The seconds spent for generating the report")
  public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
  }

  public NativeReportSummary summary(VulnerabilitySummary summary) {
    this.summary = summary;
    return this;
  }

   /**
   * Get summary
   * @return summary
  **/
  @ApiModelProperty(value = "")
  public VulnerabilitySummary getSummary() {
    return summary;
  }

  public void setSummary(VulnerabilitySummary summary) {
    this.summary = summary;
  }

  public NativeReportSummary startTime(OffsetDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * The start time of the scan process that generating report
   * @return startTime
  **/
  @ApiModelProperty(example = "2006-01-02T14:04:05", value = "The start time of the scan process that generating report")
  public OffsetDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public NativeReportSummary endTime(OffsetDateTime endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * The end time of the scan process that generating report
   * @return endTime
  **/
  @ApiModelProperty(example = "2006-01-02T15:04:05", value = "The end time of the scan process that generating report")
  public OffsetDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(OffsetDateTime endTime) {
    this.endTime = endTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NativeReportSummary nativeReportSummary = (NativeReportSummary) o;
    return Objects.equals(this.reportId, nativeReportSummary.reportId) &&
        Objects.equals(this.scanStatus, nativeReportSummary.scanStatus) &&
        Objects.equals(this.severity, nativeReportSummary.severity) &&
        Objects.equals(this.duration, nativeReportSummary.duration) &&
        Objects.equals(this.summary, nativeReportSummary.summary) &&
        Objects.equals(this.startTime, nativeReportSummary.startTime) &&
        Objects.equals(this.endTime, nativeReportSummary.endTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reportId, scanStatus, severity, duration, summary, startTime, endTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NativeReportSummary {\n");
    
    sb.append("    reportId: ").append(toIndentedString(reportId)).append("\n");
    sb.append("    scanStatus: ").append(toIndentedString(scanStatus)).append("\n");
    sb.append("    severity: ").append(toIndentedString(severity)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
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

