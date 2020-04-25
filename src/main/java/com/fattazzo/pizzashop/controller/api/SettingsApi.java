package com.fattazzo.pizzashop.controller.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fattazzo.pizzashop.model.dto.security.Settings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-24T13:51:52.050Z[GMT]")
@Api(value = "settings", description = "the settings API")
public interface SettingsApi {

	@ApiOperation(value = "Get a Settings", nickname = "getSettings", notes = "Gets the details of a single instance of a `Settings`.", response = Settings.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "settings", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `Settings`.", response = Settings.class) })
	@RequestMapping(value = "/settings/", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Settings> getSettings();

	@ApiOperation(value = "Update a Settings", nickname = "updateSettings", notes = "Updates an existing `Settings`.", response = Settings.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "settings", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response.", response = Settings.class) })
	@RequestMapping(value = "/settings/", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Settings> updateSettings(
			@ApiParam(value = "Updated `Settings` information.", required = true) @Valid @RequestBody Settings body);

}
