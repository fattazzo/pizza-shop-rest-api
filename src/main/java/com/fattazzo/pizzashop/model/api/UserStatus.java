package com.fattazzo.pizzashop.model.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets UserStatus
 */
public enum UserStatus {
  ACTIVE("Active"),
    TOCONFIRM("ToConfirm");

  private String value;

  UserStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static UserStatus fromValue(String text) {
    for (UserStatus b : UserStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
