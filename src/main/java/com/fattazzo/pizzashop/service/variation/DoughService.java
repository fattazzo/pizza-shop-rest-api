package com.fattazzo.pizzashop.service.variation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.DoughEntity;
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

	public List<DoughEntity> findAll() {
		return doughRepository.findAllByOrderByOrderAsc();
	}

	public List<DoughEntity> findAllEnabled() {
		return doughRepository.findByEnabledTrueOrderByOrderAsc();
	}

	public Optional<DoughEntity> findById(Integer id) {
		return doughRepository.findById(id);
	}

	public Optional<DoughEntity> findByName(String name) {
		return doughRepository.findByNameIgnoreCase(name);
	}

	public DoughEntity save(DoughEntity dough) {
		if (dough.getId() != null) {
			findById(dough.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return doughRepository.save(dough);
	}
}
