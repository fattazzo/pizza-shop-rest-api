package com.fattazzo.pizzashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.entity.security.BlackListRefreshToken;

public interface BlackListRefreshTokenRepository extends JpaRepository<BlackListRefreshToken, String> {

	BlackListRefreshToken findByRefreshToken(String refreshToken);
}
