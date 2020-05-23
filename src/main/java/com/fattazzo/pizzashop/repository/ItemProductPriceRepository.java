package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ItemProductPriceEntity;

public interface ItemProductPriceRepository extends JpaRepository<ItemProductPriceEntity, Integer> {

}
