package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.security.RegistrationToken;

public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, String> {

	long deleteAllByUsernameIgnoreCase(String username);

	Optional<RegistrationToken> findOneByTokenAndUsername(String token, String username);

	Optional<RegistrationToken> findOneByUsernameIgnoreCase(String token);

}
