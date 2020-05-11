package com.fattazzo.pizzashop.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fattazzo.pizzashop.model.entity.Role;
import com.fattazzo.pizzashop.model.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -2833270119513844317L;

	public static JwtUser createIstance(UserEntity user) {

		return JwtUser.builder().username(user.getUsername()).email(user.getEmail()).password(user.getPassword())
				.roles(user.getRoles()).build();
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.name()))
				.collect(Collectors.toList());
	}

	private String username;

	private String password;

	private String email;

	private List<Role> roles;

//	@Builder(builderMethodName = "newBuilderExt")
//	public JwtUser(String username, String email, List<Role> roles, String password) {
//		super(username, email, roles);
//		this.password = password;
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles() == null ? null : mapToGrantedAuthorities(this.getRoles());
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
