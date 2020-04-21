package com.fattazzo.pizzashop.controller;

import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.exception.security.MailNotSentException;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.LoginRequest;
import com.fattazzo.pizzashop.model.dto.Session;
import com.fattazzo.pizzashop.model.dto.UserDto;
import com.fattazzo.pizzashop.model.dto.UserRegistrationInfo;
import com.fattazzo.pizzashop.model.entity.User;
import com.fattazzo.pizzashop.repository.UserRepository;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.security.JwtTokenManager;
import com.fattazzo.pizzashop.service.user.UserManager;

@RestController
public class SessionController {

	protected static final Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Value("${jwt.accessToken.name}")
	private String accessTokenName;

	@Value("${jwt.refreshToken.name}")
	private String refreshTokenName;

	@Value("${spring.profiles.active}")
	private String enviroment;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@RequestMapping(value = "public/v1/confirm-registration/{username}", method = RequestMethod.POST)
	public ResponseEntity<?> confirmRegistration(@PathVariable final String username,
			@RequestParam(value = "token", required = true) final String registrationToken)
			throws AuthenticationException, JsonProcessingException {
		if (StringUtils.isEmpty(registrationToken)) {
			final RestException exception = RestException.newBuilder().title("Conferma utente in errore")
					.detail("E'  necessario il token di registrazione").status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		try {
			userManager.confirmRegistration(username, registrationToken);
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

	@RequestMapping(value = "public/v1/session", method = RequestMethod.POST)
	public ResponseEntity<Session> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Locale locale = LocaleContextHolder.getLocale();

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		userManager.validateUser(authenticationRequest.getUsername());
		final String accessToken = jwtTokenManager.generateAccessToken(userDetails);
		final String refreshToken = jwtTokenManager.generateRefreshToken(userDetails);

		return ResponseEntity.ok(Session.newBuilder().userInfo((UserDto) userDetails).locale(locale.getCountry())
				.environment(enviroment).accessToken(accessToken).refreshToken(refreshToken).build());
	}

	@RequestMapping(value = "public/v1/registration-customer", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody @Valid UserRegistrationInfo registrationInfo,
			HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
		verifyUserAlreadyExist(registrationInfo);

		try {
			userManager.registrateCustomer(registrationInfo);
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

	@RequestMapping(value = "public/v1/registration-worker", method = RequestMethod.POST)
	public ResponseEntity<?> createWorker(@RequestBody @Valid UserRegistrationInfo registrationInfo,
			HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
		verifyUserAlreadyExist(registrationInfo);

		try {
			userManager.registrateWorker(registrationInfo);
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

	@RequestMapping(value = "public/v1/session/refresh/{refreshToken}", method = RequestMethod.POST)
	public ResponseEntity<?> refreshToken(@PathVariable final String refreshToken, HttpServletResponse response)
			throws AuthenticationException {
		final Locale locale = LocaleContextHolder.getLocale();

		String accessToken = null;
		try {
			accessToken = jwtTokenManager.generateAccessTokenByRefreshToken(refreshToken);
		} catch (final Exception ex) {
			final RestException exception = RestException.newBuilder().title("Refresh Token Expired or Invalid")
					.detail("Unable to refresh access token").status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}
		final UserDetails userDetails = jwtTokenManager.getUserDetails(accessToken)
				.orElseThrow(() -> RestException.newBuilder().title("Refresh Token Expired or Invalid")
						.detail("Unable to refresh access token").status(HttpStatus.BAD_REQUEST).build());

		return ResponseEntity.ok(Session.newBuilder().userInfo((UserDto) userDetails).locale(locale.getCountry())
				.environment(enviroment).accessToken(accessToken).refreshToken(refreshToken).build());

	}

	@RequestMapping(value = "public/v1/resend-confirm-registration-mail", method = RequestMethod.POST)
	public ResponseEntity<?> resendConfirmRegistrationMail(@RequestParam final String username)
			throws AuthenticationException, JsonProcessingException {
		final User user = userRepository.findByUsernameIgnoreCase(username);
		if (user == null) {
			throw new NoSuchEntityException();
		}
		if (user.getStatus() == User.UserStatusEnum.Active) {
			final RestException exception = RestException.newBuilder()
					.title("Reinvio Mail di conferma registrazione in errore")
					.detail("E'  necessario il token di registrazione").status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		userManager.resendConfirmRegistrationMail(username, user.getEmail());
		return ResponseEntity.noContent().build();

	}

	private void verifyUserAlreadyExist(UserRegistrationInfo registrationInfo)
			throws AuthenticationException, JsonProcessingException {
		Optional<User> userOpt = userManager.findByUsername(registrationInfo.getUsername());
		if (userOpt.isPresent()) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.failed.title",
							null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("SessionController.createUser.userAlreadyExist",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}

		userOpt = userManager.findByEmail(registrationInfo.getEmail());
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
