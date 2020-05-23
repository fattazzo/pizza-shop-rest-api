package com.fattazzo.pizzashop.model.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fattazzo.pizzashop.model.api.OrderState;
import com.fattazzo.pizzashop.model.api.ShippingType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ord_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class OrderEntity extends EntityBase {

	@ManyToOne(optional = false)
	private UserEntity customer;

	@Builder.Default
	@Column(nullable = false)
	private OrderState state = OrderState.PENDING;

	@ManyToOne
	private ShippingMethodEntity shippingMethod;

	private ShippingType shippingType;

	private String transactionId;

	@Column(length = 500)
	private String customerNote;

	@Column(length = 500)
	private String backofficeNote;

	@ManyToOne(optional = false)
	private BranchEntity branch;

	@Column(nullable = false)
	private OffsetDateTime dateRequest;

	@Column(nullable = false)
	private OffsetDateTime dateCreation;

	private OffsetDateTime dateRequestConfirmed;

	@Builder.Default
	@Column(nullable = false)
	private BigDecimal total = BigDecimal.ZERO;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "streetAddress", column = @Column(name = "deliveryAddress_streetAddress")),
			@AttributeOverride(name = "number", column = @Column(name = "deliveryAddress_number")),
			@AttributeOverride(name = "place", column = @Column(name = "deliveryAddress_place")),
			@AttributeOverride(name = "postalCode", column = @Column(name = "deliveryAddress_postalCode")) })
	private Address deliveryAddress;

	@ToStringExclude
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
	private List<OrderLineEntity> lines = new ArrayList<>();

}
