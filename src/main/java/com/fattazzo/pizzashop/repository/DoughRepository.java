package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.DoughEntity;

public interface DoughRepository extends JpaRepository<DoughEntity, Integer> {

	List<DoughEntity> findByEnabledTrue();

	Optional<DoughEntity> findByNameIgnoreCase(String name);

}
