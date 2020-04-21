package com.fattazzo.pizzashop.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

public class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		if (request.getHeader("Accept-Language") == null || request.getHeader("Accept-Language").length() == 0) {
			return Locale.getDefault();
		}

		return request.getLocale();
	}
}
