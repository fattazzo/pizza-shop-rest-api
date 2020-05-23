package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ItemPizzaPriceEntity;

public interface ItemPizzaPriceRepository extends JpaRepository<ItemPizzaPriceEntity, Integer> {

}
