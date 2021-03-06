package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.UsersApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.DeliveryAddress;
import com.fattazzo.pizzashop.model.api.User;
import com.fattazzo.pizzashop.model.api.UserDetails;
import com.fattazzo.pizzashop.model.entity.UserDeliveryAddressEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity.UserStatus;
import com.fattazzo.pizzashop.model.entity.UserType;
import com.fattazzo.pizzashop.security.JwtUser;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.user.UserService;
import com.fattazzo.pizzashop.service.user.UserService.UserReadonlyException;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "users" })
public class UsersController implements UsersApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserService userService;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final HttpServletRequest request;

	@Autowired
	public UsersController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY'})")
	public ResponseEntity<UserDetails> createUser(@Valid UserDetails body) {

		final UserEntity existingUser = userService.findByUsername(body.getUsername()).orElse(null);
		if (existingUser != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("user.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("user.insert.failed.alreadyexist",
							new Object[] { existingUser.getUsername() }, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		UserEntity user = mapper.map(body, UserEntity.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setType(UserType.WORKER);
		user.setStatus(UserStatus.ACTIVE);

		user = userService.save(user);

		final UserDetails userDetails = mapper.map(user, UserDetails.class);
		userDetails.setPassword(null);

		return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY','EDIT_ACCOUNT'})")
	public ResponseEntity<Void> deleteUser(String userName) {

		try {
			userService.delete(userName);
		} catch (final UserReadonlyException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("user.delete.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("user.delete.failed.readonly",
							new Object[] { e.getMessage() }, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<UserDetails> getUser(String userName) {
		final UserEntity user = userService.findByUsername(userName).orElseThrow(NoSuchEntityException::new);

		final UserDetails userDetails = mapper.map(user, UserDetails.class);
		userDetails.setPassword(null);
		return ResponseEntity.ok(userDetails);
	}

	@Override
	public ResponseEntity<List<User>> getUsers() {
		final List<User> users = userService.findAll().stream().map(u -> mapper.map(u, User.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(users);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY','EDIT_ACCOUNT'})")
	public ResponseEntity<UserDetails> updateUser(@Valid UserDetails body, String userName) {
		final UserEntity existingUser = userService.findByUsername(userName).orElseThrow(NoSuchEntityException::new);

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserEntity loggedUser = userService.findByUsername(((JwtUser) auth.getPrincipal()).getUsername())
				.orElseThrow(NoSuchEntityException::new);
		if (loggedUser.getType() != UserType.ADMIN && !existingUser.getUsername().equals(loggedUser.getUsername())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("user.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("user.update.failed.noUpdateOtherUsers", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		if (!existingUser.getUsername().equals(body.getUsername())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("user.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("user.update.failed.usernamecannotchange", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		UserEntity user = mapper.map(body, UserEntity.class);
		if (loggedUser.getType() == UserType.ADMIN) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		} else {
			user.setPassword(existingUser.getPassword());
		}
		user.setType(existingUser.getType());
		for (final DeliveryAddress address : CollectionUtils.emptyIfNull(body.getDeliveryAddresses())) {
			final UserDeliveryAddressEntity addressEntity = mapper.map(address, UserDeliveryAddressEntity.class);
			addressEntity.setParent(user);
			user.getDeliveryAddresses().add(addressEntity);
		}
		user = userService.save(user);

		final UserDetails userDetails = mapper.map(user, UserDetails.class);
		userDetails.setPassword(null);

		return ResponseEntity.ok(userDetails);
	}

}
