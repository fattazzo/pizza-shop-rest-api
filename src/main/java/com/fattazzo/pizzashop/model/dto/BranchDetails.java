package com.fattazzo.pizzashop.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(builderMethodName = "builderDetails")
public class BranchDetails extends Branch {

	@Builder.Default
	private List<ShippingMethod> shippingMethods = new ArrayList<ShippingMethod>();

	private List<ShippingZone> shippingZones;
}
