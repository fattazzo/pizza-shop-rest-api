package com.fattazzo.pizzashop.model.dto.security;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Session
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T13:58:43.091Z[GMT]")
public class Session {
	@JsonProperty("userInfo")
	private User userInfo = null;

	@JsonProperty("locale")
	private String locale = null;

	@JsonProperty("enviroment")
	private String enviroment = null;

	@JsonProperty("accessToken")
	private String accessToken = null;

	@JsonProperty("refreshToken")
	private String refreshToken = null;

	public Session accessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public Session enviroment(String enviroment) {
		this.enviroment = enviroment;
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
				&& Objects.equals(this.enviroment, session.enviroment)
				&& Objects.equals(this.accessToken, session.accessToken)
				&& Objects.equals(this.refreshToken, session.refreshToken);
	}

	/**
	 * Get accessToken
	 * 
	 * @return accessToken
	 **/
	@ApiModelProperty(value = "")

	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Get enviroment
	 * 
	 * @return enviroment
	 **/
	@ApiModelProperty(value = "")

	public String getEnviroment() {
		return enviroment;
	}

	/**
	 * Get locale
	 * 
	 * @return locale
	 **/
	@ApiModelProperty(value = "")

	public String getLocale() {
		return locale;
	}

	/**
	 * Get refreshToken
	 * 
	 * @return refreshToken
	 **/
	@ApiModelProperty(value = "")

	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Get userInfo
	 * 
	 * @return userInfo
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public User getUserInfo() {
		return userInfo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userInfo, locale, enviroment, accessToken, refreshToken);
	}

	public Session locale(String locale) {
		this.locale = locale;
		return this;
	}

	public Session refreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
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
		sb.append("class Session {\n");

		sb.append("    userInfo: ").append(toIndentedString(userInfo)).append("\n");
		sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
		sb.append("    enviroment: ").append(toIndentedString(enviroment)).append("\n");
		sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
		sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public Session userInfo(User userInfo) {
		this.userInfo = userInfo;
		return this;
	}
}
