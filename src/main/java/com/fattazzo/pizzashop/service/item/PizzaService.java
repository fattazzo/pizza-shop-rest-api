package com.fattazzo.pizzashop.service.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.ItemPizzaEntity;
import com.fattazzo.pizzashop.repository.ItemPizzaRepository;

@Service
public class PizzaService {

	@Autowired
	private ItemPizzaRepository pizzaRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		pizzaRepository.deleteById(id);
	}

	public List<ItemPizzaEntity> findAll() {
		return pizzaRepository.findAllByOrderByNameAsc();
	}

	public List<ItemPizzaEntity> findAllByCategory(Integer categoryId) {
		return pizzaRepository.findByCategoryIdOrderByNameAsc(categoryId);
	}

	public List<ItemPizzaEntity> findAllEnabled() {
		return pizzaRepository.findByEnabledTrueOrderByNameAsc();
	}

	public Optional<ItemPizzaEntity> findById(Integer id) {
		return pizzaRepository.findById(id);
	}

	public ItemPizzaEntity save(ItemPizzaEntity entity) {
		if (entity.getId() != null) {
			findById(entity.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return pizzaRepository.save(entity);
	}
}
