package com.fattazzo.pizzashop.service.initializer.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.entity.GroupEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.model.entity.UserType;
import com.fattazzo.pizzashop.model.entity.UserEntity.UserStatus;
import com.fattazzo.pizzashop.service.group.GroupService;
import com.fattazzo.pizzashop.service.initializer.Initializer;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
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

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@Override
	public void init() {
		final Optional<UserEntity> user = userService.findAdminUser();
		if (!user.isPresent()) {
			final Optional<GroupEntity> adminGroup = groupService.loadAdminGroup();
			if (!adminGroup.isPresent()) {
				throw RestException.newBuilder()
						.title(localeUtilsMessage.getMessage("ApplicationInitializzation.title", null))
						.detail("Admin group not found").status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

			userService.save(initAdminUser(adminGroup.get()));
		}
	}

	private UserEntity initAdminUser(GroupEntity group) {
		return UserEntity.builder().username("admin").password(passwordEncoder.encode("admin")).email("admin@email.com")
				.type(UserType.ADMIN).groups(Arrays.asList(group)).status(UserStatus.ACTIVE).readOnly(true).build();
	}

	@Override
	public int priority() {
		return 200;
	}

}
