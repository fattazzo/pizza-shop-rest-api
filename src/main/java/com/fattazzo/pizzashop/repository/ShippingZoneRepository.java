package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ShippingZoneEntity;

public interface ShippingZoneRepository extends JpaRepository<ShippingZoneEntity, Integer> {

}
