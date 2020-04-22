package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

	CompanyEntity findByNameIgnoreCase(String username);

}
