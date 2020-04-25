package com.fattazzo.pizzashop.model.dto.security;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Settings
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class Settings {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("currencySymbol")
	private String currencySymbol = null;

	@JsonProperty("currencyDecimals")
	private Integer currencyDecimals = null;

	public Settings currencyDecimals(Integer currencyDecimals) {
		this.currencyDecimals = currencyDecimals;
		return this;
	}

	public Settings currencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
		return this;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Settings settings = (Settings) o;
		return Objects.equals(this.id, settings.id) && Objects.equals(this.currencySymbol, settings.currencySymbol)
				&& Objects.equals(this.currencyDecimals, settings.currencyDecimals);
	}

	/**
	 * Get currencyDecimals
	 * 
	 * @return currencyDecimals
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Integer getCurrencyDecimals() {
		return currencyDecimals;
	}

	/**
	 * Get currencySymbol
	 * 
	 * @return currencySymbol
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, currencySymbol, currencyDecimals);
	}

	public Settings id(Integer id) {
		this.id = id;
		return this;
	}

	public void setCurrencyDecimals(Integer currencyDecimals) {
		this.currencyDecimals = currencyDecimals;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Settings {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    currencySymbol: ").append(toIndentedString(currencySymbol)).append("\n");
		sb.append("    currencyDecimals: ").append(toIndentedString(currencyDecimals)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
