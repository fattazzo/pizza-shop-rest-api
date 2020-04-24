package com.fattazzo.pizzashop.model.dto.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Size {

	private Integer id;

	private String name;

	private String description;

	@Builder.Default
	private BigDecimal extra = BigDecimal.ZERO;

	@Builder.Default
	private boolean enabled = Boolean.FALSE;
}