package com.fattazzo.pizzashop.service.initializer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.CompanyEntity;
import com.fattazzo.pizzashop.service.company.CompanyService;
import com.fattazzo.pizzashop.service.initializer.Initializer;

@Service
@Qualifier("companiesInitializer")
public class CompaniesInitializer implements Initializer {

	@Autowired
	private CompanyService companyService;

	@Override
	public void init() {

		if (!companyService.load().isPresent()) {

			final CompanyEntity company = CompanyEntity.builder().name("Company name").webUrl("www.company.it").build();

			companyService.save(company);
		}
	}

	@Override
	public int priority() {
		return 300;
	}

}
