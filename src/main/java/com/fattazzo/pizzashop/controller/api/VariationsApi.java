package com.fattazzo.pizzashop.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fattazzo.pizzashop.model.dto.data.Dough;
import com.fattazzo.pizzashop.model.dto.data.Size;
import com.fattazzo.pizzashop.model.dto.data.Topping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T04:45:09.813Z[GMT]")
@Api(value = "variations", description = "the variations API")
public interface VariationsApi {

	@ApiOperation(value = "Create a Dough", nickname = "createDough", notes = "Creates a new instance of a `Dough`.", response = Dough.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful response.", response = Dough.class) })
	@RequestMapping(value = "/variations/doughs", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Dough> createDough(
			@ApiParam(value = "A new `Dough` to be created.", required = true) @Valid @RequestBody Dough body);

	@ApiOperation(value = "Create a Size", nickname = "createSize", notes = "Creates a new instance of a `Size`.", response = Size.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful response.", response = Size.class) })
	@RequestMapping(value = "/variations/sizes", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Size> createSize(
			@ApiParam(value = "A new `Size` to be created.", required = true) @Valid @RequestBody Size body);

	@ApiOperation(value = "Create a Topping", nickname = "createTopping", notes = "Creates a new instance of a `Topping`.", response = Topping.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful response.", response = Topping.class) })
	@RequestMapping(value = "/variations/toppings", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Topping> createTopping(
			@ApiParam(value = "A new `Topping` to be created.", required = true) @Valid @RequestBody Topping body);

	@ApiOperation(value = "Delete a Dough", nickname = "deleteDough", notes = "Deletes an existing `Dough`.", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/variations/doughs/{doughId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteDough(
			@ApiParam(value = "A unique identifier for a `Dough`.", required = true) @PathVariable("doughId") Integer doughId);

	@ApiOperation(value = "Delete a Size", nickname = "deleteSize", notes = "Deletes an existing `Size`.", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/variations/sizes/{sizeId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteSize(
			@ApiParam(value = "A unique identifier for a `Size`.", required = true) @PathVariable("sizeId") Integer sizeId);

	@ApiOperation(value = "Delete a Topping", nickname = "deleteTopping", notes = "Deletes an existing `Topping`.", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/variations/toppings/{toppingId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteTopping(
			@ApiParam(value = "A unique identifier for a `Topping`.", required = true) @PathVariable("toppingId") Integer toppingId);

	@ApiOperation(value = "Get a Dough", nickname = "getDough", notes = "Gets the details of a single instance of a `Dough`.", response = Dough.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `Dough`.", response = Dough.class) })
	@RequestMapping(value = "/variations/doughs/{doughId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<Dough> getDough(
			@ApiParam(value = "A unique identifier for a `Dough`.", required = true) @PathVariable("doughId") Integer doughId);

	@ApiOperation(value = "List All doughs", nickname = "getDoughs", notes = "Gets a list of all `Dough` entities.", response = Dough.class, responseContainer = "List", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `Dough` entities.", response = Dough.class, responseContainer = "List") })
	@RequestMapping(value = "/variations/doughs", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Dough>> getDoughs(
			@ApiParam(value = "If true, the list of all entities include enabled and disabled `Dough`") @Valid @RequestParam(value = "includeDisabled", required = false) Boolean includeDisabled);

	@ApiOperation(value = "Get a Size", nickname = "getSize", notes = "Gets the details of a single instance of a `Size`.", response = Size.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `Size`.", response = Size.class) })
	@RequestMapping(value = "/variations/sizes/{sizeId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Size> getSize(
			@ApiParam(value = "A unique identifier for a `Size`.", required = true) @PathVariable("sizeId") Integer sizeId);

	@ApiOperation(value = "List All sizes", nickname = "getSizes", notes = "Gets a list of all `Size` entities.", response = Size.class, responseContainer = "List", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `Size` entities.", response = Size.class, responseContainer = "List") })
	@RequestMapping(value = "/variations/sizes", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Size>> getSizes(
			@ApiParam(value = "If true, the list of all entities include enabled and disabled `Size`") @Valid @RequestParam(value = "includeDisabled", required = false) Boolean includeDisabled);

	@ApiOperation(value = "Get a Topping", nickname = "getTopping", notes = "Gets the details of a single instance of a `Topping`.", response = Topping.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `Topping`.", response = Topping.class) })
	@RequestMapping(value = "/variations/toppings/{toppingId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<Topping> getTopping(
			@ApiParam(value = "A unique identifier for a `Topping`.", required = true) @PathVariable("toppingId") Integer toppingId);

	@ApiOperation(value = "List All toppings", nickname = "getToppings", notes = "Gets a list of all `Topping` entities.", response = Topping.class, responseContainer = "List", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `Topping` entities.", response = Topping.class, responseContainer = "List") })
	@RequestMapping(value = "/variations/toppings", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Topping>> getToppings(
			@ApiParam(value = "If true, the list of all entities include enabled and disabled `Topping`") @Valid @RequestParam(value = "includeDisabled", required = false) Boolean includeDisabled);

	@ApiOperation(value = "Update a Dough", nickname = "updateDough", notes = "Updates an existing `Dough`.", response = Dough.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Successful response.", response = Dough.class) })
	@RequestMapping(value = "/variations/doughs/{doughId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Dough> updateDough(
			@ApiParam(value = "Updated `Dough` information.", required = true) @Valid @RequestBody Dough body,
			@ApiParam(value = "A unique identifier for a `Dough`.", required = true) @PathVariable("doughId") Integer doughId);

	@ApiOperation(value = "Update a Size", nickname = "updateSize", notes = "Updates an existing `Size`.", response = Size.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Successful response.", response = Size.class) })
	@RequestMapping(value = "/variations/sizes/{sizeId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Size> updateSize(
			@ApiParam(value = "Updated `Size` information.", required = true) @Valid @RequestBody Size body,
			@ApiParam(value = "A unique identifier for a `Size`.", required = true) @PathVariable("sizeId") Integer sizeId);

	@ApiOperation(value = "Update a Topping", nickname = "updateTopping", notes = "Updates an existing `Topping`.", response = Topping.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "variations", })
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Successful response.", response = Topping.class) })
	@RequestMapping(value = "/variations/toppings/{toppingId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Topping> updateTopping(
			@ApiParam(value = "Updated `Topping` information.", required = true) @Valid @RequestBody Topping body,
			@ApiParam(value = "A unique identifier for a `Topping`.", required = true) @PathVariable("toppingId") Integer toppingId);

}
