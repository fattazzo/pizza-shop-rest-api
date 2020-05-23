package com.fattazzo.pizzashop.model.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "data_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class CategoryEntity extends EntityBase {

	@Column(nullable = false, length = 100)
	private String name;

	private String description;

	@Builder.Default
	private Boolean enabled = true;

	private Integer order;

	private ItemType type;

	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<VariationDoughEntity> doughs = new TreeSet<>();

	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<VariationSizeEntity> sizes = new TreeSet<>();
}
