package com.fattazzo.pizzashop.model.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sicu_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class GroupEntity extends EntityBase {

	public static final String NAME_ADMIN = "admin";
	public static final String NAME_WORKER = "worker";
	public static final String NAME_CUSTOMER = "customer";

	@Column(length = 200, unique = true)
	@NotNull
	private String name;

	@Builder.Default
	private boolean readOnly = false;

	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "sicu_group_roles")
	Collection<Role> roles = new ArrayList();
}
