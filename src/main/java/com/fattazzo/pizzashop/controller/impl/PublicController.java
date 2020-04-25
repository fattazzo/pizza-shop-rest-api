package com.fattazzo.pizzashop.controller.impl;

import java.util.Locale;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.PublicApi;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.security.Session;
import com.fattazzo.pizzashop.model.dto.security.User;
import com.fattazzo.pizzashop.model.dto.security.UserLogin;
import com.fattazzo.pizzashop.service.security.JwtTokenManager;
import com.fattazzo.pizzashop.service.user.UserService;

@RestController
public class PublicController implements PublicApi {

	@Value("${spring.profiles.active}")
	private String enviroment;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<Session> login(@Valid UserLogin body) {
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Locale locale = LocaleContextHolder.getLocale();

		final UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());
		userService.validateUser(body.getUsername());
		final String accessToken = jwtTokenManager.generateAccessToken(userDetails);
		final String refreshToken = jwtTokenManager.generateRefreshToken(userDetails);

		final User user = modelMapper.map(userDetails, User.class);

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
			final RestException exception = RestException.newBuilder().title("Refresh Token Expired or Invalid")
					.detail("Unable to refresh access token").status(HttpStatus.BAD_REQUEST).build();
			throw exception;
		}
		final UserDetails userDetails = jwtTokenManager.getUserDetails(accessToken)
				.orElseThrow(() -> RestException.newBuilder().title("Refresh Token Expired or Invalid")
						.detail("Unable to refresh access token").status(HttpStatus.BAD_REQUEST).build());

		final User user = modelMapper.map(userDetails, User.class);

		return ResponseEntity.ok(new Session().userInfo(user).locale(locale.getCountry()).enviroment(enviroment)
				.accessToken(accessToken).refreshToken(refreshToken));
	}

}
