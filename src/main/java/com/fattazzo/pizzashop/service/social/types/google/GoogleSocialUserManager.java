package com.fattazzo.pizzashop.service.social.types.google;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleSocialUserManager implements SocialTypeManager {

	private static final String USERNAME_PREFIX = "g|";

	private static final String CLAIM_FIRST_NAME = "given_name";
	private static final String CLAIM_LAST_NAME = "family_name";

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Value("${social.google.clientId}")
	private String clientId;

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
				.type(UserType.CUSTOMER).socialType(SocialTypeEnum.GOOGLE).build();

		return userService.save(user);
	}

	private GoogleIdToken.Payload decodeToken(String accessToken) {
		final JsonFactory mJFactory = new JacksonFactory();
		// "1079647101570-8l2adk4hsop64bbbtiditmbbmdcvd481.apps.googleusercontent.com");
		try {
			final GoogleIdToken token = GoogleIdToken.parse(mJFactory, accessToken);
			return token.getPayload();
		} catch (final Exception e) {
			return null;
		}
	}

	private Optional<UserEntity> findUser(String userName) {
		return userService.findByUsernameAndSocialType(userName, SocialTypeEnum.GOOGLE);
	}

	@Override
	public Optional<UserEntity> getUser(String accessToken) {

		final Payload payload = decodeToken(accessToken);
		if (payload == null) {
			return Optional.ofNullable(null);
		}

		final String userId = payload.getSubject();
		final String email = payload.getEmail();
		final String firstName = (String) payload.get(CLAIM_FIRST_NAME);
		final String lastName = (String) payload.get(CLAIM_LAST_NAME);

		final UserEntity userEntity = findUser(userId).orElse(createUser(userId, email, firstName, lastName));
		return Optional.of(userEntity);
	}

}
