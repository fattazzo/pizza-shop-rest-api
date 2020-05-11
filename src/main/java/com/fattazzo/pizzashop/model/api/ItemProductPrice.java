package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.VariationProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ItemProductPrice
 */
@Validated
public class ItemProductPrice   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("variation")
  private VariationProduct variation = null;

  @JsonProperty("price")
  private BigDecimal price = null;

  public ItemProductPrice id(Integer id) {
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

  public ItemProductPrice variation(VariationProduct variation) {
    this.variation = variation;
    return this;
  }

  /**
   * Get variation
   * @return variation
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public VariationProduct getVariation() {
    return variation;
  }

  public void setVariation(VariationProduct variation) {
    this.variation = variation;
  }

  public ItemProductPrice price(BigDecimal price) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemProductPrice itemProductPrice = (ItemProductPrice) o;
    return Objects.equals(this.id, itemProductPrice.id) &&
        Objects.equals(this.variation, itemProductPrice.variation) &&
        Objects.equals(this.price, itemProductPrice.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, variation, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemProductPrice {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    variation: ").append(toIndentedString(variation)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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
