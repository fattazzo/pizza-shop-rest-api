package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.ProductvariationsApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.VariationProduct;
import com.fattazzo.pizzashop.model.entity.VariationProductEntity;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.variation.VariationProductService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "productvariations" })
public class ProductVariationsController implements ProductvariationsApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private VariationProductService variationProductService;

	private final HttpServletRequest request;

	@Autowired
	public ProductVariationsController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<VariationProduct> createVariationProduct(@Valid VariationProduct body) {
		final VariationProductEntity existingEntity = variationProductService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("variation.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		VariationProductEntity entity = mapper.map(body, VariationProductEntity.class);

		entity = variationProductService.save(entity);

		return new ResponseEntity<>(mapper.map(entity, VariationProduct.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Void> deleteVariationProduct(Integer variationId) {
		variationProductService.deleteById(variationId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<VariationProduct> getVariationProduct(Integer variationId) {
		final VariationProductEntity entity = variationProductService.findById(variationId)
				.orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, VariationProduct.class));
	}

	@Override
	public ResponseEntity<List<VariationProduct>> getVariationProducts(@Valid Boolean includeDisabled) {
		final List<VariationProductEntity> entities = (BooleanUtils.isTrue(includeDisabled))
				? variationProductService.findAll()
				: variationProductService.findAllEnabled();

		final List<VariationProduct> variations = entities.stream().map(d -> mapper.map(d, VariationProduct.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(variations);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<VariationProduct> updateVariationProduct(@Valid VariationProduct body, Integer variationId) {
		final VariationProductEntity existingEntity = variationProductService.findById(variationId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final VariationProductEntity entity = variationProductService
				.save(mapper.map(body, VariationProductEntity.class));
		return ResponseEntity.ok(mapper.map(entity, VariationProduct.class));
	}

}
