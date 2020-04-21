package com.fattazzo.pizzashop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class WebAppConfig {
	@Autowired
	@Qualifier("errorMessageSource")
	private MessageSource errorMessageSource;

	@Bean
	@Primary
	public LocalValidatorFactoryBean validator() {
		final LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(errorMessageSource);
		// validatorFactoryBean.setMessageInterpolator(messageInterpolator);
		return validatorFactoryBean;
	}
}
