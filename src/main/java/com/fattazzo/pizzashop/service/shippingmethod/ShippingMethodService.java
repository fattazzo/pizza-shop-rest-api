package com.fattazzo.pizzashop.service.shippingmethod;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.ShippingMethodEntity;
import com.fattazzo.pizzashop.repository.ShippingMethodRepository;

@Service
public class ShippingMethodService {

	protected static final Logger logger = LoggerFactory.getLogger(ShippingMethodService.class);

	@Autowired
	private ShippingMethodRepository shippingMethodRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);

		shippingMethodRepository.deleteById(id);
	}

	public List<ShippingMethodEntity> findAll() {
		return shippingMethodRepository.findAll();
	}

	public List<ShippingMethodEntity> findAllEnabled() {
		return shippingMethodRepository.findByEnabledTrue();
	}

	public Optional<ShippingMethodEntity> findById(Integer id) {
		return shippingMethodRepository.findById(id);
	}

	public ShippingMethodEntity save(ShippingMethodEntity method) {
		if (method.getId() != null) {
			findById(method.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return shippingMethodRepository.save(method);
	}

}
