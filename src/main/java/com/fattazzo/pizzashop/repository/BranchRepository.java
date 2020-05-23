package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.BranchEntity;

public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	List<BranchEntity> findByShippingZonesName(String shippingZoneName);

	Optional<BranchEntity> findOneByPrimary(boolean primary);

}
