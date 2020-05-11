package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {

	Optional<GroupEntity> findByNameIgnoreCase(String name);

}
