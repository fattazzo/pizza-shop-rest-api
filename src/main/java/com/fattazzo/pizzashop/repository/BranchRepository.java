package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.data.BranchEntity;

public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	Optional<BranchEntity> findOneByPrimary(boolean primary);
}
