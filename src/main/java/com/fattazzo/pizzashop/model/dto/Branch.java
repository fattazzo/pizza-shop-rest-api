package com.fattazzo.pizzashop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Branch {

	private Integer id;

	private String phone;

	private String webUrl;

	@Builder.Default
	private boolean primary = false;

	private Address address;

	@Builder.Default
	private boolean enabled = false;
}
