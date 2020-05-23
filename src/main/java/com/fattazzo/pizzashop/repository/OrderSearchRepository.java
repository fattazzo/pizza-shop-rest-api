package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import com.fattazzo.pizzashop.model.api.OrderSearchParameters;
import com.fattazzo.pizzashop.model.api.OrderSearchResult;

public interface OrderSearchRepository {

	List<OrderSearchResult> findOrdersByParams(OrderSearchParameters params, Optional<String> customerUserName);
}
