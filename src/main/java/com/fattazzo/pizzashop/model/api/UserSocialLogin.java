package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.SocialTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserSocialLogin
 */
@Validated
public class UserSocialLogin   {
  @JsonProperty("locale")
  private String locale = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("token")
  private String token = null;

  @JsonProperty("socialType")
  private SocialTypeEnum socialType = null;

  public UserSocialLogin locale(String locale) {
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

  public UserSocialLogin userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public UserSocialLogin token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UserSocialLogin socialType(SocialTypeEnum socialType) {
    this.socialType = socialType;
    return this;
  }

  /**
   * Get socialType
   * @return socialType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public SocialTypeEnum getSocialType() {
    return socialType;
  }

  public void setSocialType(SocialTypeEnum socialType) {
    this.socialType = socialType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSocialLogin userSocialLogin = (UserSocialLogin) o;
    return Objects.equals(this.locale, userSocialLogin.locale) &&
        Objects.equals(this.userId, userSocialLogin.userId) &&
        Objects.equals(this.token, userSocialLogin.token) &&
        Objects.equals(this.socialType, userSocialLogin.socialType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locale, userId, token, socialType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSocialLogin {\n");
    
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    socialType: ").append(toIndentedString(socialType)).append("\n");
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
