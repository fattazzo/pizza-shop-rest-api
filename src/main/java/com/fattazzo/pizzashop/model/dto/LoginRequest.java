package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class LoginRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	@JsonProperty("locale")
	private String locale;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final LoginRequest loginRequest = (LoginRequest) o;
		return Objects.equals(this.username, loginRequest.username)
				&& Objects.equals(this.password, loginRequest.password)
				&& Objects.equals(this.locale, loginRequest.locale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password, locale);
	}

	public LoginRequest locale(String locale) {
		this.locale = locale;
		return this;
	}

	public LoginRequest password(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + ", locale=" + locale + "]";
	}

	public LoginRequest username(String username) {
		this.username = username;
		return this;
	}
}
