package com.fattazzo.pizzashop.model.entity;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Role
 */
public enum Role {

	WEB_ADMIN("WEB_ADMIN"), SECURITY("SECURITY"), SHIPPING_METHODS("SHIPPING_METHODS"), COMPANY("COMPANY"),
	EDIT_ACCOUNT("EDIT_ACCOUNT"), VARIATIONS("VARIATIONS");

	@JsonCreator
	public static Role fromValue(String text) {
		for (final Role b : Role.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public static List<Role> getDefaultAdminRole() {
		return Arrays.asList(Role.values());
	}

	public static List<Role> getDefaultCustomerRole() {
		return Arrays.asList(Role.EDIT_ACCOUNT);
	}

	public static List<Role> getDefaultWorkerRole() {
		return Arrays.asList(Role.WEB_ADMIN, Role.EDIT_ACCOUNT);
	}

	private String value;

	Role(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}
}
