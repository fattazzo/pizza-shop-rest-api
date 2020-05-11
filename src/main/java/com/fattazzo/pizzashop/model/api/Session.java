package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Session
 */
@Validated
public class Session   {
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

  public Session userInfo(User userInfo) {
    this.userInfo = userInfo;
    return this;
  }

  /**
   * Get userInfo
   * @return userInfo
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public User getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(User userInfo) {
    this.userInfo = userInfo;
  }

  public Session locale(String locale) {
    this.locale = locale;
    return this;
  }

  /**
   * Get locale
   * @return locale
  **/
  @ApiModelProperty(value = "")
  
    public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public Session enviroment(String enviroment) {
    this.enviroment = enviroment;
    return this;
  }

  /**
   * Get enviroment
   * @return enviroment
  **/
  @ApiModelProperty(value = "")
  
    public String getEnviroment() {
    return enviroment;
  }

  public void setEnviroment(String enviroment) {
    this.enviroment = enviroment;
  }

  public Session accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  /**
   * Get accessToken
   * @return accessToken
  **/
  @ApiModelProperty(value = "")
  
    public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Session refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  /**
   * Get refreshToken
   * @return refreshToken
  **/
  @ApiModelProperty(value = "")
  
    public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Session session = (Session) o;
    return Objects.equals(this.userInfo, session.userInfo) &&
        Objects.equals(this.locale, session.locale) &&
        Objects.equals(this.enviroment, session.enviroment) &&
        Objects.equals(this.accessToken, session.accessToken) &&
        Objects.equals(this.refreshToken, session.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userInfo, locale, enviroment, accessToken, refreshToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Session {\n");
    
    sb.append("    userInfo: ").append(toIndentedString(userInfo)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    enviroment: ").append(toIndentedString(enviroment)).append("\n");
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
    sb.append("}");
    return sb.toString();
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
}
