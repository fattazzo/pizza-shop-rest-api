package com.fattazzo.pizzashop.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fattazzo.pizzashop.model.entity.GroupEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity.UserStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDetails extends User {

	private static final long serialVersionUID = -2660656695402448593L;

	@NotNull
	private String password;

	private String firstName;

	private String lastName;

	@Builder.Default
	private List<GroupEntity> groups = new ArrayList();

	@Column(name = "status")
	private UserStatusEnum status;
}
