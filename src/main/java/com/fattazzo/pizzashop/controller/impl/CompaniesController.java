package com.fattazzo.pizzashop.controller.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.CompanyApi;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.entity.CompanyEntity;
import com.fattazzo.pizzashop.service.company.CompanyService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

@RestController
public class CompaniesController implements CompanyApi {

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private CompanyService companyService;

	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	@Override
	public ResponseEntity<CompanyEntity> getCompany() {
		final Optional<CompanyEntity> company = companyService.load();

		if (!company.isPresent()) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("company.load.failed", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("company.load.notfound", null))
					.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(company.get());
	}

	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	@Override
	public ResponseEntity<CompanyEntity> updateCompany(@Valid CompanyEntity body) {
		final CompanyEntity company = companyService.save(body);
		return ResponseEntity.ok(company);
	}

}
