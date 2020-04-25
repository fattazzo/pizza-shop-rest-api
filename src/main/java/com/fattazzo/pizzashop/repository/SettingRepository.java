package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.security.SettingsEntity;

public interface SettingRepository extends JpaRepository<SettingsEntity, Integer> {
}
