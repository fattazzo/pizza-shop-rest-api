package com.fattazzo.pizzashop.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fattazzo.pizzashop.controller.api.CompaniesApi;
import com.fattazzo.pizzashop.entity.data.CompanyEntity;
import com.fattazzo.pizzashop.entity.data.CompanyLogoEntity;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Company;
import com.fattazzo.pizzashop.service.company.CompanyLogoService;
import com.fattazzo.pizzashop.service.company.CompanyService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "companies" })
public class CompaniesController implements CompaniesApi {

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyLogoService companyLogoService;

	@Autowired
	private ModelMapper mapper;

	private final HttpServletRequest request;

	@Autowired
	public CompaniesController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public ResponseEntity<Company> getCompany() {
		final Optional<CompanyEntity> companyEntity = companyService.load();

		if (!companyEntity.isPresent()) {
			throw RestException.newBuilder().title(localeUtilsMessage.getMessage("company.load.failed", null, request))
					.detail(localeUtilsMessage.getMessage("company.load.notfound", null, request))
					.status(HttpStatus.NOT_FOUND).build();
		}

		final Company company = mapper.map(companyEntity.get(), Company.class);
		company.setLogoUrl(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/logo");

		return ResponseEntity.ok(company);
	}

	@Override
	public ResponseEntity<Resource> getLogo() {

		final CompanyLogoEntity logo = companyLogoService.load().orElseThrow(NoSuchEntityException::new);

		final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(logo.getValue())) {
			@Override
			public long contentLength() {
				return logo.getValue().length;
			}

			@Override
			public String getFilename() {
				return "logo.png";
			}
		};
		return ResponseEntity.ok(resource);
	}

	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	@Override
	public ResponseEntity<Company> updateCompany(@Valid Company body) {

		CompanyEntity company = mapper.map(body, CompanyEntity.class);

		company = companyService.save(company);
		return ResponseEntity.ok(mapper.map(company, Company.class));
	}

	@PreAuthorize("@securityService.hasAnyPermission({'COMPANY'})")
	@Override
	public ResponseEntity<Void> updateLogo(@Valid MultipartFile file) {

		try {
			companyLogoService.update(file.getBytes());
		} catch (final IOException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("company.logo.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("company.logo.update.failed.detail", null, request))
					.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok().build();
	}

}
