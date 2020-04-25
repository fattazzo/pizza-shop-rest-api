package com.fattazzo.pizzashop.model.dto.data;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Company
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class Company {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("webUrl")
	private String webUrl = null;

	@JsonProperty("logoUrl")
	private String logoUrl = null;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Company company = (Company) o;
		return Objects.equals(this.id, company.id) && Objects.equals(this.name, company.name)
				&& Objects.equals(this.webUrl, company.webUrl) && Objects.equals(this.logoUrl, company.logoUrl);
	}

	/**
	 * Unique identifier
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Unique identifier")
	@NotNull

	public Integer getId() {
		return id;
	}

	/**
	 * Url of `Company` logo
	 * 
	 * @return logoUrl
	 **/
	@ApiModelProperty(value = "Url of `Company` logo")

	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * Company name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(required = true, value = "Company name")
	@NotNull

	public String getName() {
		return name;
	}

	/**
	 * Company web url
	 * 
	 * @return webUrl
	 **/
	@ApiModelProperty(value = "Company web url")

	public String getWebUrl() {
		return webUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, webUrl, logoUrl);
	}

	public Company id(Integer id) {
		this.id = id;
		return this;
	}

	public Company logoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
		return this;
	}

	public Company name(String name) {
		this.name = name;
		return this;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public void setName(String name) {
		this.name = name;
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
		sb.append("class Company {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    webUrl: ").append(toIndentedString(webUrl)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public Company webUrl(String webUrl) {
		this.webUrl = webUrl;
		return this;
	}
}