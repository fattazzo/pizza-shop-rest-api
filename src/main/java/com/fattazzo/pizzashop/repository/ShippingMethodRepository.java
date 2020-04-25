package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.ShippingMethodEntity;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethodEntity, Integer> {

}
