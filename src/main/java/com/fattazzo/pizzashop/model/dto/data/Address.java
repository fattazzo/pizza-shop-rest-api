package com.fattazzo.pizzashop.model.dto.data;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Address
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class Address {
	@JsonProperty("streetAddress")
	private String streetAddress = null;

	@JsonProperty("number")
	private String number = null;

	@JsonProperty("place")
	private String place = null;

	@JsonProperty("postalCode")
	private String postalCode = null;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Address address = (Address) o;
		return Objects.equals(this.streetAddress, address.streetAddress) && Objects.equals(this.number, address.number)
				&& Objects.equals(this.place, address.place) && Objects.equals(this.postalCode, address.postalCode);
	}

	/**
	 * Get number
	 * 
	 * @return number
	 **/
	@ApiModelProperty(value = "")

	public String getNumber() {
		return number;
	}

	/**
	 * Get place
	 * 
	 * @return place
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getPlace() {
		return place;
	}

	/**
	 * Get postalCode
	 * 
	 * @return postalCode
	 **/
	@ApiModelProperty(value = "")

	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Get streetAddress
	 * 
	 * @return streetAddress
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getStreetAddress() {
		return streetAddress;
	}

	@Override
	public int hashCode() {
		return Objects.hash(streetAddress, number, place, postalCode);
	}

	public Address number(String number) {
		this.number = number;
		return this;
	}

	public Address place(String place) {
		this.place = place;
		return this;
	}

	public Address postalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Address streetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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
		sb.append("class Address {\n");

		sb.append("    streetAddress: ").append(toIndentedString(streetAddress)).append("\n");
		sb.append("    number: ").append(toIndentedString(number)).append("\n");
		sb.append("    place: ").append(toIndentedString(place)).append("\n");
		sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
