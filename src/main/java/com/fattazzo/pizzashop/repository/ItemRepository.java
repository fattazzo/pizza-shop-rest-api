package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

}
