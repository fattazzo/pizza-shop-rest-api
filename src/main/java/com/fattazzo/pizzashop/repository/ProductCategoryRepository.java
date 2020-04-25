package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.ProductCategoryEntity;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Integer> {

	List<ProductCategoryEntity> findByEnabledTrue();

	Optional<ProductCategoryEntity> findByNameIgnoreCase(String name);

}
