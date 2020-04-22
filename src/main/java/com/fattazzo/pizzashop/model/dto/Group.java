package com.fattazzo.pizzashop.model.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.fattazzo.pizzashop.model.entity.Role;

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
public class Group {

	private Integer id;

	@NotNull
	private String name;

	@Builder.Default
	private boolean readOnly = false;

	@Builder.Default
	Collection<Role> roles = new ArrayList();
}
