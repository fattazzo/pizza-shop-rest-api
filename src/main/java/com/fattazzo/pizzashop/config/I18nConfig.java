package com.fattazzo.pizzashop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class I18nConfig { // implements WebMvcConfigurer {

	@Bean(name = "errorMessageSource")
	@Primary
	public MessageSource errorMessageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/i18n/errors/message", "classpath:/i18n/errors/validations",
				"classpath:/i18n/errors/hibernate", "classpath:/i18n/errors/tags");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

//	@Bean
//	public LocaleResolver localeResolver() {
//		final SmartLocaleResolver smartLocaleResolver = new SmartLocaleResolver();
//		smartLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//		return smartLocaleResolver;
//	}

}
