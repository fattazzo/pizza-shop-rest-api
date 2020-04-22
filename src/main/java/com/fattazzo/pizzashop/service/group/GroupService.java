package com.fattazzo.pizzashop.service.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.GroupEntity;
import com.fattazzo.pizzashop.repository.GroupRepository;

@Service
public class GroupService {

	protected static final Logger logger = LoggerFactory.getLogger(GroupService.class);

	@Autowired
	private GroupRepository groupRepository;

	public GroupEntity loadAdminGroup() {
		return groupRepository.findByNameIgnoreCase(GroupEntity.NAME_ADMIN);
	}

	public GroupEntity loadCustomerGroup() {
		return groupRepository.findByNameIgnoreCase(GroupEntity.NAME_CUSTOMER);
	}

	public GroupEntity loadWorkerGroup() {
		return groupRepository.findByNameIgnoreCase(GroupEntity.NAME_WORKER);
	}

	public GroupEntity save(GroupEntity group) {
		return groupRepository.save(group);
	}

}
