package com.fattazzo.pizzashop.service.user;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.apache.commons.collections4.ListUtils;
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
import com.fattazzo.pizzashop.model.entity.GroupEntity;
import com.fattazzo.pizzashop.model.entity.RegistrationToken;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity.UserStatusEnum;
import com.fattazzo.pizzashop.repository.RegistrationTokenRepository;
import com.fattazzo.pizzashop.repository.UserRepository;
import com.fattazzo.pizzashop.service.group.GroupService;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;

@Service
public class UserService {

	public class UserReadonlyException extends RuntimeException {
		private static final long serialVersionUID = -7569559135419043478L;

		public UserReadonlyException(String s) {
			super(s);
		}

	}

	protected static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupService groupService;

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
	public void confirmRegistration(String username, String registrationToken)
			throws NoSuchEntityException, ExpiredTokenException {
		final OffsetDateTime now = OffsetDateTime.now();
		final RegistrationToken registrationTokenEntity = registrationTokenRepository
				.findOneByTokenAndUsername(registrationToken, username).orElseThrow(NoSuchEntityException::new);

		if (now.isAfter(registrationTokenEntity.getExpiredAt())) {
			throw new ExpiredTokenException("Expired Token");
		}
		final UserEntity user = findByUsername(registrationTokenEntity.getUsername())
				.orElseThrow(NoSuchEntityException::new);

		user.setStatus(UserEntity.UserStatusEnum.Active);
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

	public void delete(String username) throws NoSuchEntityException, UserReadonlyException {

		final UserEntity userEntity = findByUsername(username).orElseThrow(NoSuchEntityException::new);

		if (userEntity.isReadOnly()) {
			throw new UserReadonlyException(userEntity.getUsername());
		}
		userRepository.delete(userEntity);
	}

	public Optional<UserEntity> findAdminUser() {
		return findByUsername("admin");
	}

	public List<UserEntity> findAll() {
		final List<UserEntity> users = userRepository.findAll();
		return ListUtils.emptyIfNull(users);
	}

	public Optional<UserEntity> findByEmail(String email) {
		final UserEntity user = userRepository.findOneByEmailIgnoreCase(email).orElse(null);
		return Optional.ofNullable(user);
	}

	public Optional<UserEntity> findByUsername(String username) {
		final UserEntity user = userRepository.findByUsernameIgnoreCase(username);
		return Optional.ofNullable(user);
	}

	@Transactional
	public void registrateCustomer(UserRegistrationInfo registrationInfo) throws MailNotSentException {
		final GroupEntity group = groupService.loadCustomerGroup();
		registrateUser(registrationInfo, group, UserStatusEnum.ToConfirm);
	}

	public void registrateUser(UserRegistrationInfo registrationInfo, GroupEntity group, UserStatusEnum status)
			throws MailNotSentException {

		final UserEntity user = UserEntity.builder().username(registrationInfo.getUsername())
				.email(registrationInfo.getEmail()).password(passwordEncoder.encode(registrationInfo.getPassword()))
				.groups(Arrays.asList(group)).status(status).build();

		userRepository.save(user);

		if (status == UserStatusEnum.ToConfirm) {
			final RegistrationToken registrationToken = createRegistrationToken(registrationInfo.getUsername());
			sendRegistrationMail(registrationToken, registrationInfo.getEmail());
		}
	}

	@Transactional
	public void registrateWorker(UserRegistrationInfo registrationInfo) throws MailNotSentException {
		final GroupEntity group = groupService.loadWorkerGroup();
		registrateUser(registrationInfo, group, UserStatusEnum.Active);
	}

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

	public UserEntity save(UserEntity user) {
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

	public void validateUser(String username) throws UserNotActiveException {
		final Optional<UserEntity> userOpt = findByUsername(username);

		if (!userOpt.isPresent() || userOpt.get().getStatus() == null
				|| userOpt.get().getStatus() != UserEntity.UserStatusEnum.Active) {
			throw new UserNotActiveException("user not active");
		}
	}
}
