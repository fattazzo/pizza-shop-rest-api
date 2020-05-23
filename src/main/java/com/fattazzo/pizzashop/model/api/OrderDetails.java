package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Address;
import com.fattazzo.pizzashop.model.api.Branch;
import com.fattazzo.pizzashop.model.api.Order;
import com.fattazzo.pizzashop.model.api.OrderLine;
import com.fattazzo.pizzashop.model.api.OrderState;
import com.fattazzo.pizzashop.model.api.ShippingMethod;
import com.fattazzo.pizzashop.model.api.ShippingType;
import com.fattazzo.pizzashop.model.api.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderDetails
 */
@Validated
public class OrderDetails extends Order  {
  @JsonProperty("branch")
  private Branch branch = null;

  @JsonProperty("lines")
  @Valid
  private List<OrderLine> lines = new ArrayList<>();

  public OrderDetails branch(Branch branch) {
    this.branch = branch;
    return this;
  }

  /**
   * Get branch
   * @return branch
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public OrderDetails lines(List<OrderLine> lines) {
    this.lines = lines;
    return this;
  }

  public OrderDetails addLinesItem(OrderLine linesItem) {
    this.lines.add(linesItem);
    return this;
  }

  /**
   * Get lines
   * @return lines
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<OrderLine> getLines() {
    return lines;
  }

  public void setLines(List<OrderLine> lines) {
    this.lines = lines;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetails orderDetails = (OrderDetails) o;
    return Objects.equals(this.branch, orderDetails.branch) &&
        Objects.equals(this.lines, orderDetails.lines) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(branch, lines, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    branch: ").append(toIndentedString(branch)).append("\n");
    sb.append("    lines: ").append(toIndentedString(lines)).append("\n");
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
