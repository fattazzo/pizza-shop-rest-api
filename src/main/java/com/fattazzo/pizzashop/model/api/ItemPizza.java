package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Category;
import com.fattazzo.pizzashop.model.api.Item;
import com.fattazzo.pizzashop.model.api.ItemPizzaPrice;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.model.api.VariationDough;
import com.fattazzo.pizzashop.model.api.VariationSize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ItemPizza
 */
@Validated
public class ItemPizza extends Item  {
  @JsonProperty("prices")
  @Valid
  private List<ItemPizzaPrice> prices = null;

  @JsonProperty("doughs")
  @Valid
  private List<VariationDough> doughs = null;

  @JsonProperty("sizes")
  @Valid
  private List<VariationSize> sizes = null;

  @JsonProperty("toppingExtras")
  @Valid
  private List<ToppingExtra> toppingExtras = null;

  public ItemPizza prices(List<ItemPizzaPrice> prices) {
    this.prices = prices;
    return this;
  }

  public ItemPizza addPricesItem(ItemPizzaPrice pricesItem) {
    if (this.prices == null) {
      this.prices = new ArrayList<>();
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
    public List<ItemPizzaPrice> getPrices() {
    return prices;
  }

  public void setPrices(List<ItemPizzaPrice> prices) {
    this.prices = prices;
  }

  public ItemPizza doughs(List<VariationDough> doughs) {
    this.doughs = doughs;
    return this;
  }

  public ItemPizza addDoughsItem(VariationDough doughsItem) {
    if (this.doughs == null) {
      this.doughs = new ArrayList<>();
    }
    this.doughs.add(doughsItem);
    return this;
  }

  /**
   * Get doughs
   * @return doughs
  **/
  @ApiModelProperty(readOnly = true, value = "")
      @Valid
    public List<VariationDough> getDoughs() {
    return doughs;
  }

  public void setDoughs(List<VariationDough> doughs) {
    this.doughs = doughs;
  }

  public ItemPizza sizes(List<VariationSize> sizes) {
    this.sizes = sizes;
    return this;
  }

  public ItemPizza addSizesItem(VariationSize sizesItem) {
    if (this.sizes == null) {
      this.sizes = new ArrayList<>();
    }
    this.sizes.add(sizesItem);
    return this;
  }

  /**
   * Get sizes
   * @return sizes
  **/
  @ApiModelProperty(readOnly = true, value = "")
      @Valid
    public List<VariationSize> getSizes() {
    return sizes;
  }

  public void setSizes(List<VariationSize> sizes) {
    this.sizes = sizes;
  }

  public ItemPizza toppingExtras(List<ToppingExtra> toppingExtras) {
    this.toppingExtras = toppingExtras;
    return this;
  }

  public ItemPizza addToppingExtrasItem(ToppingExtra toppingExtrasItem) {
    if (this.toppingExtras == null) {
      this.toppingExtras = new ArrayList<>();
    }
    this.toppingExtras.add(toppingExtrasItem);
    return this;
  }

  /**
   * Get toppingExtras
   * @return toppingExtras
  **/
  @ApiModelProperty(readOnly = true, value = "")
      @Valid
    public List<ToppingExtra> getToppingExtras() {
    return toppingExtras;
  }

  public void setToppingExtras(List<ToppingExtra> toppingExtras) {
    this.toppingExtras = toppingExtras;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemPizza itemPizza = (ItemPizza) o;
    return Objects.equals(this.prices, itemPizza.prices) &&
        Objects.equals(this.doughs, itemPizza.doughs) &&
        Objects.equals(this.sizes, itemPizza.sizes) &&
        Objects.equals(this.toppingExtras, itemPizza.toppingExtras) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prices, doughs, sizes, toppingExtras, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemPizza {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    prices: ").append(toIndentedString(prices)).append("\n");
    sb.append("    doughs: ").append(toIndentedString(doughs)).append("\n");
    sb.append("    sizes: ").append(toIndentedString(sizes)).append("\n");
    sb.append("    toppingExtras: ").append(toIndentedString(toppingExtras)).append("\n");
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
