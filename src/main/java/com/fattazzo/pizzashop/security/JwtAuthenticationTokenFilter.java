package com.fattazzo.pizzashop.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.service.security.JwtTokenManager;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@Value("${jwt.accessToken.name}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String authToken = StringUtils.replace(request.getHeader(this.tokenHeader), "Bearer ", "");

		UserDetails userDetails = null;

		if (authToken != null) {
			try {
				userDetails = jwtTokenManager.getUserDetails(authToken).orElse(null);
			} catch (final ExpiredJwtException e) {

				request.setAttribute("isExpired", true);
				chain.doFilter(request, response);
				return;
			}

		}

		if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// Ricostruisco l userdetails con i dati contenuti nel token

			// controllo integrita' token
			boolean validToken = true;
			try {
				jwtTokenManager.validateAccessToken(authToken, userDetails);
			} catch (final ExpiredTokenException e) {
				validToken = false;
				throw e;
			} catch (final Exception e) {
				logger.error("Invalid token [{}] ", e);
				validToken = false;
			}

			if (validToken) {
				final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		}

		chain.doFilter(request, response);
	}
}
