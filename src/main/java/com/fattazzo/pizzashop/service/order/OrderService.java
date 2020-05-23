package com.fattazzo.pizzashop.service.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.entity.OrderEntity;
import com.fattazzo.pizzashop.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public void deleteById(Integer id) {
		findById(id).orElseThrow(NoSuchEntityException::new);
		orderRepository.deleteById(id);
	}

	public Optional<OrderEntity> findById(Integer id) {
		return orderRepository.findById(id);
	}

	public OrderEntity save(OrderEntity order) {
		if (order.getId() != null) {
			findById(order.getId()).orElseThrow(NoSuchEntityException::new);
		}
		return orderRepository.save(order);
	}

}
