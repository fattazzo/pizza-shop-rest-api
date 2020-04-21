package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.Group;

public interface GroupRepository extends JpaRepository<Group, String> {

	Group findByNameIgnoreCase(String username);

}
