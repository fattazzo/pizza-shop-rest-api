package com.fattazzo.pizzashop.service.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.ItemProductEntity;
import com.fattazzo.pizzashop.repository.ItemProductRepository;

@Service
public class ProductService {

	@Autowired
	private ItemProductRepository productRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		productRepository.deleteById(id);
	}

	public List<ItemProductEntity> findAll() {
		return productRepository.findAllByOrderByNameAsc();
	}

	public List<ItemProductEntity> findAllByCategory(Integer categoryId) {
		return productRepository.findByCategoryIdOrderByNameAsc(categoryId);
	}

	public List<ItemProductEntity> findAllEnabled() {
		return productRepository.findByEnabledTrueOrderByNameAsc();
	}

	public Optional<ItemProductEntity> findById(Integer id) {
		return productRepository.findById(id);
	}

	public ItemProductEntity save(ItemProductEntity entity) {
		if (entity.getId() != null) {
			findById(entity.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return productRepository.save(entity);
	}
}
