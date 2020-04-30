package com.fattazzo.pizzashop.config;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Component
public class LocaleResolver extends AcceptHeaderLocaleResolver {

	private static final Map<String, Locale> LOCALES = new HashedMap<String, Locale>() {
		private static final long serialVersionUID = 1L;
		{
			put("it", new Locale("it"));
			put("en", new Locale("en"));
		}
	};

	private Locale resolveLanguage(String language) {
		if (StringUtils.defaultIfBlank(language, "").contains("it")) {
			return LOCALES.get("it");
		}
		return LOCALES.get("en");
	}

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		final String language = request.getHeader("Accept-Language");
		if (language == null || language.isEmpty()) {
			return new Locale("en");
		}
		return resolveLanguage(language);
	}

	public Locale resolveLocale(WebRequest request) {
		final String language = request.getHeader("Accept-Language");
		if (language == null || language.isEmpty()) {
			return new Locale("en");
		}
		return resolveLanguage(language);
	}

}