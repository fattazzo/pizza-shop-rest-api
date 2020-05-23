package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.VariationSize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ItemPizzaPrice
 */
@Validated
public class ItemPizzaPrice   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("price")
  private BigDecimal price = null;

  @JsonProperty("variationSize")
  private VariationSize variationSize = null;

  public ItemPizzaPrice id(Integer id) {
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

  public ItemPizzaPrice price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public ItemPizzaPrice variationSize(VariationSize variationSize) {
    this.variationSize = variationSize;
    return this;
  }

  /**
   * Get variationSize
   * @return variationSize
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public VariationSize getVariationSize() {
    return variationSize;
  }

  public void setVariationSize(VariationSize variationSize) {
    this.variationSize = variationSize;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemPizzaPrice itemPizzaPrice = (ItemPizzaPrice) o;
    return Objects.equals(this.id, itemPizzaPrice.id) &&
        Objects.equals(this.price, itemPizzaPrice.price) &&
        Objects.equals(this.variationSize, itemPizzaPrice.variationSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, variationSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemPizzaPrice {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    variationSize: ").append(toIndentedString(variationSize)).append("\n");
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
