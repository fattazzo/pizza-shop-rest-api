package com.fattazzo.pizzashop.config;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class SpringMessageSourceMessageInterpolator
		implements MessageInterpolator, MessageSourceAware, InitializingBean {

	@Autowired
	@Qualifier("errorMessageSource")
	MessageSource errorMessageSource;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (errorMessageSource == null) {
			throw new IllegalStateException(
					"MessageSource was not injected, could not initialize " + this.getClass().getSimpleName());
		}
	}

	@Override
	public String interpolate(String messageTemplate, Context context) {
		return errorMessageSource.getMessage(messageTemplate, new Object[] {}, Locale.getDefault());
	}

	@Override
	public String interpolate(String messageTemplate, Context context, Locale locale) {
		return errorMessageSource.getMessage(messageTemplate, new Object[] {}, locale);
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.errorMessageSource = errorMessageSource;

	}

}
