package com.fattazzo.pizzashop.service.local;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

@Component("localeUtilsMessage")
public class LocaleUtilsMessage {
	@Autowired
	@Qualifier("errorMessageSource")
	private MessageSource messageSource;

	public String getErrorLocalizedMessage(String keyMessage, Object[] params) {
		final Locale locale = Locale.ITALIAN;
		try {
			return messageSource.getMessage(keyMessage, params, locale);
		} catch (final NoSuchMessageException ex) {
			return "??" + keyMessage + "??";
		}
	}
}
