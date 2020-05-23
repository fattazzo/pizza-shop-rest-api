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
@Table(name = "data_items_product_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class ItemProductPriceEntity extends EntityBase {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private ItemProductEntity parent;

	@ToStringExclude
	@ManyToOne(optional = false)
	private VariationProductEntity variation;

	@Builder.Default
	@Column(nullable = false)
	private BigDecimal price = BigDecimal.ZERO;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ItemProductPriceEntity other = (ItemProductPriceEntity) obj;
		return Objects.equals(getId(), other.getId()) && Objects.equals(variation, other.variation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), variation);
	}

}
