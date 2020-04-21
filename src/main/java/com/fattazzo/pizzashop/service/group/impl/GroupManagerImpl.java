package com.fattazzo.pizzashop.service.group.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.Group;
import com.fattazzo.pizzashop.repository.GroupRepository;
import com.fattazzo.pizzashop.service.group.GroupManager;

@Service
public class GroupManagerImpl implements GroupManager {

	protected static final Logger logger = LoggerFactory.getLogger(GroupManagerImpl.class);

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public Group loadAdminGroup() {
		return groupRepository.findByNameIgnoreCase(Group.NAME_ADMIN);
	}

	@Override
	public Group loadCustomerGroup() {
		return groupRepository.findByNameIgnoreCase(Group.NAME_CUSTOMER);
	}

	@Override
	public Group loadWorkerGroup() {
		return groupRepository.findByNameIgnoreCase(Group.NAME_WORKER);
	}

	@Override
	public Group save(Group group) {
		return groupRepository.save(group);
	}

}
