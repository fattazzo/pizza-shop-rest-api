package com.fattazzo.pizzashop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	List<ProductEntity> findAllByOrderByNameAsc();

	List<ProductEntity> findByEnabledTrueOrderByNameAsc();
}
