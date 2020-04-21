package com.fattazzo.pizzashop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
public class User {

	public enum UserStatusEnum {
		Active(0), ToConfirm(1);

		private final int value;

		UserStatusEnum(int value) {
			this.value = value;
		}
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

	@OneToMany
	@Builder.Default
	private List<Group> groups = new ArrayList();

	@Column(name = "status")
	private UserStatusEnum status;
}
