package com.fattazzo.pizzashop.model.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sicu_black_list_refresh_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(builderMethodName = "newBuilder")
public class BlackListRefreshToken {

	@Id
	@Column(name = "refreshToken", unique = true)
	@NotNull
	private String refreshToken;

}
