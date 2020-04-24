package com.fattazzo.pizzashop.service.variation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.SizeEntity;
import com.fattazzo.pizzashop.repository.SizeRepository;

@Service
public class SizeService {

	@Autowired
	private SizeRepository sizeRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		sizeRepository.deleteById(id);
	}

	public List<SizeEntity> findAll() {
		return sizeRepository.findAll();
	}

	public Optional<SizeEntity> findById(Integer id) {
		return sizeRepository.findById(id);
	}

	public Optional<SizeEntity> findByName(String name) {
		return sizeRepository.findByNameIgnoreCase(name);
	}

	public SizeEntity save(SizeEntity dough) {
		if (dough.getId() != null) {
			findById(dough.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return sizeRepository.save(dough);
	}
}
