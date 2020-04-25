package com.fattazzo.pizzashop.model.dto.data;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fattazzo.pizzashop.entity.data.ShippingMethodType;

import io.swagger.annotations.ApiModelProperty;

/**
 * ShippingMethod
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class ShippingMethod {
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

	public ShippingMethod description(String description) {
		this.description = description;
		return this;
	}

	public ShippingMethod enabled(Boolean enabled) {
		this.enabled = enabled;
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
		final ShippingMethod shippingMethod = (ShippingMethod) o;
		return Objects.equals(this.id, shippingMethod.id) && Objects.equals(this.title, shippingMethod.title)
				&& Objects.equals(this.description, shippingMethod.description)
				&& Objects.equals(this.type, shippingMethod.type)
				&& Objects.equals(this.enabled, shippingMethod.enabled);
	}

	/**
	 * Shipping method description
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "Shipping method description")

	public String getDescription() {
		return description;
	}

	/**
	 * Method ID
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Method ID")
	@NotNull

	public Integer getId() {
		return id;
	}

	/**
	 * Shipping method title
	 * 
	 * @return title
	 **/
	@ApiModelProperty(required = true, value = "Shipping method title")
	@NotNull

	public String getTitle() {
		return title;
	}

	/**
	 * Get type
	 * 
	 * @return type
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public ShippingMethodType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, description, type, enabled);
	}

	public ShippingMethod id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get enabled
	 * 
	 * @return enabled
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Boolean isEnabled() {
		return enabled;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(ShippingMethodType type) {
		this.type = type;
	}

	public ShippingMethod title(String title) {
		this.title = title;
		return this;
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
		sb.append("class ShippingMethod {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public ShippingMethod type(ShippingMethodType type) {
		this.type = type;
		return this;
	}
}
