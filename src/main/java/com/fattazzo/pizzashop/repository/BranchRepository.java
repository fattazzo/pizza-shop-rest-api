package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.BranchEntity;

public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	Optional<BranchEntity> findOneByPrimary(boolean primary);
}
