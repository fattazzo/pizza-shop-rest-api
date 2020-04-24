package com.fattazzo.pizzashop.controller.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.SettingsApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.model.dto.security.Settings;
import com.fattazzo.pizzashop.model.entity.security.SettingsEntity;
import com.fattazzo.pizzashop.service.settings.SettingsService;

@RestController
public class SettingsController implements SettingsApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private SettingsService settingsService;

	@Override
	public ResponseEntity<Settings> getSettings() {
		final Optional<SettingsEntity> entityOptional = settingsService.load();

		final SettingsEntity entity = entityOptional.orElseThrow(NoSuchEntityException::new);

		return ResponseEntity.ok(mapper.map(entity, Settings.class));
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SECURITY'})")
	public ResponseEntity<Settings> updateSettings(@Valid Settings body) {
		SettingsEntity settings = mapper.map(body, SettingsEntity.class);

		settings = settingsService.save(settings);
		return ResponseEntity.ok(mapper.map(settings, Settings.class));
	}

}
