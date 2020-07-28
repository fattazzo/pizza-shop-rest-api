package com.fattazzo.pizzashop.controller.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.SessionApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.Session;
import com.fattazzo.pizzashop.model.api.SocialTypeEnum;
import com.fattazzo.pizzashop.model.api.User;
import com.fattazzo.pizzashop.model.api.UserLogin;
import com.fattazzo.pizzashop.model.api.UserSocialLogin;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.security.JwtUser;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.security.JwtTokenManager;
import com.fattazzo.pizzashop.service.social.SocialUserService;
import com.fattazzo.pizzashop.service.user.UserService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "session" })
public class SessionController implements SessionApi {

	@Value("${spring.profiles.active}")
	private String enviroment;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private SocialUserService socialUserService;

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	private final HttpServletRequest request;

	@Autowired
	public SessionController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public ResponseEntity<Session> login(@Valid UserLogin body) {
		final UserEntity userEntity = userService.findByUsername(body.getUsername())
				.orElseThrow(NoSuchEntityException::new);
		final User user = modelMapper.map(userEntity, User.class);
		// Only non social type are permitted
		if (user.getSocialType() != SocialTypeEnum.NONE) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", body.getUsername()));
		}
		userService.validateUser(userEntity.getUsername());

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Locale locale = LocaleContextHolder.getLocale();

		final UserDetails userDetails = JwtUser.createIstance(userEntity);
		final String accessToken = jwtTokenManager.generateAccessToken(userDetails);
		final String refreshToken = jwtTokenManager.generateRefreshToken(userDetails);

		return ResponseEntity.ok(new Session().userInfo(user).locale(locale.getCountry()).enviroment(enviroment)
				.accessToken(accessToken).refreshToken(refreshToken));
	}

	@Override
	public ResponseEntity<Session> loginSocial(@Valid UserSocialLogin body) {
		final UserEntity userEntity = socialUserService
				.retrieveUser(body.getSocialType(), body.getUserId(), body.getToken())
				.orElseThrow(NoSuchEntityException::new);
		final User user = modelMapper.map(userEntity, User.class);
		// Only non social type are permitted
		if (user.getSocialType() == SocialTypeEnum.NONE) {
			throw new NoSuchEntityException();
		}
		userService.validateUser(userEntity.getUsername());

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), "*"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Locale locale = LocaleContextHolder.getLocale();

		final UserDetails userDetails = JwtUser.createIstance(userEntity);
		final String accessToken = jwtTokenManager.generateAccessToken(userDetails);
		final String refreshToken = jwtTokenManager.generateRefreshToken(userDetails);

		return ResponseEntity.ok(new Session().userInfo(user).locale(locale.getCountry()).enviroment(enviroment)
				.accessToken(accessToken).refreshToken(refreshToken));
	}

	@Override
	public ResponseEntity<Session> refreshToken(String refreshToken) {
		final Locale locale = LocaleContextHolder.getLocale();

		String accessToken = null;
		try {
			accessToken = jwtTokenManager.generateAccessTokenByRefreshToken(refreshToken);
		} catch (final Exception ex) {
			final RestException exception = RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("refresh.token.error", null, request))
					.detail(localeUtilsMessage.getMessage("refresh.token.unableRefresh", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}
		final UserDetails userDetails = jwtTokenManager.getUserDetails(accessToken)
				.orElseThrow(() -> RestException.newBuilder()
						.title(localeUtilsMessage.getMessage("refresh.token.error", null, request))
						.detail(localeUtilsMessage.getMessage("refresh.token.unableRefresh", null, request))
						.status(HttpStatus.BAD_REQUEST).build());

		final User user = modelMapper.map(userDetails, User.class);

		return ResponseEntity.ok(new Session().userInfo(user).locale(locale.getCountry()).enviroment(enviroment)
				.accessToken(accessToken).refreshToken(refreshToken));
	}

}
