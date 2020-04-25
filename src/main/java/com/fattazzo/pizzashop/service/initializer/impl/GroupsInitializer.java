package com.fattazzo.pizzashop.service.initializer.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.security.GroupEntity;
import com.fattazzo.pizzashop.entity.security.Role;
import com.fattazzo.pizzashop.service.group.GroupService;
import com.fattazzo.pizzashop.service.initializer.Initializer;

@Service
@Qualifier("groupsInitializer")
public class GroupsInitializer implements Initializer {

	@Autowired
	private GroupService groupService;

	@Override
	public void init() {
		// Customer
		Optional<GroupEntity> group = groupService.loadCustomerGroup();
		if (!group.isPresent()) {
			groupService.save(initCustomerGroup());
		}

		// Worker
		group = groupService.loadWorkerGroup();
		if (!group.isPresent()) {
			groupService.save(initWorkerGroup());
		}

		// Admin
		group = groupService.loadAdminGroup();
		if (!group.isPresent()) {
			groupService.save(initAdminGroup());
		}
	}

	private GroupEntity initAdminGroup() {
		return GroupEntity.builder().name(GroupEntity.NAME_ADMIN).roles(Role.getDefaultAdminRole()).readOnly(true)
				.build();
	}

	private GroupEntity initCustomerGroup() {
		return GroupEntity.builder().name(GroupEntity.NAME_CUSTOMER).roles(Role.getDefaultCustomerRole()).readOnly(true)
				.build();
	}

	private GroupEntity initWorkerGroup() {
		return GroupEntity.builder().name(GroupEntity.NAME_WORKER).roles(Role.getDefaultWorkerRole()).readOnly(true)
				.build();
	}

	@Override
	public int priority() {
		return 100;
	}

}
