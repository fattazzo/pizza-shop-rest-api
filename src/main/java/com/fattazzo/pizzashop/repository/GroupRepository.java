package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, String> {

	GroupEntity findByNameIgnoreCase(String username);

}
