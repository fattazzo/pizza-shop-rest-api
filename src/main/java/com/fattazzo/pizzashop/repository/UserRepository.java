package com.fattazzo.pizzashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fattazzo.pizzashop.model.api.SocialTypeEnum;
import com.fattazzo.pizzashop.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	UserEntity findByUsernameIgnoreCase(String username);

	UserEntity findByUsernameIgnoreCaseAndSocialType(String username, SocialTypeEnum socialType);

	Optional<UserEntity> findOneByEmailIgnoreCase(String email);

}
