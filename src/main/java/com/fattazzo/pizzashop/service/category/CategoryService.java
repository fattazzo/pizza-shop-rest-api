package com.fattazzo.pizzashop.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.CategoryEntity;
import com.fattazzo.pizzashop.model.entity.ItemType;
import com.fattazzo.pizzashop.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		categoryRepository.deleteById(id);
	}

	public List<CategoryEntity> findAll() {
		return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
	}

	public List<CategoryEntity> findAllEnabled() {
		return categoryRepository.findByEnabledTrueOrderByOrder();
	}

	public Optional<CategoryEntity> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	public Optional<CategoryEntity> findByName(String name) {
		return categoryRepository.findByNameIgnoreCase(name);
	}

	public List<CategoryEntity> findByTypeAndEnabledTrueOrderByOrder(ItemType type) {
		return categoryRepository.findByTypeAndEnabledTrueOrderByOrder(type);
	}

	public List<CategoryEntity> findByTypeOrderByOrder(ItemType type) {
		return categoryRepository.findByTypeOrderByOrder(type);
	}

	public CategoryEntity save(CategoryEntity category) {
		if (category.getId() != null) {
			findById(category.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return categoryRepository.save(category);
	}
}
