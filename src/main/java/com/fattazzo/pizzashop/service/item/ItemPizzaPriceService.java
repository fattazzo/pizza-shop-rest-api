package com.fattazzo.pizzashop.service.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.ItemPizzaPriceEntity;
import com.fattazzo.pizzashop.repository.ItemPizzaPriceRepository;

@Service
public class ItemPizzaPriceService {

	@Autowired
	private ItemPizzaPriceRepository itemPizzaPriceRepository;

	public Optional<ItemPizzaPriceEntity> findById(Integer id) {
		return itemPizzaPriceRepository.findById(id);
	}
}
