package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderLineRequest
 */
@Validated
public class OrderLineRequest   {
  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("customerNote")
  private String customerNote = null;

  @JsonProperty("total")
  private BigDecimal total = null;

  @JsonProperty("itemId")
  private Integer itemId = null;

  @JsonProperty("pizzaDoughId")
  private Integer pizzaDoughId = null;

  @JsonProperty("pizzaPriceId")
  private Integer pizzaPriceId = null;

  @JsonProperty("pizzaToppingExtrasId")
  @Valid
  private List<Integer> pizzaToppingExtrasId = null;

  @JsonProperty("productPriceId")
  private Integer productPriceId = null;

  public OrderLineRequest quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public OrderLineRequest customerNote(String customerNote) {
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

  public OrderLineRequest total(BigDecimal total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public OrderLineRequest itemId(Integer itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * Get itemId
   * @return itemId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public OrderLineRequest pizzaDoughId(Integer pizzaDoughId) {
    this.pizzaDoughId = pizzaDoughId;
    return this;
  }

  /**
   * Get pizzaDoughId
   * @return pizzaDoughId
  **/
  @ApiModelProperty(value = "")
  
    public Integer getPizzaDoughId() {
    return pizzaDoughId;
  }

  public void setPizzaDoughId(Integer pizzaDoughId) {
    this.pizzaDoughId = pizzaDoughId;
  }

  public OrderLineRequest pizzaPriceId(Integer pizzaPriceId) {
    this.pizzaPriceId = pizzaPriceId;
    return this;
  }

  /**
   * Get pizzaPriceId
   * @return pizzaPriceId
  **/
  @ApiModelProperty(value = "")
  
    public Integer getPizzaPriceId() {
    return pizzaPriceId;
  }

  public void setPizzaPriceId(Integer pizzaPriceId) {
    this.pizzaPriceId = pizzaPriceId;
  }

  public OrderLineRequest pizzaToppingExtrasId(List<Integer> pizzaToppingExtrasId) {
    this.pizzaToppingExtrasId = pizzaToppingExtrasId;
    return this;
  }

  public OrderLineRequest addPizzaToppingExtrasIdItem(Integer pizzaToppingExtrasIdItem) {
    if (this.pizzaToppingExtrasId == null) {
      this.pizzaToppingExtrasId = new ArrayList<>();
    }
    this.pizzaToppingExtrasId.add(pizzaToppingExtrasIdItem);
    return this;
  }

  /**
   * Get pizzaToppingExtrasId
   * @return pizzaToppingExtrasId
  **/
  @ApiModelProperty(value = "")
  
    public List<Integer> getPizzaToppingExtrasId() {
    return pizzaToppingExtrasId;
  }

  public void setPizzaToppingExtrasId(List<Integer> pizzaToppingExtrasId) {
    this.pizzaToppingExtrasId = pizzaToppingExtrasId;
  }

  public OrderLineRequest productPriceId(Integer productPriceId) {
    this.productPriceId = productPriceId;
    return this;
  }

  /**
   * Get productPriceId
   * @return productPriceId
  **/
  @ApiModelProperty(value = "")
  
    public Integer getProductPriceId() {
    return productPriceId;
  }

  public void setProductPriceId(Integer productPriceId) {
    this.productPriceId = productPriceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderLineRequest orderLineRequest = (OrderLineRequest) o;
    return Objects.equals(this.quantity, orderLineRequest.quantity) &&
        Objects.equals(this.customerNote, orderLineRequest.customerNote) &&
        Objects.equals(this.total, orderLineRequest.total) &&
        Objects.equals(this.itemId, orderLineRequest.itemId) &&
        Objects.equals(this.pizzaDoughId, orderLineRequest.pizzaDoughId) &&
        Objects.equals(this.pizzaPriceId, orderLineRequest.pizzaPriceId) &&
        Objects.equals(this.pizzaToppingExtrasId, orderLineRequest.pizzaToppingExtrasId) &&
        Objects.equals(this.productPriceId, orderLineRequest.productPriceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantity, customerNote, total, itemId, pizzaDoughId, pizzaPriceId, pizzaToppingExtrasId, productPriceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderLineRequest {\n");
    
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    customerNote: ").append(toIndentedString(customerNote)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    pizzaDoughId: ").append(toIndentedString(pizzaDoughId)).append("\n");
    sb.append("    pizzaPriceId: ").append(toIndentedString(pizzaPriceId)).append("\n");
    sb.append("    pizzaToppingExtrasId: ").append(toIndentedString(pizzaToppingExtrasId)).append("\n");
    sb.append("    productPriceId: ").append(toIndentedString(productPriceId)).append("\n");
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
