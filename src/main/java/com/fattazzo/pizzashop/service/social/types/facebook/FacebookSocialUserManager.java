package com.fattazzo.pizzashop.service.social.types.facebook;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.SocialTypeEnum;
import com.fattazzo.pizzashop.model.entity.GroupEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity.UserStatus;
import com.fattazzo.pizzashop.model.entity.UserType;
import com.fattazzo.pizzashop.service.group.GroupService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.social.types.SocialTypeManager;
import com.fattazzo.pizzashop.service.user.UserService;

@Service
public class FacebookSocialUserManager implements SocialTypeManager {

	private static final String USERNAME_PREFIX = "f|";

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Value("${spring.social.facebook.app-namespace}")
	private String appNameSpace;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	private UserEntity createUser(String userName, String email, String firstName, String lastName) {
		final GroupEntity group = groupService.loadCustomerGroup()
				.orElseThrow(() -> RestException.newBuilder()
						.title(localeUtilsMessage.getMessage("group.customer.notfound.title", null))
						.detail(localeUtilsMessage.getMessage("group.customer.notfound.detail", null))
						.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

		final UserEntity user = UserEntity.builder().username(USERNAME_PREFIX + userName).email(email)
				.password(passwordEncoder.encode("*")).groups(Arrays.asList(group)).status(UserStatus.ACTIVE)
				.type(UserType.CUSTOMER).socialType(SocialTypeEnum.FACEBOOK).build();

		return userService.save(user);
	}

	private User decodeToken(String accessToken) {
		final Facebook facebook = new FacebookTemplate(accessToken, appNameSpace);
		try {
			return facebook.userOperations().getUserProfile();
		} catch (final Exception e) {
			return null;
		}
	}

	private Optional<UserEntity> findUser(String userName) {
		return userService.findByUsernameAndSocialType(userName, SocialTypeEnum.FACEBOOK);
	}

	@Override
	public Optional<UserEntity> getUser(String accessToken) {

		final User fbUser = decodeToken(accessToken);
		if (fbUser == null) {
			return Optional.ofNullable(null);
		}

		final String userId = fbUser.getId();
		final String email = fbUser.getEmail();
		final String firstName = fbUser.getFirstName();
		final String lastName = fbUser.getLastName();

		final UserEntity userEntity = findUser(userId).orElse(createUser(userId, email, firstName, lastName));
		return Optional.of(userEntity);
	}

}
