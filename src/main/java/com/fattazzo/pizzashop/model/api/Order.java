package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Address;
import com.fattazzo.pizzashop.model.api.OrderState;
import com.fattazzo.pizzashop.model.api.ShippingMethod;
import com.fattazzo.pizzashop.model.api.ShippingType;
import com.fattazzo.pizzashop.model.api.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Order
 */
@Validated
public class Order   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("customer")
  private User customer = null;

  @JsonProperty("state")
  private OrderState state = null;

  @JsonProperty("shippingMethod")
  private ShippingMethod shippingMethod = null;

  @JsonProperty("transactionId")
  private String transactionId = null;

  @JsonProperty("customerNote")
  private String customerNote = null;

  @JsonProperty("backofficeNote")
  private String backofficeNote = null;

  @JsonProperty("dateRequest")
  private OffsetDateTime dateRequest = null;

  @JsonProperty("dateCreation")
  private OffsetDateTime dateCreation = null;

  @JsonProperty("dateRequestConfirmed")
  private OffsetDateTime dateRequestConfirmed = null;

  @JsonProperty("total")
  private BigDecimal total = null;

  @JsonProperty("deliveryAddress")
  private Address deliveryAddress = null;

  @JsonProperty("shippingType")
  private ShippingType shippingType = null;

  public Order id(Integer id) {
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

  public Order customer(User customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public User getCustomer() {
    return customer;
  }

  public void setCustomer(User customer) {
    this.customer = customer;
  }

  public Order state(OrderState state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public OrderState getState() {
    return state;
  }

  public void setState(OrderState state) {
    this.state = state;
  }

  public Order shippingMethod(ShippingMethod shippingMethod) {
    this.shippingMethod = shippingMethod;
    return this;
  }

  /**
   * Get shippingMethod
   * @return shippingMethod
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ShippingMethod getShippingMethod() {
    return shippingMethod;
  }

  public void setShippingMethod(ShippingMethod shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public Order transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Get transactionId
   * @return transactionId
  **/
  @ApiModelProperty(value = "")
  
    public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Order customerNote(String customerNote) {
    this.customerNote = customerNote;
    return this;
  }

  /**
   * Get customerNote
   * @return customerNote
  **/
  @ApiModelProperty(value = "")
  
    public String getCustomerNote() {
    return customerNote;
  }

  public void setCustomerNote(String customerNote) {
    this.customerNote = customerNote;
  }

  public Order backofficeNote(String backofficeNote) {
    this.backofficeNote = backofficeNote;
    return this;
  }

  /**
   * Get backofficeNote
   * @return backofficeNote
  **/
  @ApiModelProperty(value = "")
  
    public String getBackofficeNote() {
    return backofficeNote;
  }

  public void setBackofficeNote(String backofficeNote) {
    this.backofficeNote = backofficeNote;
  }

  public Order dateRequest(OffsetDateTime dateRequest) {
    this.dateRequest = dateRequest;
    return this;
  }

  /**
   * Get dateRequest
   * @return dateRequest
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public OffsetDateTime getDateRequest() {
    return dateRequest;
  }

  public void setDateRequest(OffsetDateTime dateRequest) {
    this.dateRequest = dateRequest;
  }

  public Order dateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
    return this;
  }

  /**
   * Get dateCreation
   * @return dateCreation
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public OffsetDateTime getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
  }

  public Order dateRequestConfirmed(OffsetDateTime dateRequestConfirmed) {
    this.dateRequestConfirmed = dateRequestConfirmed;
    return this;
  }

  /**
   * Get dateRequestConfirmed
   * @return dateRequestConfirmed
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public OffsetDateTime getDateRequestConfirmed() {
    return dateRequestConfirmed;
  }

  public void setDateRequestConfirmed(OffsetDateTime dateRequestConfirmed) {
    this.dateRequestConfirmed = dateRequestConfirmed;
  }

  public Order total(BigDecimal total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Order deliveryAddress(Address deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
    return this;
  }

  /**
   * Get deliveryAddress
   * @return deliveryAddress
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Address getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(Address deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public Order shippingType(ShippingType shippingType) {
    this.shippingType = shippingType;
    return this;
  }

  /**
   * Get shippingType
   * @return shippingType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ShippingType getShippingType() {
    return shippingType;
  }

  public void setShippingType(ShippingType shippingType) {
    this.shippingType = shippingType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.customer, order.customer) &&
        Objects.equals(this.state, order.state) &&
        Objects.equals(this.shippingMethod, order.shippingMethod) &&
        Objects.equals(this.transactionId, order.transactionId) &&
        Objects.equals(this.customerNote, order.customerNote) &&
        Objects.equals(this.backofficeNote, order.backofficeNote) &&
        Objects.equals(this.dateRequest, order.dateRequest) &&
        Objects.equals(this.dateCreation, order.dateCreation) &&
        Objects.equals(this.dateRequestConfirmed, order.dateRequestConfirmed) &&
        Objects.equals(this.total, order.total) &&
        Objects.equals(this.deliveryAddress, order.deliveryAddress) &&
        Objects.equals(this.shippingType, order.shippingType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customer, state, shippingMethod, transactionId, customerNote, backofficeNote, dateRequest, dateCreation, dateRequestConfirmed, total, deliveryAddress, shippingType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    shippingMethod: ").append(toIndentedString(shippingMethod)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    customerNote: ").append(toIndentedString(customerNote)).append("\n");
    sb.append("    backofficeNote: ").append(toIndentedString(backofficeNote)).append("\n");
    sb.append("    dateRequest: ").append(toIndentedString(dateRequest)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    dateRequestConfirmed: ").append(toIndentedString(dateRequestConfirmed)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    deliveryAddress: ").append(toIndentedString(deliveryAddress)).append("\n");
    sb.append("    shippingType: ").append(toIndentedString(shippingType)).append("\n");
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
