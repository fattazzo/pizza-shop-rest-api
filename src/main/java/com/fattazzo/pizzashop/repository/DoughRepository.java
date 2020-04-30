package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.VariationDoughEntity;

public interface DoughRepository extends JpaRepository<VariationDoughEntity, Integer> {

	List<VariationDoughEntity> findAllByOrderByOrderAsc();

	List<VariationDoughEntity> findByEnabledTrueOrderByOrderAsc();

	Optional<VariationDoughEntity> findByNameIgnoreCase(String name);

}
