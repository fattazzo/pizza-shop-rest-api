package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ShippingMethodType
 */
public enum ShippingMethodType {
  PAY_PAL("PAY_PAL"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY");

  private String value;

  ShippingMethodType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ShippingMethodType fromValue(String text) {
    for (ShippingMethodType b : ShippingMethodType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
