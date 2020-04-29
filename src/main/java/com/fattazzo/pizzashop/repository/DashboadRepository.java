package com.fattazzo.pizzashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fattazzo.pizzashop.model.api.DashBoardProducts;
import com.fattazzo.pizzashop.model.api.DashboardCustomers;

@Repository
public class DashboadRepository {

	@Autowired
	private EntityManager entityManager;

	public DashboardCustomers loadCustomersStats() {

		final String qlString = "Select count(ue.id) as total,"
				+ "sum(case when ue.type = com.fattazzo.pizzashop.entity.security.UserType.CUSTOMER then 1 else 0 end) as toConfirm "
				+ "from UserEntity ue";

		final Object[] result = (Object[]) entityManager.createQuery(qlString).getSingleResult();

		return new DashboardCustomers().total(((Long) result[0]).intValue()).toConfirm(((Long) result[1]).intValue());
	}

	public DashBoardProducts loadProductsStats() {

		String qlString = "Select count(pe.id) as total from ProductEntity pe where pe.enabled = true";
		final Object totalProducts = entityManager.createQuery(qlString).getSingleResult();

		qlString = "Select count(cat.id) as total from ProductEntity pe inner join pe.category cat where pe.enabled = true group by cat";
		@SuppressWarnings("unchecked")
		final List<Object> totalCategories = entityManager.createQuery(qlString).getResultList();

		return new DashBoardProducts().totalEnable(((Long) totalProducts).intValue())
				.totalCategoriesEnable(totalCategories.size());
	}
}
