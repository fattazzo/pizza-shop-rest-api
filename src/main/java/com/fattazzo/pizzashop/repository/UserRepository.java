package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsernameIgnoreCase(String username);

	Optional<User> findOneByEmailIgnoreCase(String email);

}
