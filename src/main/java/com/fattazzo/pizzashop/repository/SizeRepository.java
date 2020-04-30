package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.VariationSizeEntity;

public interface SizeRepository extends JpaRepository<VariationSizeEntity, Integer> {

	List<VariationSizeEntity> findAllByOrderByOrderAsc();

	List<VariationSizeEntity> findByEnabledTrueOrderByOrderAsc();

	Optional<VariationSizeEntity> findByNameIgnoreCase(String name);

}
