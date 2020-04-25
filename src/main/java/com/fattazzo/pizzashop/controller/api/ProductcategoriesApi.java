package com.fattazzo.pizzashop.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fattazzo.pizzashop.model.dto.data.ProductCategory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-24T16:09:40.471Z[GMT]")
@Api(value = "productcategories", description = "the productcategories API")
public interface ProductcategoriesApi {

	@ApiOperation(value = "Create a ProductCategory", nickname = "createProductCategory", notes = "Creates a new instance of a `ProductCategory`.", response = ProductCategory.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "products", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful response.", response = ProductCategory.class) })
	@RequestMapping(value = "/productcategories", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<ProductCategory> createProductCategory(
			@ApiParam(value = "A new `ProductCategory` to be created.", required = true) @Valid @RequestBody ProductCategory body);

	@ApiOperation(value = "Delete a ProductCategory", nickname = "deleteProductCategory", notes = "Deletes an existing `ProductCategory`.", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "products", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successful response.") })
	@RequestMapping(value = "/productcategories/{productcategoryId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteProductCategory(
			@ApiParam(value = "A unique identifier for a `ProductCategory`.", required = true) @PathVariable("productcategoryId") Integer productcategoryId);

	@ApiOperation(value = "List All productcategories", nickname = "getProductCategories", notes = "Gets a list of all `ProductCategory` entities.", response = ProductCategory.class, responseContainer = "List", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "products", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns an array of `ProductCategory` entities.", response = ProductCategory.class, responseContainer = "List") })
	@RequestMapping(value = "/productcategories", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<ProductCategory>> getProductCategories(
			@ApiParam(value = "If true, the list of all entities include enabled and disabled `ProductCategory`") @Valid @RequestParam(value = "includeDisabled", required = false) Boolean includeDisabled);

	@ApiOperation(value = "Get a ProductCategory", nickname = "getProductCategory", notes = "Gets the details of a single instance of a `ProductCategory`.", response = ProductCategory.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "products", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful response - returns a single `ProductCategory`.", response = ProductCategory.class) })
	@RequestMapping(value = "/productcategories/{productcategoryId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<ProductCategory> getProductCategory(
			@ApiParam(value = "A unique identifier for a `ProductCategory`.", required = true) @PathVariable("productcategoryId") Integer productcategoryId);

	@ApiOperation(value = "Update a ProductCategory", nickname = "updateProductCategory", notes = "Updates an existing `ProductCategory`.", response = ProductCategory.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "products", })
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Successful response.", response = ProductCategory.class) })
	@RequestMapping(value = "/productcategories/{productcategoryId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<ProductCategory> updateProductCategory(
			@ApiParam(value = "Updated `ProductCategory` information.", required = true) @Valid @RequestBody ProductCategory body,
			@ApiParam(value = "A unique identifier for a `ProductCategory`.", required = true) @PathVariable("productcategoryId") Integer productcategoryId);

}
