package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Category;
import com.fattazzo.pizzashop.model.api.ItemType;
import com.fattazzo.pizzashop.model.api.VariationDough;
import com.fattazzo.pizzashop.model.api.VariationSize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CategoryDetails
 */
@Validated
public class CategoryDetails extends Category  {
  @JsonProperty("doughs")
  @Valid
  private List<VariationDough> doughs = null;

  @JsonProperty("sizes")
  @Valid
  private List<VariationSize> sizes = null;

  public CategoryDetails doughs(List<VariationDough> doughs) {
    this.doughs = doughs;
    return this;
  }

  public CategoryDetails addDoughsItem(VariationDough doughsItem) {
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
  @ApiModelProperty(value = "")
      @Valid
    public List<VariationDough> getDoughs() {
    return doughs;
  }

  public void setDoughs(List<VariationDough> doughs) {
    this.doughs = doughs;
  }

  public CategoryDetails sizes(List<VariationSize> sizes) {
    this.sizes = sizes;
    return this;
  }

  public CategoryDetails addSizesItem(VariationSize sizesItem) {
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
  @ApiModelProperty(value = "")
      @Valid
    public List<VariationSize> getSizes() {
    return sizes;
  }

  public void setSizes(List<VariationSize> sizes) {
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
    CategoryDetails categoryDetails = (CategoryDetails) o;
    return Objects.equals(this.doughs, categoryDetails.doughs) &&
        Objects.equals(this.sizes, categoryDetails.sizes) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(doughs, sizes, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryDetails {\n");
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
