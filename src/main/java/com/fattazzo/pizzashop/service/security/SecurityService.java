package com.fattazzo.pizzashop.service.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.model.entity.Role;

@Component("securityService")
public class SecurityService {
	public boolean hasAnyPermission(Role... roles) {
		final Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		for (final Role role : roles) {
			if (userAuthorities.contains(new SimpleGrantedAuthority(role.name()))) {
				return true;
			}
		}

		// no matching role found
		return false;
	}
}