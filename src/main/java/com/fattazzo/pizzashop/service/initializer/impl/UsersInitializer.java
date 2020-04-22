package com.fattazzo.pizzashop.service.initializer.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.GroupEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity.UserStatusEnum;
import com.fattazzo.pizzashop.service.group.GroupService;
import com.fattazzo.pizzashop.service.initializer.Initializer;
import com.fattazzo.pizzashop.service.user.UserService;

@Service
@Qualifier("usersInitializer")
public class UsersInitializer implements Initializer {

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void init() {
		final Optional<UserEntity> user = userService.findAdminUser();
		if (!user.isPresent()) {
			userService.save(initAdminUser(groupService.loadAdminGroup()));
		}
	}

	private UserEntity initAdminUser(GroupEntity group) {
		return UserEntity.builder().username("admin").password(passwordEncoder.encode("admin")).email("admin@email.com")
				.groups(Arrays.asList(group)).status(UserStatusEnum.Active).readOnly(true).build();
	}

	@Override
	public int priority() {
		return 200;
	}

}
