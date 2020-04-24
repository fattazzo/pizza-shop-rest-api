package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.data.SizeEntity;

public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {

	Optional<SizeEntity> findByNameIgnoreCase(String name);

}
