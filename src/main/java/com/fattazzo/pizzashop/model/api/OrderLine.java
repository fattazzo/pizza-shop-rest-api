package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Item;
import com.fattazzo.pizzashop.model.api.ItemPizzaPrice;
import com.fattazzo.pizzashop.model.api.ItemProductPrice;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.model.api.VariationDough;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderLine
 */
@Validated
public class OrderLine   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("item")
  private Item item = null;

  @JsonProperty("productPrice")
  private ItemProductPrice productPrice = null;

  @JsonProperty("pizzaPrice")
  private ItemPizzaPrice pizzaPrice = null;

  @JsonProperty("pizzaDough")
  private VariationDough pizzaDough = null;

  @JsonProperty("pizzaToppingExtras")
  @Valid
  private List<ToppingExtra> pizzaToppingExtras = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("customerNote")
  private String customerNote = null;

  @JsonProperty("total")
  private BigDecimal total = null;

  public OrderLine id(Integer id) {
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

  public OrderLine item(Item item) {
    this.item = item;
    return this;
  }

  /**
   * Get item
   * @return item
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public OrderLine productPrice(ItemProductPrice productPrice) {
    this.productPrice = productPrice;
    return this;
  }

  /**
   * Get productPrice
   * @return productPrice
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ItemProductPrice getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(ItemProductPrice productPrice) {
    this.productPrice = productPrice;
  }

  public OrderLine pizzaPrice(ItemPizzaPrice pizzaPrice) {
    this.pizzaPrice = pizzaPrice;
    return this;
  }

  /**
   * Get pizzaPrice
   * @return pizzaPrice
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ItemPizzaPrice getPizzaPrice() {
    return pizzaPrice;
  }

  public void setPizzaPrice(ItemPizzaPrice pizzaPrice) {
    this.pizzaPrice = pizzaPrice;
  }

  public OrderLine pizzaDough(VariationDough pizzaDough) {
    this.pizzaDough = pizzaDough;
    return this;
  }

  /**
   * Get pizzaDough
   * @return pizzaDough
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public VariationDough getPizzaDough() {
    return pizzaDough;
  }

  public void setPizzaDough(VariationDough pizzaDough) {
    this.pizzaDough = pizzaDough;
  }

  public OrderLine pizzaToppingExtras(List<ToppingExtra> pizzaToppingExtras) {
    this.pizzaToppingExtras = pizzaToppingExtras;
    return this;
  }

  public OrderLine addPizzaToppingExtrasItem(ToppingExtra pizzaToppingExtrasItem) {
    if (this.pizzaToppingExtras == null) {
      this.pizzaToppingExtras = new ArrayList<>();
    }
    this.pizzaToppingExtras.add(pizzaToppingExtrasItem);
    return this;
  }

  /**
   * Get pizzaToppingExtras
   * @return pizzaToppingExtras
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ToppingExtra> getPizzaToppingExtras() {
    return pizzaToppingExtras;
  }

  public void setPizzaToppingExtras(List<ToppingExtra> pizzaToppingExtras) {
    this.pizzaToppingExtras = pizzaToppingExtras;
  }

  public OrderLine quantity(Integer quantity) {
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

  public OrderLine customerNote(String customerNote) {
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

  public OrderLine total(BigDecimal total) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderLine orderLine = (OrderLine) o;
    return Objects.equals(this.id, orderLine.id) &&
        Objects.equals(this.item, orderLine.item) &&
        Objects.equals(this.productPrice, orderLine.productPrice) &&
        Objects.equals(this.pizzaPrice, orderLine.pizzaPrice) &&
        Objects.equals(this.pizzaDough, orderLine.pizzaDough) &&
        Objects.equals(this.pizzaToppingExtras, orderLine.pizzaToppingExtras) &&
        Objects.equals(this.quantity, orderLine.quantity) &&
        Objects.equals(this.customerNote, orderLine.customerNote) &&
        Objects.equals(this.total, orderLine.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, item, productPrice, pizzaPrice, pizzaDough, pizzaToppingExtras, quantity, customerNote, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderLine {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    item: ").append(toIndentedString(item)).append("\n");
    sb.append("    productPrice: ").append(toIndentedString(productPrice)).append("\n");
    sb.append("    pizzaPrice: ").append(toIndentedString(pizzaPrice)).append("\n");
    sb.append("    pizzaDough: ").append(toIndentedString(pizzaDough)).append("\n");
    sb.append("    pizzaToppingExtras: ").append(toIndentedString(pizzaToppingExtras)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    customerNote: ").append(toIndentedString(customerNote)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
