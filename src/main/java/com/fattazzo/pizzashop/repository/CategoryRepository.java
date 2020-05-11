package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.CategoryEntity;
import com.fattazzo.pizzashop.model.entity.ItemType;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

	List<CategoryEntity> findByEnabledTrueOrderByOrder();

	Optional<CategoryEntity> findByNameIgnoreCase(String name);

	List<CategoryEntity> findByTypeAndEnabledTrueOrderByOrder(ItemType type);

	List<CategoryEntity> findByTypeOrderByOrder(ItemType type);

}
