package com.fattazzo.pizzashop.service.settings;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.SettingsEntity;
import com.fattazzo.pizzashop.repository.SettingRepository;

@Service
public class SettingsService {

	@Autowired
	private SettingRepository settingRepository;

	public Optional<SettingsEntity> findById(Integer id) {
		return settingRepository.findById(id);
	}

	public Optional<SettingsEntity> load() {

		final List<SettingsEntity> settings = settingRepository.findAll();

		return settings.isEmpty() ? Optional.ofNullable(null) : Optional.of(settings.get(0));
	}

	public SettingsEntity save(SettingsEntity settings) {
		return settingRepository.save(settings);
	}

}
