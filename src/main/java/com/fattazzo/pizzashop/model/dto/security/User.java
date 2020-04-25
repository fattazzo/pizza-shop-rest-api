package com.fattazzo.pizzashop.model.dto.security;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fattazzo.pizzashop.entity.security.UserType;

import io.swagger.annotations.ApiModelProperty;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class User {
	@JsonProperty("username")
	private String username = null;

	@JsonProperty("email")
	private String email = null;

	@JsonProperty("readOnly")
	private Boolean readOnly = null;

	@JsonProperty("type")
	private UserType type = null;

	public User email(String email) {
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
		final User user = (User) o;
		return Objects.equals(this.username, user.username) && Objects.equals(this.email, user.email)
				&& Objects.equals(this.readOnly, user.readOnly) && Objects.equals(this.type, user.type);
	}

	/**
	 * The email address
	 * 
	 * @return email
	 **/
	@ApiModelProperty(value = "The email address")

	public String getEmail() {
		return email;
	}

	/**
	 * Get type
	 * 
	 * @return type
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public UserType getType() {
		return type;
	}

	/**
	 * Login name
	 * 
	 * @return username
	 **/
	@ApiModelProperty(required = true, value = "Login name")
	@NotNull

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, email, readOnly, type);
	}

	/**
	 * Read only users cannot be deleted
	 * 
	 * @return readOnly
	 **/
	@ApiModelProperty(required = true, value = "Read only users cannot be deleted")
	@NotNull

	public Boolean isReadOnly() {
		return readOnly;
	}

	public User readOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setType(UserType type) {
		this.type = type;
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
		sb.append("class User {\n");

		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    readOnly: ").append(toIndentedString(readOnly)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public User type(UserType type) {
		this.type = type;
		return this;
	}

	public User username(String username) {
		this.username = username;
		return this;
	}
}
