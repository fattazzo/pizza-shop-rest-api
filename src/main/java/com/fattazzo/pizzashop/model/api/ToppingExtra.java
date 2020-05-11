package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Topping;
import com.fattazzo.pizzashop.model.api.VariationDough;
import com.fattazzo.pizzashop.model.api.VariationSize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Price of the topping based on a dough and size
 */
@ApiModel(description = "Price of the topping based on a dough and size")
@Validated
public class ToppingExtra   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("topping")
  private Topping topping = null;

  @JsonProperty("dough")
  private VariationDough dough = null;

  @JsonProperty("size")
  private VariationSize size = null;

  @JsonProperty("extra")
  private BigDecimal extra = null;

  @JsonProperty("enabled")
  private Boolean enabled = null;

  public ToppingExtra id(Integer id) {
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

  public ToppingExtra topping(Topping topping) {
    this.topping = topping;
    return this;
  }

  /**
   * Get topping
   * @return topping
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Topping getTopping() {
    return topping;
  }

  public void setTopping(Topping topping) {
    this.topping = topping;
  }

  public ToppingExtra dough(VariationDough dough) {
    this.dough = dough;
    return this;
  }

  /**
   * Get dough
   * @return dough
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public VariationDough getDough() {
    return dough;
  }

  public void setDough(VariationDough dough) {
    this.dough = dough;
  }

  public ToppingExtra size(VariationSize size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public VariationSize getSize() {
    return size;
  }

  public void setSize(VariationSize size) {
    this.size = size;
  }

  public ToppingExtra extra(BigDecimal extra) {
    this.extra = extra;
    return this;
  }

  /**
   * Get extra
   * @return extra
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public BigDecimal getExtra() {
    return extra;
  }

  public void setExtra(BigDecimal extra) {
    this.extra = extra;
  }

  public ToppingExtra enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  /**
   * Get enabled
   * @return enabled
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToppingExtra toppingExtra = (ToppingExtra) o;
    return Objects.equals(this.id, toppingExtra.id) &&
        Objects.equals(this.topping, toppingExtra.topping) &&
        Objects.equals(this.dough, toppingExtra.dough) &&
        Objects.equals(this.size, toppingExtra.size) &&
        Objects.equals(this.extra, toppingExtra.extra) &&
        Objects.equals(this.enabled, toppingExtra.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, topping, dough, size, extra, enabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ToppingExtra {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    topping: ").append(toIndentedString(topping)).append("\n");
    sb.append("    dough: ").append(toIndentedString(dough)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
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
