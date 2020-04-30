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

import com.fattazzo.pizzashop.controller.api.BranchesApi;
import com.fattazzo.pizzashop.entity.data.BranchEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Branch;
import com.fattazzo.pizzashop.model.api.BranchDetails;
import com.fattazzo.pizzashop.model.api.ShippingZone;
import com.fattazzo.pizzashop.service.branch.BranchService;
import com.fattazzo.pizzashop.service.branch.BranchService.BranchPrimaryCheckException;
import com.fattazzo.pizzashop.service.branch.ShippingZoneService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "branches" })
public class BranchesController implements BranchesApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private BranchService branchService;

	@Autowired
	private ShippingZoneService shippingZoneService;

	private final HttpServletRequest request;

	@Autowired
	public BranchesController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<BranchDetails> createBranch(@Valid BranchDetails body) {

		BranchEntity branch = mapper.map(body, BranchEntity.class);

		try {
			branch = branchService.save(branch);
		} catch (final BranchPrimaryCheckException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("branch.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("branch.failed.primaryCheck", null, request))
					.status(HttpStatus.CONFLICT).build();
		}

		return new ResponseEntity<>(mapper.map(branch, BranchDetails.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<Void> deleteBranch(Integer branchId) {

		try {
			branchService.deleteById(branchId);
		} catch (final BranchPrimaryCheckException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("branch.delete.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("branch.failed.primaryCheck", null, request))
					.status(HttpStatus.CONFLICT).build();
		}

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<BranchDetails> getBranch(Integer branchId) {
		final BranchEntity entity = branchService.findById(branchId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, BranchDetails.class));
	}

	@Override
	public ResponseEntity<List<Branch>> getBranches() {
		final List<Branch> branches = branchService.findAll().stream().map(b -> mapper.map(b, Branch.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(branches);
	}

	@Override
	public ResponseEntity<List<ShippingZone>> getShippingZones(Integer branchId) {
		final List<ShippingZone> zones = shippingZoneService.findByBranchId(branchId).stream()
				.map(sz -> mapper.map(sz, ShippingZone.class)).collect(Collectors.toList());
		return ResponseEntity.ok(zones);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<BranchDetails> updateBranch(@Valid BranchDetails body, Integer branchId) {
		final BranchEntity existingEntity = branchService.findById(branchId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("branch.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		BranchEntity entity;
		try {
			entity = branchService.save(mapper.map(body, BranchEntity.class));
		} catch (final BranchPrimaryCheckException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("branch.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("branch.failed.primaryCheck", null, request))
					.status(HttpStatus.CONFLICT).build();
		}

		return ResponseEntity.ok(mapper.map(entity, BranchDetails.class));
	}

}
