package com.fattazzo.pizzashop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.ToppingExtraEntity;

public interface ToppingExtraRepository extends JpaRepository<ToppingExtraEntity, Integer> {

	List<ToppingExtraEntity> findByDoughIdAndSizeIdOrderByToppingNameAsc(Integer doughId, Integer sizeId);
}
