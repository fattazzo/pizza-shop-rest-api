package com.fattazzo.pizzashop.service.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.ItemEntity;
import com.fattazzo.pizzashop.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Optional<ItemEntity> findById(Integer id) {
		return itemRepository.findById(id);
	}
}
