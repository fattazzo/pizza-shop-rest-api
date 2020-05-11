package com.fattazzo.pizzashop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.fattazzo.pizzashop.model.entity.ItemEntity;

@NoRepositoryBean
public interface ItemRepository<T extends ItemEntity> extends CrudRepository<T, Integer> {

	List<T> findAllByOrderByNameAsc();

	List<T> findByCategoryIdOrderByNameAsc(Integer categoryId);

	List<T> findByEnabledTrueOrderByNameAsc();
}
