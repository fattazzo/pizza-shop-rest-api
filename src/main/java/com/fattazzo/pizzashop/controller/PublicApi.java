package com.fattazzo.pizzashop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fattazzo.pizzashop.model.dto.Session;
import com.fattazzo.pizzashop.model.dto.UserLogin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-22T15:22:06.038Z[GMT]")
@Api(value = "public", description = "the public API")
public interface PublicApi {

	@ApiOperation(value = "Create a session", nickname = "login", notes = "Create a Session information", response = Session.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "session", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Login successfull", response = Session.class) })
	@RequestMapping(value = "/public/session", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Session> login(
			@ApiParam(value = "Login user information", required = true) @Valid @RequestBody UserLogin body);

	@ApiOperation(value = "Create new session", nickname = "refreshToken", notes = "Create new Session with a valid access token", response = Session.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "session", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "New session", response = Session.class) })
	@RequestMapping(value = "/public/session/refresh/{refreshToken}", produces = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Session> refreshToken(
			@ApiParam(value = "Refresh token used for retrieve new updated session information", required = true) @PathVariable("refreshToken") String refreshToken);

}
