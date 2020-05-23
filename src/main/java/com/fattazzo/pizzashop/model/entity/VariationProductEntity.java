package com.fattazzo.pizzashop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "data_variations_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class VariationProductEntity extends EntityBase {

	@Column(nullable = false, length = 100)
	private String name;

	private String description;

	@Builder.Default
	private boolean enabled = Boolean.FALSE;

	private Integer order;
}
