package com.fattazzo.pizzashop.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@ToString
@Builder
@EqualsAndHashCode
public class ToppingExtraEntity {

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(optional = false)
	private ToppingEntity topping;

	@ManyToOne(optional = false)
	private VariationDoughEntity dough;

	@ManyToOne(optional = false)
	private VariationSizeEntity size;

	@Builder.Default
	private BigDecimal extra = BigDecimal.ZERO;

	@Builder.Default
	private boolean enabled = Boolean.TRUE;

}
