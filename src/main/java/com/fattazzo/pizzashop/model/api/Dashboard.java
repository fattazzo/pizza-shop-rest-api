package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.DashBoardPizzas;
import com.fattazzo.pizzashop.model.api.DashboardCustomers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Dashboard
 */
@Validated
public class Dashboard   {
  @JsonProperty("customers")
  private DashboardCustomers customers = null;

  @JsonProperty("pizzas")
  private DashBoardPizzas pizzas = null;

  public Dashboard customers(DashboardCustomers customers) {
    this.customers = customers;
    return this;
  }

  /**
   * Get customers
   * @return customers
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public DashboardCustomers getCustomers() {
    return customers;
  }

  public void setCustomers(DashboardCustomers customers) {
    this.customers = customers;
  }

  public Dashboard pizzas(DashBoardPizzas pizzas) {
    this.pizzas = pizzas;
    return this;
  }

  /**
   * Get pizzas
   * @return pizzas
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public DashBoardPizzas getPizzas() {
    return pizzas;
  }

  public void setPizzas(DashBoardPizzas pizzas) {
    this.pizzas = pizzas;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dashboard dashboard = (Dashboard) o;
    return Objects.equals(this.customers, dashboard.customers) &&
        Objects.equals(this.pizzas, dashboard.pizzas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customers, pizzas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Dashboard {\n");
    
    sb.append("    customers: ").append(toIndentedString(customers)).append("\n");
    sb.append("    pizzas: ").append(toIndentedString(pizzas)).append("\n");
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
