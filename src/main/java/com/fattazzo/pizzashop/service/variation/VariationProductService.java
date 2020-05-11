package com.fattazzo.pizzashop.service.variation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.VariationProductEntity;
import com.fattazzo.pizzashop.repository.VariationProductRepository;

@Service
public class VariationProductService {

	@Autowired
	private VariationProductRepository variationProductRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		variationProductRepository.deleteById(id);
	}

	public List<VariationProductEntity> findAll() {
		return variationProductRepository.findAllByOrderByOrderAsc();
	}

	public List<VariationProductEntity> findAllEnabled() {
		return variationProductRepository.findByEnabledTrueOrderByOrderAsc();
	}

	public Optional<VariationProductEntity> findById(Integer id) {
		return variationProductRepository.findById(id);
	}

	public Optional<VariationProductEntity> findByName(String name) {
		return variationProductRepository.findByNameIgnoreCase(name);
	}

	public VariationProductEntity save(VariationProductEntity entity) {
		if (entity.getId() != null) {
			findById(entity.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return variationProductRepository.save(entity);
	}

}
