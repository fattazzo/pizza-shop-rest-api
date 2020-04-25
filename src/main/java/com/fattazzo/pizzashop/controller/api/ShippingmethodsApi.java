/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.ShippingMethod;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T15:30:26.294Z[GMT]")
@Api(value = "shippingmethods", description = "the shippingmethods API")
public interface ShippingmethodsApi {

    @ApiOperation(value = "Create a ShippingMethod", nickname = "createShippingMethod", notes = "Creates a new instance of a `ShippingMethod`.", response = ShippingMethod.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response.", response = ShippingMethod.class) })
    @RequestMapping(value = "/shippingmethods",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ShippingMethod> createShippingMethod(@ApiParam(value = "A new `ShippingMethod` to be created." ,required=true )  @Valid @RequestBody ShippingMethod body
);


    @ApiOperation(value = "Delete a ShippingMethod", nickname = "deleteShippingMethod", notes = "Deletes an existing `ShippingMethod`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/shippingmethods/{shippingmethodId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteShippingMethod(@ApiParam(value = "A unique identifier for a `ShippingMethod`.",required=true) @PathVariable("shippingmethodId") Integer shippingmethodId
);


    @ApiOperation(value = "Get a ShippingMethod", nickname = "getShippingMethod", notes = "Gets the details of a single instance of a `ShippingMethod`.", response = ShippingMethod.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns a single `ShippingMethod`.", response = ShippingMethod.class) })
    @RequestMapping(value = "/shippingmethods/{shippingmethodId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ShippingMethod> getShippingMethod(@ApiParam(value = "A unique identifier for a `ShippingMethod`.",required=true) @PathVariable("shippingmethodId") Integer shippingmethodId
);


    @ApiOperation(value = "List All shippingmethods", nickname = "getShippingMethods", notes = "Gets a list of all `ShippingMethod` entities.", response = ShippingMethod.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `ShippingMethod` entities.", response = ShippingMethod.class, responseContainer = "List") })
    @RequestMapping(value = "/shippingmethods",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ShippingMethod>> getShippingMethods();


    @ApiOperation(value = "Update a ShippingMethod", nickname = "updateShippingMethod", notes = "Updates an existing `ShippingMethod`.", response = ShippingMethod.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Successful response.", response = ShippingMethod.class) })
    @RequestMapping(value = "/shippingmethods/{shippingmethodId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ShippingMethod> updateShippingMethod(@ApiParam(value = "Updated `ShippingMethod` information." ,required=true )  @Valid @RequestBody ShippingMethod body
,@ApiParam(value = "A unique identifier for a `ShippingMethod`.",required=true) @PathVariable("shippingmethodId") Integer shippingmethodId
);

}
