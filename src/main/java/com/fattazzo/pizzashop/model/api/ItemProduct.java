package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Category;
import com.fattazzo.pizzashop.model.api.Item;
import com.fattazzo.pizzashop.model.api.ItemProductPrice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ItemProduct
 */
@Validated
public class ItemProduct extends Item  {
  @JsonProperty("prices")
  @Valid
  private List<ItemProductPrice> prices = null;

  public ItemProduct prices(List<ItemProductPrice> prices) {
    this.prices = prices;
    return this;
  }

  public ItemProduct addPricesItem(ItemProductPrice pricesItem) {
    if (this.prices == null) {
      this.prices = new ArrayList<ItemProductPrice>();
    }
    this.prices.add(pricesItem);
    return this;
  }

  /**
   * Get prices
   * @return prices
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ItemProductPrice> getPrices() {
    return prices;
  }

  public void setPrices(List<ItemProductPrice> prices) {
    this.prices = prices;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemProduct itemProduct = (ItemProduct) o;
    return Objects.equals(this.prices, itemProduct.prices) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prices, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemProduct {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    prices: ").append(toIndentedString(prices)).append("\n");
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
