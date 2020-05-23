package com.fattazzo.pizzashop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ItemProductEntity;

public interface ItemProductRepository extends JpaRepository<ItemProductEntity, Integer> {

	List<ItemProductEntity> findAllByOrderByNameAsc();

	List<ItemProductEntity> findByCategoryIdOrderByNameAsc(Integer categoryId);

	List<ItemProductEntity> findByEnabledTrueOrderByNameAsc();
}
