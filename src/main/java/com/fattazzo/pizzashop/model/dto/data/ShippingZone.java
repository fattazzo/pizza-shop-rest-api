package com.fattazzo.pizzashop.model.dto.data;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ShippingZone
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class ShippingZone {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ShippingZone shippingZone = (ShippingZone) o;
		return Objects.equals(this.id, shippingZone.id) && Objects.equals(this.name, shippingZone.name);
	}

	/**
	 * Unique identifier for the resource
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Unique identifier for the resource")
	@NotNull

	public Integer getId() {
		return id;
	}

	/**
	 * Shipping zone name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(required = true, value = "Shipping zone name")
	@NotNull

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	public ShippingZone id(Integer id) {
		this.id = id;
		return this;
	}

	public ShippingZone name(String name) {
		this.name = name;
		return this;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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
		sb.append("class ShippingZone {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
