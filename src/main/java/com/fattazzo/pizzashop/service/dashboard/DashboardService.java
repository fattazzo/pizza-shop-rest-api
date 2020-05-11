package com.fattazzo.pizzashop.service.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.api.Dashboard;
import com.fattazzo.pizzashop.repository.DashboadRepository;

@Service
public class DashboardService {

	@Autowired
	private DashboadRepository dashboadRepository;

	public Dashboard load() {

		final Dashboard dashboard = new Dashboard().customers(dashboadRepository.loadCustomersStats())
				.pizzas(dashboadRepository.loadPizzasStats());

		return dashboard;
	}
}
