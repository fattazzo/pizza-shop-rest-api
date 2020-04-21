package com.fattazzo.pizzashop.service.group;

import com.fattazzo.pizzashop.model.entity.Group;

public interface GroupManager {

	Group loadAdminGroup();

	Group loadCustomerGroup();

	Group loadWorkerGroup();

	Group save(Group group);
}
