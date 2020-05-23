package com.fattazzo.pizzashop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ToppingExtraEntity;

public interface ToppingExtraRepository extends JpaRepository<ToppingExtraEntity, Integer> {

	List<ToppingExtraEntity> findByDoughIdAndVariationSizeIdOrderByToppingNameAsc(Integer doughId, Integer sizeId);
}
