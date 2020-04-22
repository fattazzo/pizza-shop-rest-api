package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.BranchesApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.Branch;
import com.fattazzo.pizzashop.model.dto.BranchDetails;
import com.fattazzo.pizzashop.model.dto.ShippingZone;
import com.fattazzo.pizzashop.model.entity.BranchEntity;
import com.fattazzo.pizzashop.model.entity.ShippingZoneEntity;
import com.fattazzo.pizzashop.service.branch.BranchService;
import com.fattazzo.pizzashop.service.branch.BranchService.BranchPrimaryCheckException;
import com.fattazzo.pizzashop.service.branch.ShippingZoneService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

@RestController
public class BranchesController implements BranchesApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private BranchService branchService;

	@Autowired
	private ShippingZoneService shippingZoneService;

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<BranchDetails> createBranch(@Valid BranchDetails body) {

		BranchEntity branch = mapper.map(body, BranchEntity.class);

		try {
			branch = branchService.save(branch);
		} catch (final BranchPrimaryCheckException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("branch.insert.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("branch.failed.primaryCheck", null))
					.status(HttpStatus.CONFLICT).build();
		}

		return new ResponseEntity<>(mapper.map(branch, BranchDetails.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<ShippingZone> createShippingZone(@Valid ShippingZone body, Integer branchId) {
		ShippingZoneEntity zone = mapper.map(body, ShippingZoneEntity.class);

		zone = shippingZoneService.save(zone);

		return new ResponseEntity<>(mapper.map(zone, ShippingZone.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<Void> deleteBranch(Integer branchId) {

		try {
			branchService.deleteById(branchId);
		} catch (final BranchPrimaryCheckException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("branch.delete.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("branch.failed.primaryCheck", null))
					.status(HttpStatus.CONFLICT).build();
		}

		return ResponseEntity.noContent().build();
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<Void> deleteShippingZone(Integer shippingzoneId, Integer branchId) {
		shippingZoneService.deleteById(shippingzoneId);
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
	public ResponseEntity<ShippingZone> getShippingZone(Integer shippingzoneId, Integer branchId) {
		final ShippingZoneEntity entity = shippingZoneService.findById(shippingzoneId)
				.orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, ShippingZone.class));
	}

	@Override
	public ResponseEntity<List<ShippingZone>> getShippingZones(Integer branchId) {
		final List<ShippingZone> zones = shippingZoneService.findAll().stream()
				.map(sz -> mapper.map(sz, ShippingZone.class)).collect(Collectors.toList());
		return ResponseEntity.ok(zones);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	public ResponseEntity<BranchDetails> updateBranch(@Valid BranchDetails body, Integer branchId) {
		final BranchEntity existingEntity = branchService.findById(branchId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("branch.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("branch.update.failed.idParamNotEquals", null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		BranchEntity entity;
		try {
			entity = branchService.save(mapper.map(body, BranchEntity.class));
		} catch (final BranchPrimaryCheckException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("branch.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("branch.failed.primaryCheck", null))
					.status(HttpStatus.CONFLICT).build();
		}

		return ResponseEntity.ok(mapper.map(entity, BranchDetails.class));
	}

	@Override
	public ResponseEntity<ShippingZone> updateShippingZone(@Valid ShippingZone body, Integer shippingzoneId,
			Integer branchId) {
		final ShippingZoneEntity existingEntity = shippingZoneService.findById(shippingzoneId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("shippingzone.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("shippingzone.update.failed.idParamNotEquals",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final ShippingZoneEntity entity = shippingZoneService.save(mapper.map(body, ShippingZoneEntity.class));

		return ResponseEntity.ok(mapper.map(entity, ShippingZone.class));
	}

}