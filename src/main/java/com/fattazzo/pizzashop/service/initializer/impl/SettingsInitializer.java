package com.fattazzo.pizzashop.service.initializer.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.entity.security.SettingsEntity;
import com.fattazzo.pizzashop.service.initializer.Initializer;
import com.fattazzo.pizzashop.service.settings.SettingsService;

@Service
@Qualifier("settingsInitializer")
public class SettingsInitializer implements Initializer {

	@Autowired
	private SettingsService settingsService;

	@Override
	public void init() {
		final Optional<SettingsEntity> optional = settingsService.load();

		if (!optional.isPresent()) {
			final SettingsEntity settings = SettingsEntity.builder().currencyDecimals(2).currencySymbol("€").build();
			settingsService.save(settings);
		}
	}

	@Override
	public int priority() {
		return 0;
	}

}
