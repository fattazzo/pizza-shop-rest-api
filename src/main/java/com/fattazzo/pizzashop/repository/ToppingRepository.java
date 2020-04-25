package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.ToppingEntity;

public interface ToppingRepository extends JpaRepository<ToppingEntity, Integer> {

	List<ToppingEntity> findByEnabledTrue();

	Optional<ToppingEntity> findByNameIgnoreCase(String name);

}
