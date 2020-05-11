package com.fattazzo.pizzashop.repository;

import org.springframework.transaction.annotation.Transactional;

import com.fattazzo.pizzashop.model.entity.ItemPizzaEntity;

@Transactional
public interface ItemPizzaRepository extends ItemRepository<ItemPizzaEntity> {

}
