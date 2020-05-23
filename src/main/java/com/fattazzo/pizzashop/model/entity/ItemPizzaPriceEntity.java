package com.fattazzo.pizzashop.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "data_items_pizza_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class ItemPizzaPriceEntity extends EntityBase {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pizza_id")
	private ItemPizzaEntity parent;

	@ToStringExclude
	@ManyToOne(optional = false)
	@JoinColumn(name = "size_id")
	private VariationSizeEntity variationSize;

	@Builder.Default
	@Column(nullable = false)
	private BigDecimal price = BigDecimal.ZERO;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ItemPizzaPriceEntity other = (ItemPizzaPriceEntity) obj;
		return Objects.equals(getId(), other.getId()) && Objects.equals(variationSize, other.variationSize);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(getId(), variationSize);
		return result;
	}

}
