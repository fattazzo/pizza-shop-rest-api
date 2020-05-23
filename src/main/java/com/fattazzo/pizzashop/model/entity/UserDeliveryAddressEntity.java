package com.fattazzo.pizzashop.model.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fattazzo.pizzashop.model.api.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sicu_users_delivery_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class UserDeliveryAddressEntity extends EntityBase {

	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_username")
	private UserEntity parent;

	@Embedded
	private Address address;

	@ManyToOne
	private ShippingMethodEntity shippingMethod;
}
