package com.fattazzo.pizzashop.entity.security;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sicu_registration_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(builderMethodName = "newBuilder")
@IdClass(RegistrationTokenEntityId.class)
public class RegistrationToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private String token;

	@Column(length = 50)
	@NotNull
	@Id
	private String username;

	private OffsetDateTime expiredAt;

	@Builder.Default
	private OffsetDateTime createdAt = OffsetDateTime.now();

}
