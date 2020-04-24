package com.fattazzo.pizzashop.service.initializer.impl;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.CompanyLogoEntity;
import com.fattazzo.pizzashop.repository.CompanyLogoRepository;
import com.fattazzo.pizzashop.service.company.CompanyLogoService;
import com.fattazzo.pizzashop.service.initializer.Initializer;

@Service
@Qualifier("companiesLogoInitializer")
public class CompaniesLogoInitializer implements Initializer {

	@Autowired
	private CompanyLogoService companyLogoService;

	@Autowired
	private CompanyLogoRepository companyLogoRepository;

	@Value("classpath:images/pizza.png")
	Resource logoFile;

	@Override
	public void init() {

		if (!companyLogoService.load().isPresent()) {

			CompanyLogoEntity logo;
			try {
				logo = CompanyLogoEntity.builder().value(Files.readAllBytes(logoFile.getFile().toPath())).build();
				companyLogoRepository.save(logo);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int priority() {
		return 400;
	}

}
