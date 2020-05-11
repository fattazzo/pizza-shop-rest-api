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

import com.fattazzo.pizzashop.controller.api.PizzavariationsApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Topping;
import com.fattazzo.pizzashop.model.api.VariationDough;
import com.fattazzo.pizzashop.model.api.VariationSize;
import com.fattazzo.pizzashop.model.entity.ToppingEntity;
import com.fattazzo.pizzashop.model.entity.VariationDoughEntity;
import com.fattazzo.pizzashop.model.entity.VariationSizeEntity;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.variation.DoughService;
import com.fattazzo.pizzashop.service.variation.SizeService;
import com.fattazzo.pizzashop.service.variation.ToppingService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "pizzavariations" })
public class PizzaVariationsController implements PizzavariationsApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private DoughService doughService;

	@Autowired
	private SizeService sizeService;

	@Autowired
	private ToppingService toppingService;

	private final HttpServletRequest request;

	@Autowired
	public PizzaVariationsController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<VariationDough> createDough(@Valid VariationDough body) {
		final VariationDoughEntity existingEntity = doughService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("variation.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		VariationDoughEntity dough = mapper.map(body, VariationDoughEntity.class);

		dough = doughService.save(dough);

		return new ResponseEntity<>(mapper.map(dough, VariationDough.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<VariationSize> createSize(@Valid VariationSize body) {
		final VariationSizeEntity existingEntity = sizeService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("variation.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		VariationSizeEntity size = mapper.map(body, VariationSizeEntity.class);

		size = sizeService.save(size);

		return new ResponseEntity<>(mapper.map(size, VariationSize.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Topping> createTopping(@Valid Topping body) {
		final ToppingEntity existingEntity = toppingService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("variation.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		ToppingEntity topping = mapper.map(body, ToppingEntity.class);

		topping = toppingService.save(topping);

		return new ResponseEntity<>(mapper.map(topping, Topping.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Void> deleteDough(Integer doughId) {
		doughService.deleteById(doughId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Void> deleteSize(Integer sizeId) {
		sizeService.deleteById(sizeId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Void> deleteTopping(Integer toppingId) {
		toppingService.deleteById(toppingId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<VariationDough> getDough(Integer doughId) {
		final VariationDoughEntity entity = doughService.findById(doughId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, VariationDough.class));
	}

	@Override
	public ResponseEntity<List<VariationDough>> getDoughs(@Valid Boolean includeDisabled) {
		final List<VariationDoughEntity> entities = (BooleanUtils.isTrue(includeDisabled)) ? doughService.findAll()
				: doughService.findAllEnabled();

		final List<VariationDough> doughs = entities.stream().map(d -> mapper.map(d, VariationDough.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(doughs);
	}

	@Override
	public ResponseEntity<VariationSize> getSize(Integer sizeId) {
		final VariationSizeEntity entity = sizeService.findById(sizeId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, VariationSize.class));
	}

	@Override
	public ResponseEntity<List<VariationSize>> getSizes(@Valid Boolean includeDisabled) {
		final List<VariationSizeEntity> entities = (BooleanUtils.isTrue(includeDisabled)) ? sizeService.findAll()
				: sizeService.findAllEnabled();

		final List<VariationSize> sizes = entities.stream().map(s -> mapper.map(s, VariationSize.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(sizes);
	}

	@Override
	public ResponseEntity<Topping> getTopping(Integer toppingId) {
		final ToppingEntity entity = toppingService.findById(toppingId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, Topping.class));
	}

	@Override
	public ResponseEntity<List<Topping>> getToppings(@Valid Boolean includeDisabled) {
		final List<ToppingEntity> entities = (BooleanUtils.isTrue(includeDisabled)) ? toppingService.findAll()
				: toppingService.findAllEnabled();

		final List<Topping> toppings = entities.stream().map(t -> mapper.map(t, Topping.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(toppings);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<VariationDough> updateDough(@Valid VariationDough body, Integer doughId) {
		final VariationDoughEntity existingEntity = doughService.findById(doughId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final VariationDoughEntity dough = doughService.save(mapper.map(body, VariationDoughEntity.class));
		return ResponseEntity.ok(mapper.map(dough, VariationDough.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<VariationSize> updateSize(@Valid VariationSize body, Integer sizeId) {
		final VariationSizeEntity existingEntity = sizeService.findById(sizeId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final VariationSizeEntity size = sizeService.save(mapper.map(body, VariationSizeEntity.class));
		return ResponseEntity.ok(mapper.map(size, VariationSize.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Topping> updateTopping(@Valid Topping body, Integer toppingId) {
		final ToppingEntity existingEntity = toppingService.findById(toppingId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("variation.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final ToppingEntity topping = toppingService.save(mapper.map(body, ToppingEntity.class));
		return ResponseEntity.ok(mapper.map(topping, Topping.class));
	}

}
