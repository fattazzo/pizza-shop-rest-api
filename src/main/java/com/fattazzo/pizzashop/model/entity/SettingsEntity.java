package com.fattazzo.pizzashop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sicu_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class SettingsEntity extends EntityBase {

	@Column(nullable = false)
	private String currencySymbol;

	@Builder.Default
	@Column(nullable = false)
	private int currencyDecimals = 2;

	@Builder.Default
	@Column(nullable = false)
	private Integer minOrderRequestMinutes = 45;

	@Builder.Default
	@Column(nullable = false)
	private Integer processingOrdersMinutesPartition = 15;

}
