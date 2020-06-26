package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets SocialTypeEnum
 */
public enum SocialTypeEnum {
  NONE("NONE"),
    GOOGLE("GOOGLE"),
    FACEBOOK("FACEBOOK");

  private String value;

  SocialTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SocialTypeEnum fromValue(String text) {
    for (SocialTypeEnum b : SocialTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
