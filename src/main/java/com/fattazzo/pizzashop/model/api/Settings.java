package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Settings
 */
@Validated
public class Settings   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("currencySymbol")
  private String currencySymbol = null;

  @JsonProperty("currencyDecimals")
  private Integer currencyDecimals = null;

  @JsonProperty("minOrderRequestMinutes")
  private Integer minOrderRequestMinutes = null;

  @JsonProperty("processingOrdersMinutesPartition")
  private Integer processingOrdersMinutesPartition = null;

  public Settings id(Integer id) {
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

  public Settings currencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
    return this;
  }

  /**
   * Currency symbol
   * @return currencySymbol
  **/
  @ApiModelProperty(required = true, value = "Currency symbol")
      @NotNull

  @Size(min=1,max=5)   public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public Settings currencyDecimals(Integer currencyDecimals) {
    this.currencyDecimals = currencyDecimals;
    return this;
  }

  /**
   * Number of decimals used for currency
   * minimum: 0
   * maximum: 5
   * @return currencyDecimals
  **/
  @ApiModelProperty(required = true, value = "Number of decimals used for currency")
      @NotNull

  @Min(0) @Max(5)   public Integer getCurrencyDecimals() {
    return currencyDecimals;
  }

  public void setCurrencyDecimals(Integer currencyDecimals) {
    this.currencyDecimals = currencyDecimals;
  }

  public Settings minOrderRequestMinutes(Integer minOrderRequestMinutes) {
    this.minOrderRequestMinutes = minOrderRequestMinutes;
    return this;
  }

  /**
   * Minimum time in minutes to request the order
   * minimum: 0
   * @return minOrderRequestMinutes
  **/
  @ApiModelProperty(required = true, value = "Minimum time in minutes to request the order")
      @NotNull

  @Min(0)  public Integer getMinOrderRequestMinutes() {
    return minOrderRequestMinutes;
  }

  public void setMinOrderRequestMinutes(Integer minOrderRequestMinutes) {
    this.minOrderRequestMinutes = minOrderRequestMinutes;
  }

  public Settings processingOrdersMinutesPartition(Integer processingOrdersMinutesPartition) {
    this.processingOrdersMinutesPartition = processingOrdersMinutesPartition;
    return this;
  }

  /**
   * Get processingOrdersMinutesPartition
   * @return processingOrdersMinutesPartition
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getProcessingOrdersMinutesPartition() {
    return processingOrdersMinutesPartition;
  }

  public void setProcessingOrdersMinutesPartition(Integer processingOrdersMinutesPartition) {
    this.processingOrdersMinutesPartition = processingOrdersMinutesPartition;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Settings settings = (Settings) o;
    return Objects.equals(this.id, settings.id) &&
        Objects.equals(this.currencySymbol, settings.currencySymbol) &&
        Objects.equals(this.currencyDecimals, settings.currencyDecimals) &&
        Objects.equals(this.minOrderRequestMinutes, settings.minOrderRequestMinutes) &&
        Objects.equals(this.processingOrdersMinutesPartition, settings.processingOrdersMinutesPartition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, currencySymbol, currencyDecimals, minOrderRequestMinutes, processingOrdersMinutesPartition);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Settings {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    currencySymbol: ").append(toIndentedString(currencySymbol)).append("\n");
    sb.append("    currencyDecimals: ").append(toIndentedString(currencyDecimals)).append("\n");
    sb.append("    minOrderRequestMinutes: ").append(toIndentedString(minOrderRequestMinutes)).append("\n");
    sb.append("    processingOrdersMinutesPartition: ").append(toIndentedString(processingOrdersMinutesPartition)).append("\n");
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
