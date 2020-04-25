package com.fattazzo.pizzashop.model.dto.data;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Branch
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class Branch {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("phone")
	private String phone = null;

	@JsonProperty("webUrl")
	private String webUrl = null;

	@JsonProperty("primary")
	private Boolean primary = null;

	@JsonProperty("address")
	private Address address = null;

	@JsonProperty("enabled")
	private Boolean enabled = null;

	public Branch address(Address address) {
		this.address = address;
		return this;
	}

	public Branch enabled(Boolean enabled) {
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
		final Branch branch = (Branch) o;
		return Objects.equals(this.id, branch.id) && Objects.equals(this.phone, branch.phone)
				&& Objects.equals(this.webUrl, branch.webUrl) && Objects.equals(this.primary, branch.primary)
				&& Objects.equals(this.address, branch.address) && Objects.equals(this.enabled, branch.enabled);
	}

	/**
	 * Get address
	 * 
	 * @return address
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public Address getAddress() {
		return address;
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

	/**
	 * Get phone
	 * 
	 * @return phone
	 **/
	@ApiModelProperty(value = "")

	public String getPhone() {
		return phone;
	}

	/**
	 * Get webUrl
	 * 
	 * @return webUrl
	 **/
	@ApiModelProperty(value = "")

	public String getWebUrl() {
		return webUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, phone, webUrl, primary, address, enabled);
	}

	public Branch id(Integer id) {
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

	/**
	 * Get primary
	 * 
	 * @return primary
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Boolean isPrimary() {
		return primary;
	}

	public Branch phone(String phone) {
		this.phone = phone;
		return this;
	}

	public Branch primary(Boolean primary) {
		this.primary = primary;
		return this;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
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
		sb.append("class Branch {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
		sb.append("    webUrl: ").append(toIndentedString(webUrl)).append("\n");
		sb.append("    primary: ").append(toIndentedString(primary)).append("\n");
		sb.append("    address: ").append(toIndentedString(address)).append("\n");
		sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public Branch webUrl(String webUrl) {
		this.webUrl = webUrl;
		return this;
	}
}
