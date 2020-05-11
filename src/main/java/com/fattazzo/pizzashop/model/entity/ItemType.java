package com.fattazzo.pizzashop.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets ItemType
 */
public enum ItemType {
	PIZZA("PIZZA"), PRODUCT("PRODUCT");

	@JsonCreator
	public static ItemType fromValue(String text) {
		for (final ItemType b : ItemType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	ItemType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}
}
