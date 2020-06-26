package com.fattazzo.pizzashop.service.social.types;

import java.util.Optional;

import com.fattazzo.pizzashop.model.entity.UserEntity;

public interface SocialTypeManager {

	Optional<UserEntity> getUser(String accessToken);
}
