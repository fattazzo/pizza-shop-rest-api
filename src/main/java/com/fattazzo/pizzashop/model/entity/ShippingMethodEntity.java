package com.fattazzo.pizzashop.model.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fattazzo.pizzashop.model.api.ShippingMethodType;
import com.fattazzo.pizzashop.model.api.ShippingType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ShippingMethod
 */
@Entity
@Table(name = "data_shipping_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class ShippingMethodEntity extends EntityBase {

	@Column(nullable = false)
	private String title;

	private String description;

	@Column(nullable = false)
	private ShippingMethodType type;

	@Builder.Default
	private boolean enabled = Boolean.TRUE;

	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "data_shipping_methods_shipping_types")
	Collection<ShippingType> shippingTypes = new ArrayList();

}