package com.fattazzo.pizzashop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ShippingZone
 */
@Entity
@Table(name = "data_shipping_zones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class ShippingZoneEntity extends EntityBase {

	@ManyToOne
	private BranchEntity branch;

	@Column(nullable = false)
	private String name;

}
