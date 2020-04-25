package com.fattazzo.pizzashop.controller.api;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fattazzo.pizzashop.model.dto.data.Company;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-23T14:00:18.083Z[GMT]")
@Api(value = "company", description = "the company API")
public interface CompanyApi {

	@ApiOperation(value = "Get a Company", nickname = "getCompany", notes = "Gets the details of a single instance of a `Company`.", response = Company.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "companies", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `Company`.", response = Company.class) })
	@RequestMapping(value = "/company", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Company> getCompany();

	@ApiOperation(value = "Company logo", nickname = "getLogo", notes = "Get a `Company` logo image", response = Resource.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "companies", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Logo image", response = Resource.class) })
	@RequestMapping(value = "/company/logo", produces = { "image/png" }, method = RequestMethod.GET)
	ResponseEntity<Resource> getLogo();

	@ApiOperation(value = "Update a Company", nickname = "updateCompany", notes = "Updates an existing `Company`.", response = Company.class, authorizations = {
			@Authorization(value = "Authentication") }, tags = { "companies", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response.", response = Company.class) })
	@RequestMapping(value = "/company", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Company> updateCompany(
			@ApiParam(value = "Updated `Company` information.", required = true) @Valid @RequestBody Company body);

	@ApiOperation(value = "Update a Company logo", nickname = "updateLogo", notes = "Updates an existing `Company` logo.", authorizations = {
			@Authorization(value = "Authentication") }, tags = { "companies", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/company/logo", consumes = { "multipart/form-data" }, method = RequestMethod.PUT)
	ResponseEntity<Void> updateLogo(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file);

}
