package com.fattazzo.pizzashop.service.user;

import java.util.Optional;

import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.exception.security.MailNotSentException;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.UserNotActiveException;
import com.fattazzo.pizzashop.model.dto.UserRegistrationInfo;
import com.fattazzo.pizzashop.model.entity.User;

public interface UserManager {

	void confirmRegistration(String username, String registrationToken)
			throws NoSuchEntityException, ExpiredTokenException;

	Optional<User> findAdminUser();

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	void registrateCustomer(UserRegistrationInfo registrationInfo) throws MailNotSentException;

	void registrateWorker(UserRegistrationInfo registrationInfo) throws MailNotSentException;

	void resendConfirmRegistrationMail(String username, String email);

	User save(User user);

	void validateUser(String username) throws UserNotActiveException;
}
