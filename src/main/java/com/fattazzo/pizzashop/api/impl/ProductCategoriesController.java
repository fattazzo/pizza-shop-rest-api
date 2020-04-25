package com.fattazzo.pizzashop.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.api.ProductcategoriesApi;
import com.fattazzo.pizzashop.entity.data.ProductCategoryEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.data.ProductCategory;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.product.ProductCategoryService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "products" })
public class ProductCategoriesController implements ProductcategoriesApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ProductCategory> createProductCategory(@Valid ProductCategory body) {
		final ProductCategoryEntity existingEntity = productCategoryService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("productCategory.insert.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("productCategory.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		ProductCategoryEntity category = mapper.map(body, ProductCategoryEntity.class);

		category = productCategoryService.save(category);

		return new ResponseEntity<>(mapper.map(category, ProductCategory.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<Void> deleteProductCategory(Integer productcategoryId) {
		productCategoryService.deleteById(productcategoryId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<ProductCategory>> getProductCategories(@Valid Boolean includeDisabled) {
		final List<ProductCategoryEntity> entities = (BooleanUtils.isTrue(includeDisabled))
				? productCategoryService.findAll()
				: productCategoryService.findAllEnabled();

		final List<ProductCategory> categories = entities.stream().map(pc -> mapper.map(pc, ProductCategory.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(categories);
	}

	@Override
	public ResponseEntity<ProductCategory> getProductCategory(Integer productcategoryId) {
		final ProductCategoryEntity entity = productCategoryService.findById(productcategoryId)
				.orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, ProductCategory.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'PRODUCTS'})")
	public ResponseEntity<ProductCategory> updateProductCategory(@Valid ProductCategory body,
			Integer productcategoryId) {
		final ProductCategoryEntity existingEntity = productCategoryService.findById(productcategoryId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("productCategory.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("idParamNotEquals", null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final ProductCategoryEntity category = productCategoryService
				.save(mapper.map(body, ProductCategoryEntity.class));
		return ResponseEntity.ok(mapper.map(category, ProductCategory.class));
	}

}
