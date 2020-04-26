package com.fattazzo.pizzashop.model.api;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * DeliveryAddress
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-26T14:55:34.957Z[GMT]")
public class DeliveryAddress   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("address")
  private Address address = null;

  @JsonProperty("shippingMethod")
  private ShippingMethod shippingMethod = null;

  public DeliveryAddress id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DeliveryAddress address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public DeliveryAddress shippingMethod(ShippingMethod shippingMethod) {
    this.shippingMethod = shippingMethod;
    return this;
  }

  /**
   * Get shippingMethod
   * @return shippingMethod
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ShippingMethod getShippingMethod() {
    return shippingMethod;
  }

  public void setShippingMethod(ShippingMethod shippingMethod) {
    this.shippingMethod = shippingMethod;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeliveryAddress deliveryAddress = (DeliveryAddress) o;
    return Objects.equals(this.id, deliveryAddress.id) &&
        Objects.equals(this.address, deliveryAddress.address) &&
        Objects.equals(this.shippingMethod, deliveryAddress.shippingMethod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, shippingMethod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeliveryAddress {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    shippingMethod: ").append(toIndentedString(shippingMethod)).append("\n");
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
