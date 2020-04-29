package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Dough;
import com.fattazzo.pizzashop.model.api.ProductCategory;
import com.fattazzo.pizzashop.model.api.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProductCategoryDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-28T14:20:54.148Z[GMT]")
public class ProductCategoryDetails extends ProductCategory  {
  @JsonProperty("doughs")
  @Valid
  private List<Dough> doughs = null;

  @JsonProperty("sizes")
  @Valid
  private List<Size> sizes = null;

  public ProductCategoryDetails doughs(List<Dough> doughs) {
    this.doughs = doughs;
    return this;
  }

  public ProductCategoryDetails addDoughsItem(Dough doughsItem) {
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
  @ApiModelProperty(value = "")
      @Valid
    public List<Dough> getDoughs() {
    return doughs;
  }

  public void setDoughs(List<Dough> doughs) {
    this.doughs = doughs;
  }

  public ProductCategoryDetails sizes(List<Size> sizes) {
    this.sizes = sizes;
    return this;
  }

  public ProductCategoryDetails addSizesItem(Size sizesItem) {
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
  @ApiModelProperty(value = "")
      @Valid
    public List<Size> getSizes() {
    return sizes;
  }

  public void setSizes(List<Size> sizes) {
    this.sizes = sizes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductCategoryDetails productCategoryDetails = (ProductCategoryDetails) o;
    return Objects.equals(this.doughs, productCategoryDetails.doughs) &&
        Objects.equals(this.sizes, productCategoryDetails.sizes) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(doughs, sizes, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductCategoryDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    doughs: ").append(toIndentedString(doughs)).append("\n");
    sb.append("    sizes: ").append(toIndentedString(sizes)).append("\n");
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
