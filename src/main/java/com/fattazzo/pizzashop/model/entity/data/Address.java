package com.fattazzo.pizzashop.model.entity.data;

import javax.persistence.Embeddable;

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
@Embeddable
@Builder
@ToString
public class Address {

	private String streetAddress;

	private String number;

	private String place;

	private String postalCode;

}
