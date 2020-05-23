package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Role
 */
public enum Role {
  WEB_ADMIN("WEB_ADMIN"),
    SECURITY("SECURITY"),
    SHIPPING_METHODS("SHIPPING_METHODS"),
    COMPANY("COMPANY"),
    EDIT_ACCOUNT("EDIT_ACCOUNT"),
    VARIATIONS("VARIATIONS"),
    PRODUCTS("PRODUCTS"),
    ORDERS("ORDERS"),
    CREATE_ORDER("CREATE_ORDER"),
    PROCESS_ORDER("PROCESS_ORDER");

  private String value;

  Role(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Role fromValue(String text) {
    for (Role b : Role.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
