package com.fattazzo.pizzashop.service.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.api.OrderSearchParameters;
import com.fattazzo.pizzashop.model.api.OrderSearchResult;
import com.fattazzo.pizzashop.repository.OrderSearchRepository;

@Service
public class OrderSearchService {

	@Autowired
	private OrderSearchRepository orderSearchRepository;

	public List<OrderSearchResult> findOrdersByParams(OrderSearchParameters params, Optional<String> customerUserName) {
		return orderSearchRepository.findOrdersByParams(params, customerUserName);
	}
}
