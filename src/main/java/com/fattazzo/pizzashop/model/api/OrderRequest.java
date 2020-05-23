package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Address;
import com.fattazzo.pizzashop.model.api.OrderLineRequest;
import com.fattazzo.pizzashop.model.api.ShippingType;
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
 * OrderRequest
 */
@Validated
public class OrderRequest   {
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

  @JsonProperty("shippingMethodId")
  private Integer shippingMethodId = null;

  @JsonProperty("lines")
  @Valid
  private List<OrderLineRequest> lines = new ArrayList<>();

  @JsonProperty("shippingType")
  private ShippingType shippingType = null;

  public OrderRequest transactionId(String transactionId) {
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

  public OrderRequest customerNote(String customerNote) {
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

  public OrderRequest backofficeNote(String backofficeNote) {
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

  public OrderRequest dateRequest(OffsetDateTime dateRequest) {
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

  public OrderRequest dateCreation(OffsetDateTime dateCreation) {
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

  public OrderRequest dateRequestConfirmed(OffsetDateTime dateRequestConfirmed) {
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

  public OrderRequest total(BigDecimal total) {
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

  public OrderRequest deliveryAddress(Address deliveryAddress) {
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

  public OrderRequest shippingMethodId(Integer shippingMethodId) {
    this.shippingMethodId = shippingMethodId;
    return this;
  }

  /**
   * Get shippingMethodId
   * @return shippingMethodId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getShippingMethodId() {
    return shippingMethodId;
  }

  public void setShippingMethodId(Integer shippingMethodId) {
    this.shippingMethodId = shippingMethodId;
  }

  public OrderRequest lines(List<OrderLineRequest> lines) {
    this.lines = lines;
    return this;
  }

  public OrderRequest addLinesItem(OrderLineRequest linesItem) {
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
    public List<OrderLineRequest> getLines() {
    return lines;
  }

  public void setLines(List<OrderLineRequest> lines) {
    this.lines = lines;
  }

  public OrderRequest shippingType(ShippingType shippingType) {
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
    OrderRequest orderRequest = (OrderRequest) o;
    return Objects.equals(this.transactionId, orderRequest.transactionId) &&
        Objects.equals(this.customerNote, orderRequest.customerNote) &&
        Objects.equals(this.backofficeNote, orderRequest.backofficeNote) &&
        Objects.equals(this.dateRequest, orderRequest.dateRequest) &&
        Objects.equals(this.dateCreation, orderRequest.dateCreation) &&
        Objects.equals(this.dateRequestConfirmed, orderRequest.dateRequestConfirmed) &&
        Objects.equals(this.total, orderRequest.total) &&
        Objects.equals(this.deliveryAddress, orderRequest.deliveryAddress) &&
        Objects.equals(this.shippingMethodId, orderRequest.shippingMethodId) &&
        Objects.equals(this.lines, orderRequest.lines) &&
        Objects.equals(this.shippingType, orderRequest.shippingType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, customerNote, backofficeNote, dateRequest, dateCreation, dateRequestConfirmed, total, deliveryAddress, shippingMethodId, lines, shippingType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderRequest {\n");
    
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    customerNote: ").append(toIndentedString(customerNote)).append("\n");
    sb.append("    backofficeNote: ").append(toIndentedString(backofficeNote)).append("\n");
    sb.append("    dateRequest: ").append(toIndentedString(dateRequest)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    dateRequestConfirmed: ").append(toIndentedString(dateRequestConfirmed)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    deliveryAddress: ").append(toIndentedString(deliveryAddress)).append("\n");
    sb.append("    shippingMethodId: ").append(toIndentedString(shippingMethodId)).append("\n");
    sb.append("    lines: ").append(toIndentedString(lines)).append("\n");
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
