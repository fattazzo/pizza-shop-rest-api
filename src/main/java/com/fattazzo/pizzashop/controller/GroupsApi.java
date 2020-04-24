package com.fattazzo.pizzashop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fattazzo.pizzashop.model.dto.security.Group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-22T05:21:08.184Z[GMT]")
@Api(value = "groups", description = "the groups API")
public interface GroupsApi {

	@ApiOperation(value = "Create a Group", nickname = "createGroup", notes = "Creates a new instance of a `Group`.", response = Group.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "groups", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful response.", response = Group.class) })
	@RequestMapping(value = "/groups", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Group> createGroup(
			@ApiParam(value = "A new `Group` to be created.", required = true) @Valid @RequestBody Group body);

	@ApiOperation(value = "Delete a Group", nickname = "deleteGroup", notes = "Deletes an existing `Group`.", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "groups", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/groups/{groupId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteGroup(
			@ApiParam(value = "A unique identifier for a `Company`.", required = true) @PathVariable("groupId") Integer groupId);

	@ApiOperation(value = "Get a Group", nickname = "getGroup", notes = "Gets the details of a single instance of a `Group`.", response = Group.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "groups", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `Group`.", response = Group.class) })
	@RequestMapping(value = "/groups/{groupId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Group> getGroup(
			@ApiParam(value = "A unique identifier for a `Company`.", required = true) @PathVariable("groupId") Integer groupId);

	@ApiOperation(value = "List All groups", nickname = "getGroups", notes = "Gets a list of all `Group` entities.", response = Group.class, responseContainer = "List", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "groups", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `Group` entities.", response = Group.class, responseContainer = "List") })
	@RequestMapping(value = "/groups", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Group>> getGroups();

	@ApiOperation(value = "Update a Group", nickname = "updateGroup", notes = "Updates an existing `Group`.", response = Group.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "groups", })
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Successful response.", response = Group.class) })
	@RequestMapping(value = "/groups/{groupId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Group> updateGroup(
			@ApiParam(value = "Updated `Group` information.", required = true) @Valid @RequestBody Group body,
			@ApiParam(value = "A unique identifier for a `Company`.", required = true) @PathVariable("groupId") Integer groupId);

}