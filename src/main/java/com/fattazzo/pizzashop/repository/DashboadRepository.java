package com.fattazzo.pizzashop.repository;

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
				+ "sum(case when ue.status = com.fattazzo.pizzashop.model.entity.UserEntity$UserStatus.TOCONFIRM then 1 else 0 end) as toConfirm "
				+ "from UserEntity ue " + "where ue.type = com.fattazzo.pizzashop.model.entity.UserType.CUSTOMER";

		final Object[] result = (Object[]) entityManager.createQuery(qlString).getSingleResult();

		final Long total = result[0] == null ? 0 : (Long) result[0];
		final Long toConfirm = result[1] == null ? 0 : (Long) result[1];

		return new DashboardCustomers().total(total.intValue()).toConfirm(toConfirm.intValue());
	}

	public DashBoardPizzas loadPizzasStats() {

		String qlString = "Select count(pe.id) as total from ItemPizzaEntity pe where pe.enabled = true";
		final Object totalPizzas = entityManager.createQuery(qlString).getSingleResult();

		qlString = "Select count(cat.id) as total from CategoryEntity cat where cat.enabled = true and cat.type = com.fattazzo.pizzashop.model.entity.ItemType.PIZZA";
		final Object totalCategories = entityManager.createQuery(qlString).getSingleResult();

		final Long pizzas = totalPizzas == null ? 0 : (Long) totalPizzas;
		final Long categories = totalCategories == null ? 0 : (Long) totalCategories;

		return new DashBoardPizzas().totalEnable(pizzas.intValue()).totalCategoriesEnable(categories.intValue());
	}
}
