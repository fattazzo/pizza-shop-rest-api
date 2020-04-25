package com.fattazzo.pizzashop.service.initializer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.data.Address;
import com.fattazzo.pizzashop.entity.data.BranchEntity;
import com.fattazzo.pizzashop.service.branch.BranchService;
import com.fattazzo.pizzashop.service.branch.BranchService.BranchPrimaryCheckException;
import com.fattazzo.pizzashop.service.initializer.Initializer;

@Service
public class BranchInitializer implements Initializer {

	@Autowired
	private BranchService branchService;

	@Override
	public void init() {
		if (!branchService.loadPrimary().isPresent()) {
			final Address address = Address.builder().streetAddress("street").number("1").place("Rome").build();
			final BranchEntity branch = BranchEntity.builder().enabled(true).primary(true).address(address).build();

			try {
				branchService.save(branch);
			} catch (final BranchPrimaryCheckException e) {
				// No primary branches on db
			}
		}
	}

	@Override
	public int priority() {
		return 400;
	}

}
