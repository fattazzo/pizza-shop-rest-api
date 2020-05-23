package com.fattazzo.pizzashop.service.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.ItemProductPriceEntity;
import com.fattazzo.pizzashop.repository.ItemProductPriceRepository;

@Service
public class ItemProductPriceService {

	@Autowired
	private ItemProductPriceRepository itemProductPriceRepository;

	public Optional<ItemProductPriceEntity> findById(Integer id) {
		return itemProductPriceRepository.findById(id);
	}
}
