package com.fattazzo.pizzashop.model.dto.data;

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
public class ProductCategory {

	private Integer id;

	private String name;

	private String description;

	@Builder.Default
	private Boolean enabled = true;

}
