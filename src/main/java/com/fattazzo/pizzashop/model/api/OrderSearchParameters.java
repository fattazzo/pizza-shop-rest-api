package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.OrderState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderSearchParameters
 */
@Validated
public class OrderSearchParameters   {
  @JsonProperty("states")
  @Valid
  private List<OrderState> states = null;

  @JsonProperty("branchId")
  private Integer branchId = null;

  public OrderSearchParameters states(List<OrderState> states) {
    this.states = states;
    return this;
  }

  public OrderSearchParameters addStatesItem(OrderState statesItem) {
    if (this.states == null) {
      this.states = new ArrayList<>();
    }
    this.states.add(statesItem);
    return this;
  }

  /**
   * Get states
   * @return states
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<OrderState> getStates() {
    return states;
  }

  public void setStates(List<OrderState> states) {
    this.states = states;
  }

  public OrderSearchParameters branchId(Integer branchId) {
    this.branchId = branchId;
    return this;
  }

  /**
   * Get branchId
   * @return branchId
  **/
  @ApiModelProperty(value = "")
  
    public Integer getBranchId() {
    return branchId;
  }

  public void setBranchId(Integer branchId) {
    this.branchId = branchId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderSearchParameters orderSearchParameters = (OrderSearchParameters) o;
    return Objects.equals(this.states, orderSearchParameters.states) &&
        Objects.equals(this.branchId, orderSearchParameters.branchId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(states, branchId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderSearchParameters {\n");
    
    sb.append("    states: ").append(toIndentedString(states)).append("\n");
    sb.append("    branchId: ").append(toIndentedString(branchId)).append("\n");
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
