package com.fattazzo.pizzashop.entity.data;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "data_product_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class ProductCategoryEntity {

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String name;

	private String description;

	@Builder.Default
	private Boolean enabled = true;

	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<VariationDoughEntity> doughs = new TreeSet<>();

	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<VariationSizeEntity> sizes = new TreeSet<>();
}
