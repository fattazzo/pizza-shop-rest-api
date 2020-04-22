package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fattazzo.pizzashop.model.entity.UserEntity.UserStatus;
import com.fattazzo.pizzashop.model.entity.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * UserDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilderExt")
@ToString
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("username")
	private String username;

	@JsonProperty("email")
	private String email;

	@Builder.Default
	private boolean readOnly = false;

	@Builder.Default
	private UserType type = UserType.WORKER;

	private UserStatus status;

}
