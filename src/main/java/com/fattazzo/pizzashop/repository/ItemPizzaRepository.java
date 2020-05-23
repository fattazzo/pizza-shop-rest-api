package com.fattazzo.pizzashop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ItemPizzaEntity;

public interface ItemPizzaRepository extends JpaRepository<ItemPizzaEntity, Integer> {

	List<ItemPizzaEntity> findAllByOrderByNameAsc();

	List<ItemPizzaEntity> findByCategoryIdOrderByNameAsc(Integer categoryId);

	List<ItemPizzaEntity> findByEnabledTrueOrderByNameAsc();

}
