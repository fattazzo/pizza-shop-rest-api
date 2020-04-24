package com.fattazzo.pizzashop.service.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.CompanyLogoEntity;
import com.fattazzo.pizzashop.repository.CompanyLogoRepository;

@Service
public class CompanyLogoService {

	@Autowired
	private CompanyLogoRepository companyLogoRepository;

	public Optional<CompanyLogoEntity> load() {

		final List<CompanyLogoEntity> logos = companyLogoRepository.findAll();

		return logos.isEmpty() ? Optional.ofNullable(null) : Optional.of(logos.get(0));
	}

	public void update(byte[] content) {
		final CompanyLogoEntity logo = load().orElseThrow(NoSuchEntityException::new);

		logo.setValue(content);
		companyLogoRepository.save(logo);
	}

}
