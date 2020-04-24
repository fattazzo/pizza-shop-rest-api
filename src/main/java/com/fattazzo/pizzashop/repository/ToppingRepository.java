package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.data.ToppingEntity;

public interface ToppingRepository extends JpaRepository<ToppingEntity, Integer> {

	Optional<ToppingEntity> findByNameIgnoreCase(String name);

}
