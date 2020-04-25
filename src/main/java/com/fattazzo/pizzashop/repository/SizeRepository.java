package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.SizeEntity;

public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {

	List<SizeEntity> findByEnabledTrue();

	Optional<SizeEntity> findByNameIgnoreCase(String name);

}
