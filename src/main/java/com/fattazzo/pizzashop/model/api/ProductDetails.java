package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Dough;
import com.fattazzo.pizzashop.model.api.Product;
import com.fattazzo.pizzashop.model.api.ProductCategory;
import com.fattazzo.pizzashop.model.api.Size;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProductDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-28T14:20:54.148Z[GMT]")
public class ProductDetails extends Product  {
  @JsonProperty("doughs")
  @Valid
  private List<Dough> doughs = null;

  @JsonProperty("sizes")
  @Valid
  private List<Size> sizes = null;

  @JsonProperty("toppingExtras")
  @Valid
  private List<ToppingExtra> toppingExtras = null;

  public ProductDetails doughs(List<Dough> doughs) {
    this.doughs = doughs;
    return this;
  }

  public ProductDetails addDoughsItem(Dough doughsItem) {
    if (this.doughs == null) {
      this.doughs = new ArrayList<Dough>();
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
    public List<Dough> getDoughs() {
    return doughs;
  }

  public void setDoughs(List<Dough> doughs) {
    this.doughs = doughs;
  }

  public ProductDetails sizes(List<Size> sizes) {
    this.sizes = sizes;
    return this;
  }

  public ProductDetails addSizesItem(Size sizesItem) {
    if (this.sizes == null) {
      this.sizes = new ArrayList<Size>();
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
    public List<Size> getSizes() {
    return sizes;
  }

  public void setSizes(List<Size> sizes) {
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
