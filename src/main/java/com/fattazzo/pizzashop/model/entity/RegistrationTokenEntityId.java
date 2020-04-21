package com.fattazzo.pizzashop.model.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationTokenEntityId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String token;

	private String username;
}
