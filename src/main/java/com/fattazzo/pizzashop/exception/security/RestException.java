package com.fattazzo.pizzashop.exception.security;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class RestException extends RuntimeException {

	private static final long serialVersionUID = 5956676434791674628L;

	private String title;

	private HttpStatus status;

	private String detail;
}
