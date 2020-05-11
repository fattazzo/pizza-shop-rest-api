package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.SettingsEntity;

public interface SettingRepository extends JpaRepository<SettingsEntity, Integer> {
}
