package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.PizzacategoriesApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Category;
import com.fattazzo.pizzashop.model.api.CategoryDetails;
import com.fattazzo.pizzashop.model.entity.CategoryEntity;
import com.fattazzo.pizzashop.model.entity.ItemType;
import com.fattazzo.pizzashop.service.category.CategoryService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "pizzacategories" })
public class PizzaCategoriesController implements PizzacategoriesApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private CategoryService categoryService;

	private final HttpServletRequest request;

	@Autowired
	public PizzaCategoriesController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<CategoryDetails> createPizzaCategory(CategoryDetails body) {
		final CategoryEntity existingEntity = categoryService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("category.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("category.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		CategoryEntity category = mapper.map(body, CategoryEntity.class);
		category.setType(ItemType.PIZZA);

		category = categoryService.save(category);

		return new ResponseEntity<>(mapper.map(category, CategoryDetails.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deletePizzaCategory(Integer categoryId) {
		categoryService.deleteById(categoryId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Category>> getPizzaCategories(Boolean includeDisabled) {
		final List<CategoryEntity> entities = (BooleanUtils.isTrue(includeDisabled))
				? categoryService.findByTypeOrderByOrder(ItemType.PIZZA)
				: categoryService.findByTypeAndEnabledTrueOrderByOrder(ItemType.PIZZA);

		final List<Category> categories = entities.stream().map(pc -> mapper.map(pc, Category.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(categories);
	}

	@Override
	public ResponseEntity<CategoryDetails> getPizzaCategory(Integer categoryId) {
		final CategoryEntity entity = categoryService.findById(categoryId).orElseThrow(NoSuchEntityException::new);

		if (entity.getType() != ItemType.PIZZA) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("category.load.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("category.load.failed.wrongType", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		return ResponseEntity.ok(mapper.map(entity, CategoryDetails.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<CategoryDetails> updatePizzaCategory(CategoryDetails body, Integer categoryId) {
		final CategoryEntity existingEntity = categoryService.findById(categoryId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("category.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final CategoryEntity category = categoryService.save(mapper.map(body, CategoryEntity.class));

		final CategoryDetails categoryDetails = mapper.map(category, CategoryDetails.class);

		return ResponseEntity.ok(categoryDetails);
	}

}
