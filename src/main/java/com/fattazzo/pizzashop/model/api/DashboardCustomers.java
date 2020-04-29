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
 * DashboardCustomers
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-28T14:20:54.148Z[GMT]")
public class DashboardCustomers   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("toConfirm")
  private Integer toConfirm = null;

  public DashboardCustomers total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Total user of type 'Customer'
   * @return total
  **/
  @ApiModelProperty(required = true, value = "Total user of type 'Customer'")
      @NotNull

    public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public DashboardCustomers toConfirm(Integer toConfirm) {
    this.toConfirm = toConfirm;
    return this;
  }

  /**
   * Number of 'Customer' users who need to confirm registration
   * @return toConfirm
  **/
  @ApiModelProperty(required = true, value = "Number of 'Customer' users who need to confirm registration")
      @NotNull

    public Integer getToConfirm() {
    return toConfirm;
  }

  public void setToConfirm(Integer toConfirm) {
    this.toConfirm = toConfirm;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardCustomers dashboardCustomers = (DashboardCustomers) o;
    return Objects.equals(this.total, dashboardCustomers.total) &&
        Objects.equals(this.toConfirm, dashboardCustomers.toConfirm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, toConfirm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardCustomers {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    toConfirm: ").append(toIndentedString(toConfirm)).append("\n");
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
