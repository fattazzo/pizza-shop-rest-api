package com.fattazzo.pizzashop.controller.impl;

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

import com.fattazzo.pizzashop.controller.api.VariationsApi;
import com.fattazzo.pizzashop.entity.data.DoughEntity;
import com.fattazzo.pizzashop.entity.data.SizeEntity;
import com.fattazzo.pizzashop.entity.data.ToppingEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Dough;
import com.fattazzo.pizzashop.model.api.Size;
import com.fattazzo.pizzashop.model.api.Topping;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.variation.DoughService;
import com.fattazzo.pizzashop.service.variation.SizeService;
import com.fattazzo.pizzashop.service.variation.ToppingService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "variations" })
public class VariationsController implements VariationsApi {

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

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Dough> createDough(@Valid Dough body) {
		final DoughEntity existingEntity = doughService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("variation.insert.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("variation.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		DoughEntity dough = mapper.map(body, DoughEntity.class);

		dough = doughService.save(dough);

		return new ResponseEntity<>(mapper.map(dough, Dough.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Size> createSize(@Valid Size body) {
		final SizeEntity existingEntity = sizeService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("variation.insert.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("variation.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		SizeEntity size = mapper.map(body, SizeEntity.class);

		size = sizeService.save(size);

		return new ResponseEntity<>(mapper.map(size, Size.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Topping> createTopping(@Valid Topping body) {
		final ToppingEntity existingEntity = toppingService.findByName(body.getName()).orElse(null);
		if (existingEntity != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("variation.insert.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("variation.insert.failed.alreadyexist",
							new Object[] { existingEntity.getName() }))
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
	public ResponseEntity<Dough> getDough(Integer doughId) {
		final DoughEntity entity = doughService.findById(doughId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, Dough.class));
	}

	@Override
	public ResponseEntity<List<Dough>> getDoughs(@Valid Boolean includeDisabled) {
		final List<DoughEntity> entities = (BooleanUtils.isTrue(includeDisabled)) ? doughService.findAll()
				: doughService.findAllEnabled();

		final List<Dough> doughs = entities.stream().map(d -> mapper.map(d, Dough.class)).collect(Collectors.toList());
		return ResponseEntity.ok(doughs);
	}

	@Override
	public ResponseEntity<Size> getSize(Integer sizeId) {
		final SizeEntity entity = sizeService.findById(sizeId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, Size.class));
	}

	@Override
	public ResponseEntity<List<Size>> getSizes(@Valid Boolean includeDisabled) {
		final List<SizeEntity> entities = (BooleanUtils.isTrue(includeDisabled)) ? sizeService.findAll()
				: sizeService.findAllEnabled();

		final List<Size> sizes = entities.stream().map(s -> mapper.map(s, Size.class)).collect(Collectors.toList());
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
	public ResponseEntity<Dough> updateDough(@Valid Dough body, Integer doughId) {
		final DoughEntity existingEntity = doughService.findById(doughId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("variation.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("variation.update.failed.idParamNotEquals",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final DoughEntity dough = doughService.save(mapper.map(body, DoughEntity.class));
		return ResponseEntity.ok(mapper.map(dough, Dough.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Size> updateSize(@Valid Size body, Integer sizeId) {
		final SizeEntity existingEntity = sizeService.findById(sizeId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("variation.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("variation.update.failed.idParamNotEquals",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final SizeEntity size = sizeService.save(mapper.map(body, SizeEntity.class));
		return ResponseEntity.ok(mapper.map(size, Size.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<Topping> updateTopping(@Valid Topping body, Integer toppingId) {
		final ToppingEntity existingEntity = toppingService.findById(toppingId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("variation.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("variation.update.failed.idParamNotEquals",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final ToppingEntity topping = toppingService.save(mapper.map(body, ToppingEntity.class));
		return ResponseEntity.ok(mapper.map(topping, Topping.class));
	}

}
