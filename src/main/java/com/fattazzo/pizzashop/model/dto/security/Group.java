package com.fattazzo.pizzashop.model.dto.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fattazzo.pizzashop.entity.security.Role;

import io.swagger.annotations.ApiModelProperty;

/**
 * Group
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class Group {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("roles")
	@Valid
	private List<Role> roles = null;

	@JsonProperty("readOnly")
	private Boolean readOnly = null;

	public Group addRolesItem(Role rolesItem) {
		if (this.roles == null) {
			this.roles = new ArrayList<Role>();
		}
		this.roles.add(rolesItem);
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
		final Group group = (Group) o;
		return Objects.equals(this.id, group.id) && Objects.equals(this.name, group.name)
				&& Objects.equals(this.roles, group.roles) && Objects.equals(this.readOnly, group.readOnly);
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

	/**
	 * List of assigned `Role'
	 * 
	 * @return roles
	 **/
	@ApiModelProperty(value = "List of assigned `Role'")
	@Valid
	public List<Role> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, roles, readOnly);
	}

	public Group id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Read only groups cannot be deleted
	 * 
	 * @return readOnly
	 **/
	@ApiModelProperty(required = true, value = "Read only groups cannot be deleted")
	@NotNull

	public Boolean isReadOnly() {
		return readOnly;
	}

	public Group name(String name) {
		this.name = name;
		return this;
	}

	public Group readOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}

	public Group roles(List<Role> roles) {
		this.roles = roles;
		return this;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
		sb.append("class Group {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
		sb.append("    readOnly: ").append(toIndentedString(readOnly)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
