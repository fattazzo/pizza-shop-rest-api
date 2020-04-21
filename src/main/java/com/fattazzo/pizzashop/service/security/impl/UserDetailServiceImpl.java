package com.fattazzo.pizzashop.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fattazzo.pizzashop.model.entity.User;
import com.fattazzo.pizzashop.repository.UserRepository;
import com.fattazzo.pizzashop.security.JwtUser;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userRepository.findByUsernameIgnoreCase(username);
		if (user == null /*
							 * || user.getStatus() == null || user.getStatus() !=
							 * UserEntity.UserStatusEnum.Active
							 */) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return JwtUser.createIstance(user);
		}
	}
}
