package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.DoughEntity;

public interface DoughRepository extends JpaRepository<DoughEntity, Integer> {

	Optional<DoughEntity> findByNameIgnoreCase(String name);

}
