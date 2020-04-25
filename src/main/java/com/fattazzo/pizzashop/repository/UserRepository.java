package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.entity.security.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	UserEntity findByUsernameIgnoreCase(String username);

	Optional<UserEntity> findOneByEmailIgnoreCase(String email);

}
