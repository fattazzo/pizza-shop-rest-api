package com.fattazzo.pizzashop.service.branch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.ShippingZoneEntity;
import com.fattazzo.pizzashop.repository.ShippingZoneRepository;

@Service
public class ShippingZoneService {

	@Autowired
	private ShippingZoneRepository shippingZoneRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);

		shippingZoneRepository.deleteById(id);
	}

	public List<ShippingZoneEntity> findByBranchId(Integer branchId) {
		return shippingZoneRepository.findByBranchId(branchId);
	}

	public Optional<ShippingZoneEntity> findById(Integer id) {
		return shippingZoneRepository.findById(id);
	}

	public ShippingZoneEntity save(ShippingZoneEntity shippingZone) {
		if (shippingZone.getId() != null) {
			findById(shippingZone.getId()).orElseThrow(NoSuchEntityException::new);
		}

		return shippingZoneRepository.save(shippingZone);
	}

}
