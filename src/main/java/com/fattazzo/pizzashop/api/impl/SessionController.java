package com.fattazzo.pizzashop.api.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fattazzo.pizzashop.entity.security.UserEntity;
import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.exception.security.MailNotSentException;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.security.UserRegistrationInfo;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.user.UserService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "session" })
public class SessionController {

	protected static final Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Value("${spring.profiles.active}")
	private String enviroment;

	@Autowired
	private UserService userService;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@RequestMapping(value = "public/confirm-registration/{username}", method = RequestMethod.POST)
	public ResponseEntity<?> confirmRegistration(@PathVariable final String username,
			@RequestParam(value = "token", required = true) final String registrationToken)
			throws AuthenticationException, JsonProcessingException {
		if (StringUtils.isEmpty(registrationToken)) {
			final RestException exception = RestException.newBuilder().title("Conferma utente in errore")
					.detail("E'  necessario il token di registrazione").status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		try {
			userService.confirmRegistration(username, registrationToken);
		} catch (final ExpiredTokenException ex) {

			final RestException exception = RestException.newBuilder().title("Conferma utente in errore")
					.detail("Link di Attivazione Scaduto o Gia' Utilizzato: Richiedere un nuovo link di attivazione")
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;

		} catch (final NoSuchEntityException ex) {
			final RestException exception = RestException.newBuilder().title("Conferma utente in errore")
					.detail("Token di Attivazione Invalido").status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		return ResponseEntity.ok("Utente Attivato, puoi iniziare ad utilizzare mybudget");

	}

	@RequestMapping(value = "public/registration-customer", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody @Valid UserRegistrationInfo registrationInfo,
			HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
		verifyUserAlreadyExist(registrationInfo);

		try {
			userService.registrateCustomer(registrationInfo);
		} catch (final MailNotSentException exc) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.failed.title",
							null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.mailNotSent",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "public/registration-worker", method = RequestMethod.POST)
	public ResponseEntity<?> createWorker(@RequestBody @Valid UserRegistrationInfo registrationInfo,
			HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
		verifyUserAlreadyExist(registrationInfo);

		try {
			userService.registrateWorker(registrationInfo);
		} catch (final MailNotSentException exc) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.failed.title",
							null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.mailNotSent",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "public/resend-confirm-registration-mail", method = RequestMethod.POST)
	public ResponseEntity<?> resendConfirmRegistrationMail(@RequestParam final String username)
			throws AuthenticationException, JsonProcessingException {
		final Optional<UserEntity> user = userService.findByUsername(username);
		if (!user.isPresent()) {
			throw new NoSuchEntityException();
		}
		if (user.get().getStatus() == UserEntity.UserStatus.Active) {
			final RestException exception = RestException.newBuilder()
					.title("Reinvio Mail di conferma registrazione in errore")
					.detail("E'  necessario il token di registrazione").status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		userService.resendConfirmRegistrationMail(username, user.get().getEmail());
		return ResponseEntity.noContent().build();

	}

	private void verifyUserAlreadyExist(UserRegistrationInfo registrationInfo)
			throws AuthenticationException, JsonProcessingException {
		Optional<UserEntity> userOpt = userService.findByUsername(registrationInfo.getUsername());
		if (userOpt.isPresent()) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.failed.title",
							null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.userAlreadyExist",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		userOpt = userService.findByEmail(registrationInfo.getEmail());
		if (userOpt.isPresent()) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.failed.title",
							null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.emailAlreadyUsed",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}
	}

}
