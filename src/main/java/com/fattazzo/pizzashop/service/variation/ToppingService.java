package com.fattazzo.pizzashop.service.variation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.ToppingEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.repository.ToppingRepository;

@Service
public class ToppingService {

	@Autowired
	private ToppingRepository toppingRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		toppingRepository.deleteById(id);
	}

	public List<ToppingEntity> findAll() {
		return toppingRepository.findAll();
	}

	public List<ToppingEntity> findAllEnabled() {
		return toppingRepository.findByEnabledTrue();
	}

	public Optional<ToppingEntity> findById(Integer id) {
		return toppingRepository.findById(id);
	}

	public Optional<ToppingEntity> findByName(String name) {
		return toppingRepository.findByNameIgnoreCase(name);
	}

	public ToppingEntity save(ToppingEntity dough) {
		if (dough.getId() != null) {
			findById(dough.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return toppingRepository.save(dough);
	}
}
