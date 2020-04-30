package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.DashBoardProducts;
import com.fattazzo.pizzashop.model.api.DashboardCustomers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Dashboard
 */

public class Dashboard   {
  @JsonProperty("customers")
  private DashboardCustomers customers = null;

  @JsonProperty("products")
  private DashBoardProducts products = null;

  public Dashboard customers(DashboardCustomers customers) {
    this.customers = customers;
    return this;
  }

  /**
   * Get customers
   * @return customers
  **/
  @ApiModelProperty(required = true, value = "")
    public DashboardCustomers getCustomers() {
    return customers;
  }

  public void setCustomers(DashboardCustomers customers) {
    this.customers = customers;
  }

  public Dashboard products(DashBoardProducts products) {
    this.products = products;
    return this;
  }

  /**
   * Get products
   * @return products
  **/
  @ApiModelProperty(required = true, value = "")
    public DashBoardProducts getProducts() {
    return products;
  }

  public void setProducts(DashBoardProducts products) {
    this.products = products;
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
        Objects.equals(this.products, dashboard.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customers, products);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Dashboard {\n");
    
    sb.append("    customers: ").append(toIndentedString(customers)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
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
