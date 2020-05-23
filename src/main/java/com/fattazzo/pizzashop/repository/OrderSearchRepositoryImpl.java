package com.fattazzo.pizzashop.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.fattazzo.pizzashop.model.api.OrderSearchParameters;
import com.fattazzo.pizzashop.model.api.OrderSearchResult;

@Repository
public class OrderSearchRepositoryImpl implements OrderSearchRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<OrderSearchResult> findOrdersByParams(OrderSearchParameters params, Optional<String> customerUserName) {

		// @formatter:off
		final StringBuilder qlString = new StringBuilder();
		qlString.append("select oe.id as id,");
		qlString.append("             oe.backofficeNote as backofficeNote,");
		qlString.append("             customer.username as customerUserName,");
		qlString.append("             oe.customerNote as customerNote, ");
		qlString.append("             oe.dateCreation as dateCreation,");
		qlString.append("             oe.dateRequest as dateRequest, ");
		qlString.append("             oe.dateRequestConfirmed as dateRequestConfirmed, ");
		qlString.append("             oe.deliveryAddress.number as deliveryAddressNumber, ");
		qlString.append("             oe.deliveryAddress.streetAddress as deliveryAddressStreet, ");
		qlString.append("             oe.deliveryAddress.place as deliveryAddressPlace, ");
		qlString.append("             oe.deliveryAddress.postalCode as deliveryAddressPostalCode, ");
		qlString.append("             sm.title as shippingMethod, ");
		qlString.append("             oe.shippingType as shippingType, ");
		qlString.append("             oe.state as state, ");
		qlString.append("             oe.total as total, ");
		qlString.append("             oe.transactionId as transactionId ");
		qlString.append("from OrderEntity oe inner join oe.customer as customer ");
		qlString.append("                                        inner join oe.shippingMethod as sm ");
		qlString.append("where 1 = 1 ");
		// @formatter:on

		if (customerUserName.isPresent()) {
			qlString.append(" and customer.username = :paramcustomerUsername ");
		}

		if (CollectionUtils.isNotEmpty(params.getStates())) {
			qlString.append(" and oe.state in (:paramStates)");
		}

		final Query query = entityManager.createQuery(qlString.toString());
		if (customerUserName.isPresent()) {
			query.setParameter("paramcustomerUsername", customerUserName.get());
		}

		if (CollectionUtils.isNotEmpty(params.getStates())) {
			query.setParameter("paramStates", params.getStates());
		}

		return query.unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(Transformers.aliasToBean(OrderSearchResult.class)).getResultList();
	}

}
