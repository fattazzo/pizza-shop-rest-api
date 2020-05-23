package com.fattazzo.pizzashop.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Item
 */
@Entity
@Table(name = "data_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 2)
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ItemEntity extends EntityBase {

	@Column(nullable = false)
	private String name;

	private String description;

	private boolean enabled = Boolean.TRUE;

	@ToStringExclude
	@ManyToOne(optional = false)
	private CategoryEntity category;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn
	@LazyToOne(value = LazyToOneOption.NO_PROXY)
	private ItemImageEntity image;

}
