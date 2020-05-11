package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.VariationProductEntity;

public interface VariationProductRepository extends JpaRepository<VariationProductEntity, Integer> {

	List<VariationProductEntity> findAllByOrderByOrderAsc();

	List<VariationProductEntity> findByEnabledTrueOrderByOrderAsc();

	Optional<VariationProductEntity> findByNameIgnoreCase(String name);

}
