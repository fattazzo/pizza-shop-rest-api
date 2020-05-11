package com.fattazzo.pizzashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fattazzo.pizzashop.model.api.DashBoardPizzas;
import com.fattazzo.pizzashop.model.api.DashboardCustomers;

@Repository
public class DashboadRepository {

	@Autowired
	private EntityManager entityManager;

	public DashboardCustomers loadCustomersStats() {

		final String qlString = "Select count(ue.id) as total,"
				+ "sum(case when ue.type = com.fattazzo.pizzashop.model.entity.UserType.CUSTOMER then 1 else 0 end) as toConfirm "
				+ "from UserEntity ue";

		final Object[] result = (Object[]) entityManager.createQuery(qlString).getSingleResult();

		return new DashboardCustomers().total(((Long) result[0]).intValue()).toConfirm(((Long) result[1]).intValue());
	}

	public DashBoardPizzas loadPizzasStats() {

		String qlString = "Select count(pe.id) as total from ItemPizzaEntity pe where pe.enabled = true";
		final Object totalProducts = entityManager.createQuery(qlString).getSingleResult();

		qlString = "Select count(cat.id) as total from CategoryEntity cat where cat.enabled = true and cat.type = com.fattazzo.pizzashop.model.entity.ItemType.PIZZA";
		@SuppressWarnings("unchecked")
		final List<Object> totalCategories = entityManager.createQuery(qlString).getResultList();

		return new DashBoardPizzas().totalEnable(((Long) totalProducts).intValue())
				.totalCategoriesEnable(totalCategories.size());
	}
}
