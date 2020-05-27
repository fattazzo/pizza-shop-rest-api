package com.fattazzo.pizzashop.model.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "data_topping_extras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class ToppingExtraEntity extends EntityBase {

	@ManyToOne(optional = false)
	private ToppingEntity topping;

	@ManyToOne(optional = false)
	private VariationDoughEntity dough;

	@ToStringExclude
	@ManyToOne(optional = false)
	@JoinColumn(name = "size_id")
	private VariationSizeEntity variationSize;

	@Builder.Default
	private BigDecimal extra = BigDecimal.ZERO;

	@Builder.Default
	private boolean enabled = Boolean.TRUE;

}
