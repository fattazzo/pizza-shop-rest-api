package com.fattazzo.pizzashop.service.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.data.CompanyEntity;
import com.fattazzo.pizzashop.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public Optional<CompanyEntity> load() {

		final List<CompanyEntity> companies = companyRepository.findAll();

		return companies.isEmpty() ? Optional.ofNullable(null) : Optional.of(companies.get(0));
	}

	public CompanyEntity save(CompanyEntity company) {
		return companyRepository.save(company);
	}

}
