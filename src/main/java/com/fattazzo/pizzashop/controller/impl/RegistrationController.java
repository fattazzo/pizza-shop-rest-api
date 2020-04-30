package com.fattazzo.pizzashop.controller.impl;

import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.config.LocaleResolver;
import com.fattazzo.pizzashop.controller.api.RegistrationApi;
import com.fattazzo.pizzashop.entity.security.UserEntity;
import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.exception.security.MailNotSentException;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.UserRegistrationInfo;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.user.UserService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "registration" })
public class RegistrationController implements RegistrationApi {

	@Autowired
	private UserService userService;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private LocaleResolver localeResolver;

	private final HttpServletRequest request;

	@Autowired
	public RegistrationController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public ResponseEntity<String> confirmRegistration(String username, String registrationToken) {
		if (StringUtils.isEmpty(registrationToken)) {

			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("confirm.registration.error", null, request))
					.detail(localeUtilsMessage.getMessage("confirm.registration.tokenRequired", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		try {
			userService.confirmRegistration(username, registrationToken);
		} catch (final ExpiredTokenException ex) {

			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("confirm.registration.error", null, request))
					.detail(localeUtilsMessage.getMessage("confirm.registration.tokenExpiredOrNotValid", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;

		} catch (final NoSuchEntityException ex) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("confirm.registration.error", null, request))
					.detail(localeUtilsMessage.getMessage("confirm.registration.invalid", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		return ResponseEntity.ok(localeUtilsMessage.getMessage("user.activated.success", null, request));
	}

	@Override
	public ResponseEntity<Void> createCustomer(UserRegistrationInfo body) {
		verifyUserAlreadyExist(body);

		final Locale locale = localeResolver.resolveLocale(request);

		try {
			userService.registrateCustomer(body, locale);
		} catch (final MailNotSentException exc) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("SessionController.createUser.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("SessionController.createUser.mailNotSent", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> resendConfirmRegistrationMail(String username) {
		final Optional<UserEntity> user = userService.findByUsername(username);
		if (!user.isPresent()) {
			throw new NoSuchEntityException();
		}
		if (user.get().getStatus() == UserEntity.UserStatus.ACTIVE) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("resend.activation.email.error", null, request))
					.detail(localeUtilsMessage.getMessage("resend.activation.email.userActive", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		final Locale locale = localeResolver.resolveLocale(request);

		userService.resendConfirmRegistrationMail(username, user.get().getEmail(), locale);
		return ResponseEntity.noContent().build();
	}

	private void verifyUserAlreadyExist(UserRegistrationInfo registrationInfo) throws AuthenticationException {
		Optional<UserEntity> userOpt = userService.findByUsername(registrationInfo.getUsername());
		if (userOpt.isPresent()) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("SessionController.createUser.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("SessionController.createUser.userAlreadyExist", null,
							request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		userOpt = userService.findByEmail(registrationInfo.getEmail());
		if (userOpt.isPresent()) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("SessionController.createUser.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("SessionController.createUser.emailAlreadyUsed", null,
							request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}
	}
}
