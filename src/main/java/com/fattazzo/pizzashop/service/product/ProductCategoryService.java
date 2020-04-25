package com.fattazzo.pizzashop.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.ProductCategoryEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		productCategoryRepository.deleteById(id);
	}

	public List<ProductCategoryEntity> findAll() {
		return productCategoryRepository.findAll();
	}

	public List<ProductCategoryEntity> findAllEnabled() {
		return productCategoryRepository.findByEnabledTrue();
	}

	public Optional<ProductCategoryEntity> findById(Integer id) {
		return productCategoryRepository.findById(id);
	}

	public Optional<ProductCategoryEntity> findByName(String name) {
		return productCategoryRepository.findByNameIgnoreCase(name);
	}

	public ProductCategoryEntity save(ProductCategoryEntity category) {
		if (category.getId() != null) {
			findById(category.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return productCategoryRepository.save(category);
	}
}
