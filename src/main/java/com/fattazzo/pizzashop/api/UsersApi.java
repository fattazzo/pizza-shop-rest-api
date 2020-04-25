package com.fattazzo.pizzashop.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fattazzo.pizzashop.model.dto.security.User;
import com.fattazzo.pizzashop.model.dto.security.UserDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-21T15:52:08.856Z[GMT]")
@Api(value = "users", description = "the users API")
public interface UsersApi {

	@ApiOperation(value = "Create a User", nickname = "createUser", notes = "Creates a new instance of a `User`.", response = UserDetails.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "users", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful response.", response = UserDetails.class) })
	@RequestMapping(value = "/users", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<UserDetails> createUser(
			@ApiParam(value = "A new `User` to be created.", required = true) @Valid @RequestBody UserDetails body);

	@ApiOperation(value = "Delete a User", nickname = "deleteUser", notes = "Deletes an existing `User`.", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "users", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/users/{userName}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteUser(
			@ApiParam(value = "A unique identifier for a `User`.", required = true) @PathVariable("userName") String userName);

	@ApiOperation(value = "Get a User", nickname = "getUser", notes = "Gets the details of a single instance of a `User`.", response = UserDetails.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "users", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `User`.", response = UserDetails.class) })
	@RequestMapping(value = "/users/{userName}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<UserDetails> getUser(
			@ApiParam(value = "A unique identifier for a `User`.", required = true) @PathVariable("userName") String userName);

	@ApiOperation(value = "List All users", nickname = "getUsers", notes = "Gets a list of all `User` entities.", response = User.class, responseContainer = "List", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "users", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `User` entities.", response = User.class, responseContainer = "List") })
	@RequestMapping(value = "/users", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<User>> getUsers();

	@ApiOperation(value = "Update a User", nickname = "updateUser", notes = "Updates an existing `User`.", response = UserDetails.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "users", })
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Successful response.", response = UserDetails.class) })
	@RequestMapping(value = "/users/{userName}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<UserDetails> updateUser(
			@ApiParam(value = "Updated `User` information.", required = true) @Valid @RequestBody UserDetails body,
			@ApiParam(value = "A unique identifier for a `User`.", required = true) @PathVariable("userName") String userName);

}
