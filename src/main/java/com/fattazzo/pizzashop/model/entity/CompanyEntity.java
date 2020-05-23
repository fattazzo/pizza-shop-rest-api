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

/**
 * Company
 */
@Entity
@Table(name = "data_companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class CompanyEntity extends EntityBase {

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String webUrl;

}
