package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.CategoriesApi;
import com.fattazzo.pizzashop.model.api.Category;
import com.fattazzo.pizzashop.model.entity.CategoryEntity;
import com.fattazzo.pizzashop.service.category.CategoryService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "categories" })
public class CategoriesController implements CategoriesApi {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ResponseEntity<List<Category>> getCategories(Boolean includeDisabled) {
		final List<CategoryEntity> entities = (BooleanUtils.isTrue(includeDisabled)) ? categoryService.findAll()
				: categoryService.findAllEnabled();

		final List<Category> categories = entities.stream().map(ce -> mapper.map(ce, Category.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(categories);
	}

}
