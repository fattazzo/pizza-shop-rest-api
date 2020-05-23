package com.fattazzo.pizzashop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ShippingMethodEntity;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethodEntity, Integer> {

	List<ShippingMethodEntity> findByEnabledTrue();

}
