package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

	Optional<CompanyEntity> findByNameIgnoreCase(String username);

}
