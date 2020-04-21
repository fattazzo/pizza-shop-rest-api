package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * UserRegistrationInfo
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class UserRegistrationInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("username")
	private String username;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

	public UserRegistrationInfo email(String email) {
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
		final UserRegistrationInfo userRegistrationInfo = (UserRegistrationInfo) o;
		return Objects.equals(this.username, userRegistrationInfo.username)
				&& Objects.equals(this.firstname, userRegistrationInfo.firstname)
				&& Objects.equals(this.lastname, userRegistrationInfo.lastname)
				&& Objects.equals(this.password, userRegistrationInfo.password)
				&& Objects.equals(this.email, userRegistrationInfo.email);
	}

	public UserRegistrationInfo firstname(String firstname) {
		this.firstname = firstname;
		return this;
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
	 * Get firstname
	 * 
	 * @return firstname
	 */

	public String getFirstname() {
		return firstname;
	}

	/**
	 * Get lastname
	 * 
	 * @return lastname
	 */

	public String getLastname() {
		return lastname;
	}

	/**
	 * Get password
	 * 
	 * @return password
	 */
	@NotNull
	@Size(min = 5, max = 30)
	public String getPassword() {
		return password;
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
		return Objects.hash(username, firstname, lastname, password, email);
	}

	public UserRegistrationInfo lastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public UserRegistrationInfo password(String password) {
		this.password = password;
		return this;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPassword(String password) {
		this.password = password;
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
		sb.append("class UserRegistrationInfo {\n");

		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
		sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public UserRegistrationInfo username(String username) {
		this.username = username;
		return this;
	}
}
