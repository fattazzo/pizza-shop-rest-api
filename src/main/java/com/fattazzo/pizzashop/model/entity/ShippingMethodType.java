package com.fattazzo.pizzashop.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets ShippingMethodType
 */
public enum ShippingMethodType {
	PAY_PAL("PAY_PAL"), CASH_ON_DELIVERY("CASH_ON_DELIVERY");

	@JsonCreator
	public static ShippingMethodType fromValue(String text) {
		for (final ShippingMethodType b : ShippingMethodType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	ShippingMethodType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}
}
