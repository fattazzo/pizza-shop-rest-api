package com.fattazzo.pizzashop.model.dto.security;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fattazzo.pizzashop.model.entity.security.GroupEntity;

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
}
