package com.fattazzo.pizzashop.model.entity.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets UserType
 */
public enum UserType {
	ADMIN("ADMIN"), WORKER("WORKER"), CUSTOMER("CUSTOMER");

	@JsonCreator
	public static UserType fromValue(String text) {
		for (final UserType b : UserType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	UserType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}
}
