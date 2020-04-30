package com.fattazzo.pizzashop.service.local;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.fattazzo.pizzashop.config.LocaleResolver;

@Component("localeUtilsMessage")
public class LocaleUtilsMessage {
	@Autowired
	// @Qualifier("errorMessageSource")
	private MessageSource messageSource;

	@Autowired
	private LocaleResolver localeResolver;

	public String getMessage(String keyMessage, Object[] params) {
		return getMessage(keyMessage, params, new Locale("en"));
	}

	public String getMessage(String keyMessage, Object[] params, HttpServletRequest request) {
		try {
			return messageSource.getMessage(keyMessage, params, localeResolver.resolveLocale(request));
		} catch (final NoSuchMessageException ex) {
			return "??" + keyMessage + "??";
		}
	}

	public String getMessage(String keyMessage, Object[] params, Locale locale) {
		try {
			return messageSource.getMessage(keyMessage, params, locale);
		} catch (final NoSuchMessageException ex) {
			return "??" + keyMessage + "??";
		}
	}

	public String getMessage(String keyMessage, Object[] params, WebRequest request) {
		try {
			return messageSource.getMessage(keyMessage, params, localeResolver.resolveLocale(request));
		} catch (final NoSuchMessageException ex) {
			return "??" + keyMessage + "??";
		}
	}
}
