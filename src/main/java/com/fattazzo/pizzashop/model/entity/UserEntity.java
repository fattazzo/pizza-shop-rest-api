package com.fattazzo.pizzashop.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringExclude;

import com.fattazzo.pizzashop.model.api.SocialTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sicu_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserEntity {

	public enum UserStatus {
		ACTIVE, TOCONFIRM;
	}

	@Id
	@Column(length = 50, unique = true)
	@NotNull
	private String username;

	@Column(length = 200)
	@NotNull
	private String password;

	@Column(length = 100)
	private String email;

	@Column(length = 200)
	private String firstName;

	@Column(length = 200)
	private String lastName;

	@Builder.Default
	private boolean readOnly = false;

	@Builder.Default
	private UserType type = UserType.WORKER;

	@ManyToMany
	@Builder.Default
	private List<GroupEntity> groups = new ArrayList();

	private UserStatus status;

	@Builder.Default
	private SocialTypeEnum socialType = SocialTypeEnum.NONE;

	@ToStringExclude
	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
	private List<UserDeliveryAddressEntity> deliveryAddresses = new ArrayList();

	public List<Role> getRoles() {
		final List<Role> roles = CollectionUtils.emptyIfNull(getGroups()).stream().flatMap(g -> g.getRoles().stream())
				.collect(Collectors.toList());
		return roles;
	}
}
