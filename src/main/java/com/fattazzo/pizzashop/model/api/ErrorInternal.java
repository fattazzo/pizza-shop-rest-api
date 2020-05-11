package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ErrorInternal
 */
@Validated
public class ErrorInternal   {
  @JsonProperty("exception")
  private String exception = null;

  @JsonProperty("stack")
  private String stack = null;

  public ErrorInternal exception(String exception) {
    this.exception = exception;
    return this;
  }

  /**
   * Get exception
   * @return exception
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public ErrorInternal stack(String stack) {
    this.stack = stack;
    return this;
  }

  /**
   * Get stack
   * @return stack
  **/
  @ApiModelProperty(value = "")
  
    public String getStack() {
    return stack;
  }

  public void setStack(String stack) {
    this.stack = stack;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorInternal errorInternal = (ErrorInternal) o;
    return Objects.equals(this.exception, errorInternal.exception) &&
        Objects.equals(this.stack, errorInternal.stack);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exception, stack);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorInternal {\n");
    
    sb.append("    exception: ").append(toIndentedString(exception)).append("\n");
    sb.append("    stack: ").append(toIndentedString(stack)).append("\n");
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
