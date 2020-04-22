package com.fattazzo.pizzashop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fattazzo.pizzashop.model.entity.CompanyEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-21T10:22:33.596Z[GMT]")
@Api(value = "company", description = "the company API")
public interface CompanyApi {

	@ApiOperation(value = "Get a Company", nickname = "getCompany", notes = "Gets the details of a single instance of a Company.", response = CompanyEntity.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "companies", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single Company.", response = CompanyEntity.class) })
	@RequestMapping(value = "/company", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<CompanyEntity> getCompany();

	@ApiOperation(value = "Update a Company", nickname = "updateCompany", notes = "Updates an existing Company.", response = CompanyEntity.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "companies", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response.", response = CompanyEntity.class) })
	@RequestMapping(value = "/company", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<CompanyEntity> updateCompany(
			@ApiParam(value = "Updated Company information.", required = true) @Valid @RequestBody CompanyEntity body);

}