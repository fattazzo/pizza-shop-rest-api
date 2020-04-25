package com.fattazzo.pizzashop.model.dto.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * BranchDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class BranchDetails extends Branch {
	@JsonProperty("shippingMethods")
	@Valid
	private List<ShippingMethod> shippingMethods = new ArrayList<ShippingMethod>();

	@JsonProperty("shippingZones")
	@Valid
	private List<ShippingZone> shippingZones = null;

	public BranchDetails addShippingMethodsItem(ShippingMethod shippingMethodsItem) {
		this.shippingMethods.add(shippingMethodsItem);
		return this;
	}

	public BranchDetails addShippingZonesItem(ShippingZone shippingZonesItem) {
		if (this.shippingZones == null) {
			this.shippingZones = new ArrayList<ShippingZone>();
		}
		this.shippingZones.add(shippingZonesItem);
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
		final BranchDetails branchDetails = (BranchDetails) o;
		return Objects.equals(this.shippingMethods, branchDetails.shippingMethods)
				&& Objects.equals(this.shippingZones, branchDetails.shippingZones) && super.equals(o);
	}

	/**
	 * List of available `ShippingMethod`
	 * 
	 * @return shippingMethods
	 **/
	@ApiModelProperty(required = true, value = "List of available `ShippingMethod`")
	@NotNull
	@Valid
	public List<ShippingMethod> getShippingMethods() {
		return shippingMethods;
	}

	/**
	 * List of available `ShippingZone`
	 * 
	 * @return shippingZones
	 **/
	@ApiModelProperty(value = "List of available `ShippingZone`")
	@Valid
	public List<ShippingZone> getShippingZones() {
		return shippingZones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(shippingMethods, shippingZones, super.hashCode());
	}

	public void setShippingMethods(List<ShippingMethod> shippingMethods) {
		this.shippingMethods = shippingMethods;
	}

	public void setShippingZones(List<ShippingZone> shippingZones) {
		this.shippingZones = shippingZones;
	}

	public BranchDetails shippingMethods(List<ShippingMethod> shippingMethods) {
		this.shippingMethods = shippingMethods;
		return this;
	}

	public BranchDetails shippingZones(List<ShippingZone> shippingZones) {
		this.shippingZones = shippingZones;
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
		sb.append("class BranchDetails {\n");
		sb.append("    ").append(toIndentedString(super.toString())).append("\n");
		sb.append("    shippingMethods: ").append(toIndentedString(shippingMethods)).append("\n");
		sb.append("    shippingZones: ").append(toIndentedString(shippingZones)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
