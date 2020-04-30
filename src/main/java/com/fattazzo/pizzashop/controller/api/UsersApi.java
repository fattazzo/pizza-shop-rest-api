/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.User;
import com.fattazzo.pizzashop.model.api.UserDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.List;
import java.util.Map;
@Api(value = "Users", description = "the Users API")
public interface UsersApi {

    @ApiOperation(value = "Create a User", nickname = "createUser", notes = "Creates a new instance of a `User`. Only 'WORKER' type can be created", response = UserDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response.", response = UserDetails.class) })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<UserDetails> createUser(@ApiParam(value = "A new `User` to be created." ,required=true )   @RequestBody UserDetails body
);


    @ApiOperation(value = "Delete a User", nickname = "deleteUser", notes = "Deletes an existing `User`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/users/{userName}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@ApiParam(value = "A unique identifier for a `User`.",required=true) @PathVariable("userName") String userName
);


    @ApiOperation(value = "Get a User", nickname = "getUser", notes = "Gets the details of a single instance of a `User`.", response = UserDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns a single `User`.", response = UserDetails.class) })
    @RequestMapping(value = "/users/{userName}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserDetails> getUser(@ApiParam(value = "A unique identifier for a `User`.",required=true) @PathVariable("userName") String userName
);


    @ApiOperation(value = "List All users", nickname = "getUsers", notes = "Gets a list of all `User` entities.", response = User.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `User` entities.", response = User.class, responseContainer = "List") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers();


    @ApiOperation(value = "Update a User", nickname = "updateUser", notes = "Updates an existing `User`.", response = UserDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Successful response.", response = UserDetails.class),
        @ApiResponse(code = 400, message = "Username cannot change") })
    @RequestMapping(value = "/users/{userName}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<UserDetails> updateUser(@ApiParam(value = "Updated `User` information." ,required=true )   @RequestBody UserDetails body
,@ApiParam(value = "A unique identifier for a `User`.",required=true) @PathVariable("userName") String userName
);

}
