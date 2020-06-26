package com.fattazzo.pizzashop.service.social;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.api.SocialTypeEnum;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.service.social.types.facebook.FacebookSocialUserManager;
import com.fattazzo.pizzashop.service.social.types.google.GoogleSocialUserManager;

@Service
public class SocialUserService {

	@Autowired
	private FacebookSocialUserManager facebookSocialUserManager;

	@Autowired
	private GoogleSocialUserManager googleSocialUserManager;

	public Optional<UserEntity> retrieveUser(SocialTypeEnum socialEnum, String userId, String accessToken) {

		Optional<UserEntity> user;
		switch (socialEnum) {
		case GOOGLE:
			user = googleSocialUserManager.getUser(accessToken);
			break;
		case FACEBOOK:
			user = facebookSocialUserManager.getUser(accessToken);
			break;
		default:
			user = Optional.ofNullable(null);
			break;
		}

		return user;
	}
}
