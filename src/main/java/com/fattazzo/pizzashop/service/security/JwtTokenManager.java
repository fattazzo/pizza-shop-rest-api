package com.fattazzo.pizzashop.service.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fattazzo.pizzashop.exception.security.ExpiredTokenException;
import com.fattazzo.pizzashop.exception.security.RefreshTokenInBlackList;
import com.fattazzo.pizzashop.model.entity.Role;
import com.fattazzo.pizzashop.repository.BlackListRefreshTokenRepository;
import com.fattazzo.pizzashop.security.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenManager {

	public enum TokenTypeEnum {
		AccessToken, RefreshToken
	}

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "iat";
	static final String CLAIM_KEY_AUTHORITIES = "roles";
	static final String CLAIM_KEY_MAIL = "mail";
	static final String CLAIM_KEY_IS_ENABLED = "isEnabled";

	static final Logger logger = LoggerFactory.getLogger(JwtTokenManager.class);

	static String getEmailFromToken(Claims claims) {
		return (String) claims.get(CLAIM_KEY_MAIL);
	}

	static Date getExpirationDateFromToken(Claims claims) {
		return claims.getExpiration();
	}

	static String getUsernameFromToken(Claims claims) {
		return claims.getSubject();
	}

	static boolean isTokenExpired(Claims claims) {
		final Date expiration = getExpirationDateFromToken(claims);
		return expiration.before(new Date());
	}

	@Autowired
	private BlackListRefreshTokenRepository blackListRefreshTokenRepository;

	@Autowired
	private UserDetailsService userDetailsService;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.accessToken.expiration}")
	private Long accessTokenExpiration;

	@Value("${jwt.refreshToken.expiration}")
	private Long refreshTokenExpiration;

	public String generateAccessToken(UserDetails userDetails) {
		final Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());
		final List<String> auth = userDetails.getAuthorities().stream().map(role -> role.getAuthority())
				.collect(Collectors.toList());
		claims.put(CLAIM_KEY_AUTHORITIES, auth);
		claims.put(CLAIM_KEY_IS_ENABLED, userDetails.isEnabled());
		claims.put(CLAIM_KEY_MAIL, ((JwtUser) userDetails).getEmail());

		return generateToken(claims, TokenTypeEnum.AccessToken);
	}

	public String generateAccessTokenByRefreshToken(String refreshToken) throws Exception {
		if (blackListRefreshTokenRepository.findByRefreshToken(refreshToken) != null) {

			logger.error("refresh token [{}] is in blacklist", refreshToken);
			throw new RefreshTokenInBlackList("Token in BlackList");
		}
		final Claims claims = getClaimsFromToken(refreshToken);

		if (isTokenExpired(claims)) {
			logger.error("refresh token [{}] is expired", refreshToken);
			throw new ExpiredTokenException("Refresh Token Expired");
		}

		final String username = getUsernameFromToken(claims);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (userDetails == null) {
			logger.error("refresh token [{}] refer invalid user", refreshToken);
			throw new UsernameNotFoundException("User Not Exist");
		}
		return this.generateAccessToken(userDetails);
	}

	private Date generateAccessTokenExpirationDate() {
		return new Date(System.currentTimeMillis() + accessTokenExpiration * 1000);
	}

	public String generateRefreshToken(UserDetails userDetails) {
		final Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());

		return generateToken(claims, TokenTypeEnum.RefreshToken);
	}

	private Date generateRefreshTokenExpirationDate() {
		return new Date(System.currentTimeMillis() + refreshTokenExpiration * 1000);
	}

	String generateToken(Map<String, Object> claims, TokenTypeEnum tokenTypeEnum) {

		return Jwts.builder().setClaims(claims)
				.setExpiration(tokenTypeEnum == TokenTypeEnum.AccessToken ? generateAccessTokenExpirationDate()
						: generateRefreshTokenExpirationDate())
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (final ExpiredJwtException e1) {
			throw e1;
		} catch (final Exception e) {
			claims = null;
		}
		return claims;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
		} catch (final Exception e) {
			created = null;
		}
		return created;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (final Exception e) {
			expiration = null;
		}
		return expiration;
	}

	@SuppressWarnings("unchecked")
	public Optional<UserDetails> getUserDetails(String authToken) throws ExpiredJwtException {
		if (StringUtils.isEmpty(authToken)) {
			return Optional.empty();
		}

		try {
			final Claims claims = getClaimsFromToken(authToken);
			List<String> authorities = null;
			if (claims.get(CLAIM_KEY_AUTHORITIES) != null) {
				authorities = (List<String>) claims.get(CLAIM_KEY_AUTHORITIES);
			}

			return Optional.of(JwtUser.builder().username(claims.getSubject())
					.roles(authorities.stream().map(v -> Role.fromValue(v)).collect(Collectors.toList()))
					.email(getEmailFromToken(claims)).build());

		} catch (final ExpiredJwtException e) {
			throw e;
		} catch (final Exception e) {
			logger.info("Error during getUserDetails from token", authToken);
			return Optional.empty();
		}

	}

	public void validateAccessToken(String token, UserDetails userDetails) throws Exception {
		final JwtUser user = (JwtUser) userDetails;
		final Claims claims = getClaimsFromToken(token);
		if (claims == null) {
			throw new UsernameNotFoundException("Invalid Claims");
		}

		final String username = getUsernameFromToken(claims);
		if (user == null || !username.equals(user.getUsername())) {
			throw new UsernameNotFoundException("Invalid User");
		}
	}
}
