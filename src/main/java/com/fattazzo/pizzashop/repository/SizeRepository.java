package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.SizeEntity;

public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {

	List<SizeEntity> findAllByOrderByOrderAsc();

	List<SizeEntity> findByEnabledTrueOrderByOrderAsc();

	Optional<SizeEntity> findByNameIgnoreCase(String name);

}
