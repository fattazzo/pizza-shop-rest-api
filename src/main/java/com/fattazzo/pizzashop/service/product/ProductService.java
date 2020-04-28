package com.fattazzo.pizzashop.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.ProductEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		productRepository.deleteById(id);
	}

	public List<ProductEntity> findAll() {
		return productRepository.findAllByOrderByNameAsc();
	}

	public List<ProductEntity> findAllEnabled() {
		return productRepository.findByEnabledTrueOrderByNameAsc();
	}

	public Optional<ProductEntity> findById(Integer id) {
		return productRepository.findById(id);
	}

	public ProductEntity save(ProductEntity product) {
		if (product.getId() != null) {
			findById(product.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return productRepository.save(product);
	}
}
