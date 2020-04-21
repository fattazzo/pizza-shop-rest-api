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
 * Session
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Builder.Default
	@JsonProperty("userInfo")
	private UserDto userInfo = null;

	@JsonProperty("locale")
	private String locale;

	@JsonProperty("environment")
	private String environment;

	@JsonProperty("accessToken")
	private String accessToken;

	@JsonProperty("refreshToken")
	private String refreshToken;

	public Session accessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public Session environment(String environment) {
		this.environment = environment;
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
		final Session session = (Session) o;
		return Objects.equals(this.userInfo, session.userInfo) && Objects.equals(this.locale, session.locale)
				&& Objects.equals(this.environment, session.environment)
				&& Objects.equals(this.accessToken, session.accessToken)
				&& Objects.equals(this.refreshToken, session.refreshToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userInfo, locale, environment, accessToken, refreshToken);
	}

	public Session locale(String locale) {
		this.locale = locale;
		return this;
	}

	public Session refreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	@Override
	public String toString() {
		return "Session [userInfo=" + userInfo + ", locale=" + locale + ", environment=" + environment
				+ ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
	}

	public Session userInfo(UserDto userInfo) {
		this.userInfo = userInfo;
		return this;
	}
}
