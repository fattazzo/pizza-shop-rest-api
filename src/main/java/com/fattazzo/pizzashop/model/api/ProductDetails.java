package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Product;
import com.fattazzo.pizzashop.model.api.ProductCategory;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.model.api.VariationDough;
import com.fattazzo.pizzashop.model.api.VariationSize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDetails
 */

public class ProductDetails extends Product  {
  @JsonProperty("doughs")
  
  private List<VariationDough> doughs = null;

  @JsonProperty("sizes")
  
  private List<VariationSize> sizes = null;

  @JsonProperty("toppingExtras")
  
  private List<ToppingExtra> toppingExtras = null;

  public ProductDetails doughs(List<VariationDough> doughs) {
    this.doughs = doughs;
    return this;
  }

  public ProductDetails addDoughsItem(VariationDough doughsItem) {
    if (this.doughs == null) {
      this.doughs = new ArrayList<VariationDough>();
    }
    this.doughs.add(doughsItem);
    return this;
  }

  /**
   * Get doughs
   * @return doughs
  **/
  @ApiModelProperty(readOnly = true, value = "")
    public List<VariationDough> getDoughs() {
    return doughs;
  }

  public void setDoughs(List<VariationDough> doughs) {
    this.doughs = doughs;
  }

  public ProductDetails sizes(List<VariationSize> sizes) {
    this.sizes = sizes;
    return this;
  }

  public ProductDetails addSizesItem(VariationSize sizesItem) {
    if (this.sizes == null) {
      this.sizes = new ArrayList<VariationSize>();
    }
    this.sizes.add(sizesItem);
    return this;
  }

  /**
   * Get sizes
   * @return sizes
  **/
  @ApiModelProperty(readOnly = true, value = "")
    public List<VariationSize> getSizes() {
    return sizes;
  }

  public void setSizes(List<VariationSize> sizes) {
    this.sizes = sizes;
  }

  public ProductDetails toppingExtras(List<ToppingExtra> toppingExtras) {
    this.toppingExtras = toppingExtras;
    return this;
  }

  public ProductDetails addToppingExtrasItem(ToppingExtra toppingExtrasItem) {
    if (this.toppingExtras == null) {
      this.toppingExtras = new ArrayList<ToppingExtra>();
    }
    this.toppingExtras.add(toppingExtrasItem);
    return this;
  }

  /**
   * Get toppingExtras
   * @return toppingExtras
  **/
  @ApiModelProperty(readOnly = true, value = "")
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
    ProductDetails productDetails = (ProductDetails) o;
    return Objects.equals(this.doughs, productDetails.doughs) &&
        Objects.equals(this.sizes, productDetails.sizes) &&
        Objects.equals(this.toppingExtras, productDetails.toppingExtras) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(doughs, sizes, toppingExtras, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
