package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.ProductImageEntity;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {

}
