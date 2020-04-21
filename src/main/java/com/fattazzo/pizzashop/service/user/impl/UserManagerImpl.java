package com.fattazzo.pizzashop.service.user.impl;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.exception.security.MailNotSentException;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.UserNotActiveException;
import com.fattazzo.pizzashop.model.dto.UserRegistrationInfo;
import com.fattazzo.pizzashop.model.entity.Group;
import com.fattazzo.pizzashop.model.entity.RegistrationToken;
import com.fattazzo.pizzashop.model.entity.User;
import com.fattazzo.pizzashop.model.entity.User.UserStatusEnum;
import com.fattazzo.pizzashop.repository.RegistrationTokenRepository;
import com.fattazzo.pizzashop.repository.UserRepository;
import com.fattazzo.pizzashop.service.group.GroupManager;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.user.UserManager;

@Service
public class UserManagerImpl implements UserManager {
	protected static final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupManager groupManager;

	@Autowired
	private RegistrationTokenRepository registrationTokenRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${registration.expiration}")
	private Integer registrationTokenExpiration;

	@Value("${registration.confirmUrl}")
	private String confirmUrl;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	@Transactional
	@Override
	public void confirmRegistration(String username, String registrationToken)
			throws NoSuchEntityException, ExpiredTokenException {
		final OffsetDateTime now = OffsetDateTime.now();
		final RegistrationToken registrationTokenEntity = registrationTokenRepository
				.findOneByTokenAndUsername(registrationToken, username).orElseThrow(NoSuchEntityException::new);

		if (now.isAfter(registrationTokenEntity.getExpiredAt())) {
			throw new ExpiredTokenException("Expired Token");
		}
		final User user = userRepository.findByUsernameIgnoreCase(registrationTokenEntity.getUsername());
		if (user == null) {
			throw new NoSuchEntityException();
		}
		user.setStatus(User.UserStatusEnum.Active);
		userRepository.save(user);
		registrationTokenRepository.delete(registrationTokenEntity);
	}

	private RegistrationToken createRegistrationToken(String username) {
		final RegistrationToken registrationToken = RegistrationToken.newBuilder()
				.token("" + Math.abs(Double.valueOf(Math.random() * Math.pow(10, 5)).intValue()))
				.expiredAt(OffsetDateTime.now().plus(registrationTokenExpiration, ChronoUnit.MINUTES))
				.username(username).build();

		return registrationTokenRepository.save(registrationToken);

	}

	@Override
	public Optional<User> findAdminUser() {
		return Optional.ofNullable(userRepository.findByUsernameIgnoreCase("admin"));
	}

	@Override
	public Optional<User> findByEmail(String email) {
		final User user = userRepository.findOneByEmailIgnoreCase(email).orElse(null);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		final User user = userRepository.findByUsernameIgnoreCase(username);
		return Optional.ofNullable(user);
	}

	@Override
	@Transactional
	public void registrateCustomer(UserRegistrationInfo registrationInfo) throws MailNotSentException {
		final Group group = groupManager.loadCustomerGroup();
		registrateUser(registrationInfo, group, UserStatusEnum.ToConfirm);
	}

	public void registrateUser(UserRegistrationInfo registrationInfo, Group group, UserStatusEnum status)
			throws MailNotSentException {

		final User user = User.builder().username(registrationInfo.getUsername()).email(registrationInfo.getEmail())
				.password(passwordEncoder.encode(registrationInfo.getPassword())).groups(Arrays.asList(group))
				.status(status).build();

		userRepository.save(user);

		if (status == UserStatusEnum.ToConfirm) {
			final RegistrationToken registrationToken = createRegistrationToken(registrationInfo.getUsername());
			sendRegistrationMail(registrationToken, registrationInfo.getEmail());
		}
	}

	@Override
	@Transactional
	public void registrateWorker(UserRegistrationInfo registrationInfo) throws MailNotSentException {
		final Group group = groupManager.loadWorkerGroup();
		registrateUser(registrationInfo, group, UserStatusEnum.Active);
	}

	@Override
	@Transactional
	public void resendConfirmRegistrationMail(String username, String email) {
		RegistrationToken registrationToken = registrationTokenRepository.findOneByUsernameIgnoreCase(username)
				.orElse(null);

		long l = -1;
		if (registrationToken != null) {
			l = registrationTokenRepository.deleteAllByUsernameIgnoreCase(registrationToken.getUsername());
		}
		logger.info("debug l [{}] ", l);

		registrationToken = createRegistrationToken(username);
		sendRegistrationMail(registrationToken, email);

	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	private void sendRegistrationMail(RegistrationToken registrationToken, String email) throws MailNotSentException {
		try {
			final MimeMessage message = sender.createMimeMessage();

			// Enable the multipart flag!
			final MimeMessageHelper helper = new MimeMessageHelper(message, true);

			final String registratioTemplate = localeUtilsMessage.getErrorLocalizedMessage("activationMail", null);
			helper.setTo(email);
			helper.setText(registratioTemplate.replace("@1", registrationToken.getUsername()).replace("@2",
					registrationToken.getToken()), true);
			helper.setSubject("Attivazione Account Pizza Shop");

			sender.send(message);
		} catch (final Exception exception) {
			throw new MailNotSentException();
		}
	}

	@Override
	public void validateUser(String username) throws UserNotActiveException {
		final Optional<User> userOpt = findByUsername(username);

		if (!userOpt.isPresent() || userOpt.get().getStatus() == null
				|| userOpt.get().getStatus() != User.UserStatusEnum.Active) {
			throw new UserNotActiveException("user not active");
		}
	}
}
