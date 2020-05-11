package com.fattazzo.pizzashop.repository;

import org.springframework.transaction.annotation.Transactional;

import com.fattazzo.pizzashop.model.entity.ItemProductEntity;

@Transactional
public interface ItemProductRepository extends ItemRepository<ItemProductEntity> {

}
