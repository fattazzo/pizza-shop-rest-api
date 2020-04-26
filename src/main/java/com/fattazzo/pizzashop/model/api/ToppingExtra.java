package com.fattazzo.pizzashop.model.api;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Price of the topping based on a dough and size
 */
@ApiModel(description = "Price of the topping based on a dough and size")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-26T14:55:34.957Z[GMT]")
public class ToppingExtra   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("topping")
  private Topping topping = null;

  @JsonProperty("dough")
  private Dough dough = null;

  @JsonProperty("size")
  private Size size = null;

  @JsonProperty("extra")
  private BigDecimal extra = null;

  @JsonProperty("enabled")
  private Boolean enabled = null;

  @JsonProperty("toppingEnabled")
  private Boolean toppingEnabled = null;

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

  public ToppingExtra dough(Dough dough) {
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
    public Dough getDough() {
    return dough;
  }

  public void setDough(Dough dough) {
    this.dough = dough;
  }

  public ToppingExtra size(Size size) {
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
    public Size getSize() {
    return size;
  }

  public void setSize(Size size) {
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

  public ToppingExtra toppingEnabled(Boolean toppingEnabled) {
    this.toppingEnabled = toppingEnabled;
    return this;
  }

  /**
   * Get toppingEnabled
   * @return toppingEnabled
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Boolean isToppingEnabled() {
    return toppingEnabled;
  }

  public void setToppingEnabled(Boolean toppingEnabled) {
    this.toppingEnabled = toppingEnabled;
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
        Objects.equals(this.enabled, toppingExtra.enabled) &&
        Objects.equals(this.toppingEnabled, toppingExtra.toppingEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, topping, dough, size, extra, enabled, toppingEnabled);
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
    sb.append("    toppingEnabled: ").append(toIndentedString(toppingEnabled)).append("\n");
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
