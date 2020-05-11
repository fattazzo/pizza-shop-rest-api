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
 * DashBoardPizzas
 */
@Validated
public class DashBoardPizzas   {
  @JsonProperty("totalEnable")
  private Integer totalEnable = null;

  @JsonProperty("totalCategoriesEnable")
  private Integer totalCategoriesEnable = null;

  public DashBoardPizzas totalEnable(Integer totalEnable) {
    this.totalEnable = totalEnable;
    return this;
  }

  /**
   * Total of `ItemPizza` enabled.
   * @return totalEnable
  **/
  @ApiModelProperty(required = true, value = "Total of `ItemPizza` enabled.")
      @NotNull

    public Integer getTotalEnable() {
    return totalEnable;
  }

  public void setTotalEnable(Integer totalEnable) {
    this.totalEnable = totalEnable;
  }

  public DashBoardPizzas totalCategoriesEnable(Integer totalCategoriesEnable) {
    this.totalCategoriesEnable = totalCategoriesEnable;
    return this;
  }

  /**
   * Total of pizza `Category` enabled.
   * @return totalCategoriesEnable
  **/
  @ApiModelProperty(required = true, value = "Total of pizza `Category` enabled.")
      @NotNull

    public Integer getTotalCategoriesEnable() {
    return totalCategoriesEnable;
  }

  public void setTotalCategoriesEnable(Integer totalCategoriesEnable) {
    this.totalCategoriesEnable = totalCategoriesEnable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashBoardPizzas dashBoardPizzas = (DashBoardPizzas) o;
    return Objects.equals(this.totalEnable, dashBoardPizzas.totalEnable) &&
        Objects.equals(this.totalCategoriesEnable, dashBoardPizzas.totalCategoriesEnable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalEnable, totalCategoriesEnable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashBoardPizzas {\n");
    
    sb.append("    totalEnable: ").append(toIndentedString(totalEnable)).append("\n");
    sb.append("    totalCategoriesEnable: ").append(toIndentedString(totalCategoriesEnable)).append("\n");
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
