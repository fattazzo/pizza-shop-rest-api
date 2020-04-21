package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fattazzo.pizzashop.model.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * UserDto
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("username")
	private String username;

	@JsonProperty("email")
	private String email;

	@Builder.Default
	@JsonProperty("roles")
	private List<Role> roles = new ArrayList<>();

	public UserDto addRolesItem(Role rolesItem) {
		this.roles.add(rolesItem);
		return this;
	}

	public UserDto email(String email) {
		this.email = email;
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
		final UserDto user = (UserDto) o;
		return Objects.equals(this.username, user.username) && Objects.equals(this.email, user.email)
				&& Objects.equals(this.roles, user.roles);
	}

	/**
	 * Get email
	 *
	 * @return email
	 */
	@NotNull

	public String getEmail() {
		return email;
	}

	/**
	 * UserDto Roles
	 *
	 * @return roles
	 */
	@NotNull
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Get username
	 *
	 * @return username
	 */
	@NotNull
	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, email); // , roles);
	}

	public UserDto roles(List<Role> roles) {
		this.roles = roles;
		return this;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
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
		sb.append("class UserDto {\n");

		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append(" roles: ").append(toIndentedString(roles)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public UserDto username(String username) {
		this.username = username;
		return this;
	}
}
