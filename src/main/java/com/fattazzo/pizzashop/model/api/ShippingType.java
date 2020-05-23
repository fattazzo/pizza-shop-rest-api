package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ShippingType
 */
public enum ShippingType {
  DELIVERY_TO_HOME("DELIVERY_TO_HOME"),
    PICKUP_IN_STORE("PICKUP_IN_STORE");

  private String value;

  ShippingType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ShippingType fromValue(String text) {
    for (ShippingType b : ShippingType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
