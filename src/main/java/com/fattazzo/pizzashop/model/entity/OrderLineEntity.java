package com.fattazzo.pizzashop.model.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ord_orders_lines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class OrderLineEntity extends EntityBase {

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id")
	private OrderEntity parent;

	@ManyToOne(optional = false)
	private ItemEntity item;

	@ManyToOne
	private ItemProductPriceEntity productPrice;

	@ManyToOne
	private ItemPizzaPriceEntity pizzaPrice;

	@ManyToOne
	private VariationDoughEntity pizzaDough;

	@ManyToMany
	private List<ToppingExtraEntity> pizzaToppingExtras;

	@Builder.Default
	@Column(nullable = false)
	private Integer quantity = 0;

	@Column(length = 500)
	private String customerNote;

	@Builder.Default
	@Column(nullable = false)
	private BigDecimal total = BigDecimal.ZERO;
}
