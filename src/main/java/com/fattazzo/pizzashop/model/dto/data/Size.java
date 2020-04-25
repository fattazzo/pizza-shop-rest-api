package com.fattazzo.pizzashop.model.dto.data;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Size
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class Size {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("extra")
	private BigDecimal extra = null;

	@JsonProperty("enabled")
	private Boolean enabled = null;

	public Size description(String description) {
		this.description = description;
		return this;
	}

	public Size enabled(Boolean enabled) {
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
		final Size size = (Size) o;
		return Objects.equals(this.id, size.id) && Objects.equals(this.name, size.name)
				&& Objects.equals(this.description, size.description) && Objects.equals(this.extra, size.extra)
				&& Objects.equals(this.enabled, size.enabled);
	}

	public Size extra(BigDecimal extra) {
		this.extra = extra;
		return this;
	}

	/**
	 * Get description
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "")

	public String getDescription() {
		return description;
	}

	/**
	 * Get extra
	 * 
	 * @return extra
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public BigDecimal getExtra() {
		return extra;
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
	 * Get name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, extra, enabled);
	}

	public Size id(Integer id) {
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

	public Size name(String name) {
		this.name = name;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setExtra(BigDecimal extra) {
		this.extra = extra;
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
		sb.append("class Size {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
		sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
