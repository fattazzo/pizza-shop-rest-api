package com.fattazzo.pizzashop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fattazzo.pizzashop.model.dto.Branch;
import com.fattazzo.pizzashop.model.dto.BranchDetails;
import com.fattazzo.pizzashop.model.dto.ShippingZone;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-22T16:10:05.569Z[GMT]")
@Api(value = "branches", description = "the branches API")
public interface BranchesApi {

	@ApiOperation(value = "Create a Branch for company", nickname = "createBranch", notes = "Creates a new instance of a `Branch`.", response = BranchDetails.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful response.", response = BranchDetails.class) })
	@RequestMapping(value = "/branches", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<BranchDetails> createBranch(
			@ApiParam(value = "A new `Branch` to be created.", required = true) @Valid @RequestBody BranchDetails body);

	@ApiOperation(value = "Create a ShippingZone", nickname = "createShippingZone", notes = "Creates a new instance of a `ShippingZone`.", response = ShippingZone.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful response.", response = ShippingZone.class) })
	@RequestMapping(value = "/branches/{branchId}/shippingzones", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<ShippingZone> createShippingZone(
			@ApiParam(value = "A new `ShippingZone` to be created.", required = true) @Valid @RequestBody ShippingZone body,
			@ApiParam(value = "A unique identifier for a `Company`.", required = true) @PathVariable("branchId") Integer branchId);

	@ApiOperation(value = "Delete a Branch", nickname = "deleteBranch", notes = "Deletes an existing `Branch`.", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/branches/{branchId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteBranch(
			@ApiParam(value = "A unique identifier for a `Branch`.", required = true) @PathVariable("branchId") Integer branchId);

	@ApiOperation(value = "Delete a ShippingZone", nickname = "deleteShippingZone", notes = "Deletes an existing `ShippingZone`.", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/branches/{branchId}/shippingzones/{shippingzoneId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteShippingZone(
			@ApiParam(value = "A unique identifier for a `ShippingZone`.", required = true) @PathVariable("shippingzoneId") Integer shippingzoneId,
			@ApiParam(value = "A unique identifier for a `Branch`.", required = true) @PathVariable("branchId") Integer branchId);

	@ApiOperation(value = "Get a Branch", nickname = "getBranch", notes = "Gets the details of a single instance of a `Branch`.", response = BranchDetails.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `Branch`.", response = BranchDetails.class) })
	@RequestMapping(value = "/branches/{branchId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<BranchDetails> getBranch(
			@ApiParam(value = "A unique identifier for a `Branch`.", required = true) @PathVariable("branchId") Integer branchId);

	@ApiOperation(value = "List All branches of company", nickname = "getBranches", notes = "Gets a list of all `Branch` entities.", response = Branch.class, responseContainer = "List", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `Branch` entities.", response = Branch.class, responseContainer = "List") })
	@RequestMapping(value = "/branches", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Branch>> getBranches();

	@ApiOperation(value = "Get a ShippingZone", nickname = "getShippingZone", notes = "Gets the details of a single instance of a `ShippingZone`.", response = ShippingZone.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `ShippingZone`.", response = ShippingZone.class) })
	@RequestMapping(value = "/branches/{branchId}/shippingzones/{shippingzoneId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<ShippingZone> getShippingZone(
			@ApiParam(value = "A unique identifier for a `ShippingZone`.", required = true) @PathVariable("shippingzoneId") Integer shippingzoneId,
			@ApiParam(value = "A unique identifier for a `Branch`.", required = true) @PathVariable("branchId") Integer branchId);

	@ApiOperation(value = "List All shippingzones", nickname = "getShippingZones", notes = "Gets a list of all `ShippingZone` entities.", response = ShippingZone.class, responseContainer = "List", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `ShippingZone` entities.", response = ShippingZone.class, responseContainer = "List") })
	@RequestMapping(value = "/branches/{branchId}/shippingzones", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<ShippingZone>> getShippingZones(
			@ApiParam(value = "A unique identifier for a `Company`.", required = true) @PathVariable("branchId") Integer branchId);

	@ApiOperation(value = "Update a Branch", nickname = "updateBranch", notes = "Updates an existing `Branch`.", response = BranchDetails.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Successful response.", response = BranchDetails.class) })
	@RequestMapping(value = "/branches/{branchId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<BranchDetails> updateBranch(
			@ApiParam(value = "Updated `Branch` information.", required = true) @Valid @RequestBody BranchDetails body,
			@ApiParam(value = "A unique identifier for a `Branch`.", required = true) @PathVariable("branchId") Integer branchId);

	@ApiOperation(value = "Update a ShippingZone", nickname = "updateShippingZone", notes = "Updates an existing `ShippingZone`.", response = ShippingZone.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "branches", })
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Successful response.", response = ShippingZone.class) })
	@RequestMapping(value = "/branches/{branchId}/shippingzones/{shippingzoneId}", produces = {
			"application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
	ResponseEntity<ShippingZone> updateShippingZone(
			@ApiParam(value = "Updated `ShippingZone` information.", required = true) @Valid @RequestBody ShippingZone body,
			@ApiParam(value = "A unique identifier for a `ShippingZone`.", required = true) @PathVariable("shippingzoneId") Integer shippingzoneId,
			@ApiParam(value = "A unique identifier for a `Branch`.", required = true) @PathVariable("branchId") Integer branchId);

}