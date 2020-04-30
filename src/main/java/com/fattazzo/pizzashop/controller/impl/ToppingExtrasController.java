package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.ToppingextrasApi;
import com.fattazzo.pizzashop.entity.data.ToppingExtraEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.variation.ToppingExtraService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "variations" })
public class ToppingExtrasController implements ToppingextrasApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private ToppingExtraService toppingExtraService;

	private final HttpServletRequest request;

	@Autowired
	public ToppingExtrasController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public ResponseEntity<ToppingExtra> getToppingExtra(Integer toppingextraId) {
		final ToppingExtraEntity entity = toppingExtraService.findById(toppingextraId)
				.orElseThrow(NoSuchEntityException::new);

		return ResponseEntity.ok(mapper.map(entity, ToppingExtra.class));
	}

	@Override
	public ResponseEntity<List<ToppingExtra>> getToppingExtras(Integer doughId, Integer sizeId) {
		final List<ToppingExtraEntity> entities = toppingExtraService.findAll(doughId, sizeId);

		final List<ToppingExtra> toppingExtras = entities.stream().map(te -> mapper.map(te, ToppingExtra.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(toppingExtras);
	}

	private ToppingExtraEntity update(ToppingExtra toppingExtra, Integer toppingExtraId) {
		final ToppingExtraEntity existingEntity = toppingExtraService.findById(toppingExtraId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(toppingExtra.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("toppingExtra.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		return toppingExtraService.save(mapper.map(toppingExtra, ToppingExtraEntity.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<ToppingExtra> updateToppingExtra(@Valid ToppingExtra body, Integer toppingextraId) {
		final ToppingExtraEntity entity = update(body, toppingextraId);
		return ResponseEntity.ok(mapper.map(entity, ToppingExtra.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'VARIATIONS'})")
	public ResponseEntity<List<ToppingExtra>> updateToppingExtras(@Valid List<ToppingExtra> body) {

		final List<ToppingExtra> toppingExtras = body.stream().map(te -> {
			final ToppingExtraEntity tee = update(te, te.getId());
			return mapper.map(tee, ToppingExtra.class);
		}).collect(Collectors.toList());

		return ResponseEntity.ok(toppingExtras);
	}

}
