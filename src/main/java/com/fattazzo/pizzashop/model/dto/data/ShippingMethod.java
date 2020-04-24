package com.fattazzo.pizzashop.model.dto.data;

import com.fattazzo.pizzashop.model.entity.data.ShippingMethodType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ShippingMethod
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class ShippingMethod {

	private Integer id;

	private String title;

	private String description;

	private ShippingMethodType type;

	@Builder.Default
	private boolean enabled = Boolean.TRUE;

}