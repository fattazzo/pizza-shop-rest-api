package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.ShippingMethodType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ShippingMethod
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-28T06:15:14.926Z[GMT]")
public class ShippingMethod   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("type")
  private ShippingMethodType type = null;

  @JsonProperty("enabled")
  private Boolean enabled = null;

  public ShippingMethod id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Method ID
   * @return id
  **/
  @ApiModelProperty(value = "Method ID")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ShippingMethod title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Shipping method title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "Shipping method title")
      @NotNull

    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ShippingMethod description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Shipping method description
   * @return description
  **/
  @ApiModelProperty(value = "Shipping method description")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ShippingMethod type(ShippingMethodType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ShippingMethodType getType() {
    return type;
  }

  public void setType(ShippingMethodType type) {
    this.type = type;
  }

  public ShippingMethod enabled(Boolean enabled) {
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
    ShippingMethod shippingMethod = (ShippingMethod) o;
    return Objects.equals(this.id, shippingMethod.id) &&
        Objects.equals(this.title, shippingMethod.title) &&
        Objects.equals(this.description, shippingMethod.description) &&
        Objects.equals(this.type, shippingMethod.type) &&
        Objects.equals(this.enabled, shippingMethod.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, type, enabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ShippingMethod {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
