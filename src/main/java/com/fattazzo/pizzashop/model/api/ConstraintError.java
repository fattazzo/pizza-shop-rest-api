package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * ConstraintError
 */

public class ConstraintError   {
  @JsonProperty("fieldName")
  private String fieldName = null;

  @JsonProperty("constraintsNotRespected")
  
  private List<String> constraintsNotRespected = null;

  public ConstraintError fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  /**
   * Get fieldName
   * @return fieldName
  **/
  @ApiModelProperty(value = "")
    public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public ConstraintError constraintsNotRespected(List<String> constraintsNotRespected) {
    this.constraintsNotRespected = constraintsNotRespected;
    return this;
  }

  public ConstraintError addConstraintsNotRespectedItem(String constraintsNotRespectedItem) {
    if (this.constraintsNotRespected == null) {
      this.constraintsNotRespected = new ArrayList<String>();
    }
    this.constraintsNotRespected.add(constraintsNotRespectedItem);
    return this;
  }

  /**
   * Get constraintsNotRespected
   * @return constraintsNotRespected
  **/
  @ApiModelProperty(value = "")
    public List<String> getConstraintsNotRespected() {
    return constraintsNotRespected;
  }

  public void setConstraintsNotRespected(List<String> constraintsNotRespected) {
    this.constraintsNotRespected = constraintsNotRespected;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstraintError constraintError = (ConstraintError) o;
    return Objects.equals(this.fieldName, constraintError.fieldName) &&
        Objects.equals(this.constraintsNotRespected, constraintError.constraintsNotRespected);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldName, constraintsNotRespected);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstraintError {\n");
    
    sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
    sb.append("    constraintsNotRespected: ").append(toIndentedString(constraintsNotRespected)).append("\n");
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
