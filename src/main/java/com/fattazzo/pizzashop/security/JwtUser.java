package com.fattazzo.pizzashop.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fattazzo.pizzashop.model.dto.UserDto;
import com.fattazzo.pizzashop.model.entity.Role;
import com.fattazzo.pizzashop.model.entity.User;

import lombok.Builder;

public class JwtUser extends UserDto implements UserDetails {
	public static JwtUser createIstance(User user) {

		final List<Role> roles = user.getGroups().stream().flatMap(g -> g.getRoles().stream())
				.collect(Collectors.toList());

		return JwtUser.newBuilderExt().username(user.getUsername()).email(user.getEmail()).password(user.getPassword())
				.roles(roles).build();
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.name()))
				.collect(Collectors.toList());
	}

	@JsonIgnore
	private final String password;

	@Builder(builderMethodName = "newBuilderExt")
	public JwtUser(String username, String email, List<Role> roles, String password) {
		super(username, email, roles);
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles() == null ? null : mapToGrantedAuthorities(this.getRoles());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
