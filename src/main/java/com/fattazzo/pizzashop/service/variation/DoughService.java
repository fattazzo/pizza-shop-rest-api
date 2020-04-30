package com.fattazzo.pizzashop.service.variation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.VariationDoughEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.repository.DoughRepository;

@Service
public class DoughService {

	@Autowired
	private DoughRepository doughRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		doughRepository.deleteById(id);
	}

	public List<VariationDoughEntity> findAll() {
		return doughRepository.findAllByOrderByOrderAsc();
	}

	public List<VariationDoughEntity> findAllEnabled() {
		return doughRepository.findByEnabledTrueOrderByOrderAsc();
	}

	public Optional<VariationDoughEntity> findById(Integer id) {
		return doughRepository.findById(id);
	}

	public Optional<VariationDoughEntity> findByName(String name) {
		return doughRepository.findByNameIgnoreCase(name);
	}

	public VariationDoughEntity save(VariationDoughEntity dough) {
		if (dough.getId() != null) {
			findById(dough.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return doughRepository.save(dough);
	}
}
