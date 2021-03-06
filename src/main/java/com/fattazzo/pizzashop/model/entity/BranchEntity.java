package com.fattazzo.pizzashop.model.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Branch
 */
@Entity
@Table(name = "data_branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class BranchEntity extends EntityBase {

	private String phone;

	private String webUrl;

	@Builder.Default
	private boolean primary = false;

	@Embedded
	@Column(nullable = false)
	private Address address;

	@Builder.Default
	private boolean enabled = true;

	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ShippingMethodEntity> shippingMethods = new TreeSet<ShippingMethodEntity>();

	@EqualsAndHashCode.Exclude
	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "branch", orphanRemoval = true)
	private Set<ShippingZoneEntity> shippingZones = null;

}
