/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.Branch;
import com.fattazzo.pizzashop.model.api.BranchDetails;
import com.fattazzo.pizzashop.model.api.ShippingZone;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-28T06:15:14.926Z[GMT]")
@Api(value = "branches", description = "the branches API")
public interface BranchesApi {

    @ApiOperation(value = "Create a Branch for company", nickname = "createBranch", notes = "Creates a new instance of a `Branch`.", response = BranchDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "branches", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response.", response = BranchDetails.class),
        @ApiResponse(code = 409, message = "Fail primary check on `Branch`. Only one primary branch is permitted.") })
    @RequestMapping(value = "/branches",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<BranchDetails> createBranch(@ApiParam(value = "A new `Branch` to be created." ,required=true )  @Valid @RequestBody BranchDetails body
);


    @ApiOperation(value = "Delete a Branch", nickname = "deleteBranch", notes = "Deletes an existing `Branch`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "branches", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/branches/{branchId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteBranch(@ApiParam(value = "A unique identifier for a `Branch`.",required=true) @PathVariable("branchId") Integer branchId
);


    @ApiOperation(value = "Get a Branch", nickname = "getBranch", notes = "Gets the details of a single instance of a `Branch`.", response = BranchDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "branches", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns a single `Branch`.", response = BranchDetails.class) })
    @RequestMapping(value = "/branches/{branchId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<BranchDetails> getBranch(@ApiParam(value = "A unique identifier for a `Branch`.",required=true) @PathVariable("branchId") Integer branchId
);


    @ApiOperation(value = "List All branches of company", nickname = "getBranches", notes = "Gets a list of all `Branch` entities.", response = Branch.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "branches", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `Branch` entities.", response = Branch.class, responseContainer = "List") })
    @RequestMapping(value = "/branches",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Branch>> getBranches();


    @ApiOperation(value = "List All shippingzones", nickname = "getShippingZones", notes = "Gets a list of all `ShippingZone` entities.", response = ShippingZone.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "branches", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `ShippingZone` entities.", response = ShippingZone.class, responseContainer = "List") })
    @RequestMapping(value = "/branches/{branchId}/shippingzones",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ShippingZone>> getShippingZones(@ApiParam(value = "A unique identifier for a `Branch`.",required=true) @PathVariable("branchId") Integer branchId
);


    @ApiOperation(value = "Update a Branch", nickname = "updateBranch", notes = "Updates an existing `Branch`.", response = BranchDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "branches", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Successful response.", response = BranchDetails.class),
        @ApiResponse(code = 409, message = "Fail primary check on `Branch`. Only one primary branch is permitted.") })
    @RequestMapping(value = "/branches/{branchId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<BranchDetails> updateBranch(@ApiParam(value = "Updated `Branch` information." ,required=true )  @Valid @RequestBody BranchDetails body
,@ApiParam(value = "A unique identifier for a `Branch`.",required=true) @PathVariable("branchId") Integer branchId
);

}
