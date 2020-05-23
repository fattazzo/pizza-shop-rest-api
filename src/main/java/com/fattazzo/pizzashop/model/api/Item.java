package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Item
 */
@Validated
public class Item   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("enabled")
  private Boolean enabled = null;

  @JsonProperty("category")
  private Category category = null;

  @JsonProperty("imageUrl")
  private String imageUrl = null;

  @JsonProperty("availablePrices")
  @Valid
  private List<BigDecimal> availablePrices = null;

  public Item id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Item name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Item description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Item enabled(Boolean enabled) {
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

  public Item category(Category category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Item imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
  **/
  @ApiModelProperty(value = "")
  
    public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Item availablePrices(List<BigDecimal> availablePrices) {
    this.availablePrices = availablePrices;
    return this;
  }

  public Item addAvailablePricesItem(BigDecimal availablePricesItem) {
    if (this.availablePrices == null) {
      this.availablePrices = new ArrayList<>();
    }
    this.availablePrices.add(availablePricesItem);
    return this;
  }

  /**
   * Get availablePrices
   * @return availablePrices
  **/
  @ApiModelProperty(readOnly = true, value = "")
      @Valid
    public List<BigDecimal> getAvailablePrices() {
    return availablePrices;
  }

  public void setAvailablePrices(List<BigDecimal> availablePrices) {
    this.availablePrices = availablePrices;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.id, item.id) &&
        Objects.equals(this.name, item.name) &&
        Objects.equals(this.description, item.description) &&
        Objects.equals(this.enabled, item.enabled) &&
        Objects.equals(this.category, item.category) &&
        Objects.equals(this.imageUrl, item.imageUrl) &&
        Objects.equals(this.availablePrices, item.availablePrices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, enabled, category, imageUrl, availablePrices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    availablePrices: ").append(toIndentedString(availablePrices)).append("\n");
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
