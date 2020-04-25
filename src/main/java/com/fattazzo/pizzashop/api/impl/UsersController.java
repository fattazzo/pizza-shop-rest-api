package com.fattazzo.pizzashop.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.api.UsersApi;
import com.fattazzo.pizzashop.entity.security.UserEntity;
import com.fattazzo.pizzashop.entity.security.UserType;
import com.fattazzo.pizzashop.entity.security.UserEntity.UserStatus;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.security.User;
import com.fattazzo.pizzashop.model.dto.security.UserDetails;
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

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY'})")
	public ResponseEntity<UserDetails> createUser(@Valid UserDetails body) {

		final UserEntity existingUser = userService.findByUsername(body.getUsername()).orElse(null);
		if (existingUser != null) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("user.insert.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("user.insert.failed.alreadyexist",
							new Object[] { existingUser.getUsername() }))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		UserEntity user = mapper.map(body, UserEntity.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setType(UserType.WORKER);
		user.setStatus(UserStatus.Active);

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
					.title(localeUtilsMessage.getErrorLocalizedMessage("user.delete.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("user.delete.failed.readonly",
							new Object[] { e.getMessage() }))
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

		if (!existingUser.getUsername().equals(body.getUsername())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("user.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("user.update.failed.usernamecannotchange",
							null))
					.status(HttpStatus.BAD_REQUEST).build();

		}

		UserEntity user = mapper.map(body, UserEntity.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setType(UserType.WORKER);
		user = userService.save(user);

		final UserDetails userDetails = mapper.map(user, UserDetails.class);
		userDetails.setPassword(null);

		return ResponseEntity.ok(userDetails);
	}

}
