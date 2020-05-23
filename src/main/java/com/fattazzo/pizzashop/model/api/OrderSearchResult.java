package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.OrderState;
import com.fattazzo.pizzashop.model.api.ShippingType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderSearchResult
 */
@Validated
public class OrderSearchResult   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("state")
  private OrderState state = null;

  @JsonProperty("shippingMethod")
  private String shippingMethod = null;

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

  @JsonProperty("shippingType")
  private ShippingType shippingType = null;

  @JsonProperty("customerUserName")
  private String customerUserName = null;

  @JsonProperty("deliveryAddressNumber")
  private String deliveryAddressNumber = null;

  @JsonProperty("deliveryAddressStreet")
  private String deliveryAddressStreet = null;

  @JsonProperty("deliveryAddressPlace")
  private String deliveryAddressPlace = null;

  @JsonProperty("deliveryAddressPostalCode")
  private String deliveryAddressPostalCode = null;

  public OrderSearchResult id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OrderSearchResult state(OrderState state) {
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

  public OrderSearchResult shippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
    return this;
  }

  /**
   * Get shippingMethod
   * @return shippingMethod
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getShippingMethod() {
    return shippingMethod;
  }

  public void setShippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public OrderSearchResult transactionId(String transactionId) {
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

  public OrderSearchResult customerNote(String customerNote) {
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

  public OrderSearchResult backofficeNote(String backofficeNote) {
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

  public OrderSearchResult dateRequest(OffsetDateTime dateRequest) {
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

  public OrderSearchResult dateCreation(OffsetDateTime dateCreation) {
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

  public OrderSearchResult dateRequestConfirmed(OffsetDateTime dateRequestConfirmed) {
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

  public OrderSearchResult total(BigDecimal total) {
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

  public OrderSearchResult shippingType(ShippingType shippingType) {
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

  public OrderSearchResult customerUserName(String customerUserName) {
    this.customerUserName = customerUserName;
    return this;
  }

  /**
   * Get customerUserName
   * @return customerUserName
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCustomerUserName() {
    return customerUserName;
  }

  public void setCustomerUserName(String customerUserName) {
    this.customerUserName = customerUserName;
  }

  public OrderSearchResult deliveryAddressNumber(String deliveryAddressNumber) {
    this.deliveryAddressNumber = deliveryAddressNumber;
    return this;
  }

  /**
   * Get deliveryAddressNumber
   * @return deliveryAddressNumber
  **/
  @ApiModelProperty(value = "")
  
    public String getDeliveryAddressNumber() {
    return deliveryAddressNumber;
  }

  public void setDeliveryAddressNumber(String deliveryAddressNumber) {
    this.deliveryAddressNumber = deliveryAddressNumber;
  }

  public OrderSearchResult deliveryAddressStreet(String deliveryAddressStreet) {
    this.deliveryAddressStreet = deliveryAddressStreet;
    return this;
  }

  /**
   * Get deliveryAddressStreet
   * @return deliveryAddressStreet
  **/
  @ApiModelProperty(value = "")
  
    public String getDeliveryAddressStreet() {
    return deliveryAddressStreet;
  }

  public void setDeliveryAddressStreet(String deliveryAddressStreet) {
    this.deliveryAddressStreet = deliveryAddressStreet;
  }

  public OrderSearchResult deliveryAddressPlace(String deliveryAddressPlace) {
    this.deliveryAddressPlace = deliveryAddressPlace;
    return this;
  }

  /**
   * Get deliveryAddressPlace
   * @return deliveryAddressPlace
  **/
  @ApiModelProperty(value = "")
  
    public String getDeliveryAddressPlace() {
    return deliveryAddressPlace;
  }

  public void setDeliveryAddressPlace(String deliveryAddressPlace) {
    this.deliveryAddressPlace = deliveryAddressPlace;
  }

  public OrderSearchResult deliveryAddressPostalCode(String deliveryAddressPostalCode) {
    this.deliveryAddressPostalCode = deliveryAddressPostalCode;
    return this;
  }

  /**
   * Get deliveryAddressPostalCode
   * @return deliveryAddressPostalCode
  **/
  @ApiModelProperty(value = "")
  
    public String getDeliveryAddressPostalCode() {
    return deliveryAddressPostalCode;
  }

  public void setDeliveryAddressPostalCode(String deliveryAddressPostalCode) {
    this.deliveryAddressPostalCode = deliveryAddressPostalCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderSearchResult orderSearchResult = (OrderSearchResult) o;
    return Objects.equals(this.id, orderSearchResult.id) &&
        Objects.equals(this.state, orderSearchResult.state) &&
        Objects.equals(this.shippingMethod, orderSearchResult.shippingMethod) &&
        Objects.equals(this.transactionId, orderSearchResult.transactionId) &&
        Objects.equals(this.customerNote, orderSearchResult.customerNote) &&
        Objects.equals(this.backofficeNote, orderSearchResult.backofficeNote) &&
        Objects.equals(this.dateRequest, orderSearchResult.dateRequest) &&
        Objects.equals(this.dateCreation, orderSearchResult.dateCreation) &&
        Objects.equals(this.dateRequestConfirmed, orderSearchResult.dateRequestConfirmed) &&
        Objects.equals(this.total, orderSearchResult.total) &&
        Objects.equals(this.shippingType, orderSearchResult.shippingType) &&
        Objects.equals(this.customerUserName, orderSearchResult.customerUserName) &&
        Objects.equals(this.deliveryAddressNumber, orderSearchResult.deliveryAddressNumber) &&
        Objects.equals(this.deliveryAddressStreet, orderSearchResult.deliveryAddressStreet) &&
        Objects.equals(this.deliveryAddressPlace, orderSearchResult.deliveryAddressPlace) &&
        Objects.equals(this.deliveryAddressPostalCode, orderSearchResult.deliveryAddressPostalCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, state, shippingMethod, transactionId, customerNote, backofficeNote, dateRequest, dateCreation, dateRequestConfirmed, total, shippingType, customerUserName, deliveryAddressNumber, deliveryAddressStreet, deliveryAddressPlace, deliveryAddressPostalCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderSearchResult {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    shippingMethod: ").append(toIndentedString(shippingMethod)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    customerNote: ").append(toIndentedString(customerNote)).append("\n");
    sb.append("    backofficeNote: ").append(toIndentedString(backofficeNote)).append("\n");
    sb.append("    dateRequest: ").append(toIndentedString(dateRequest)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    dateRequestConfirmed: ").append(toIndentedString(dateRequestConfirmed)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    shippingType: ").append(toIndentedString(shippingType)).append("\n");
    sb.append("    customerUserName: ").append(toIndentedString(customerUserName)).append("\n");
    sb.append("    deliveryAddressNumber: ").append(toIndentedString(deliveryAddressNumber)).append("\n");
    sb.append("    deliveryAddressStreet: ").append(toIndentedString(deliveryAddressStreet)).append("\n");
    sb.append("    deliveryAddressPlace: ").append(toIndentedString(deliveryAddressPlace)).append("\n");
    sb.append("    deliveryAddressPostalCode: ").append(toIndentedString(deliveryAddressPostalCode)).append("\n");
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
