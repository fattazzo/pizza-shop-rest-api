/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.ErrorResponse;
import com.fattazzo.pizzashop.model.api.UserRegistrationInfo;
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
@Api(value = "Registration", description = "the Registration API")
public interface RegistrationApi {

    @ApiOperation(value = "Perform a confirm for a User registration", nickname = "confirmRegistration", notes = "Complete `User` registration and change status to 'Active'", response = String.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = String.class),
        @ApiResponse(code = 400, message = "Confirm registration failed", response = ErrorResponse.class) })
    @RequestMapping(value = "/public/confirm-registration/{username}",
        produces = { "text/xml", "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<String> confirmRegistration(@ApiParam(value = "Username of `User` entity",required=true) @PathVariable("username") String username
,@NotNull @ApiParam(value = "Valid token required to confirm registration", required = true) @Valid @RequestParam(value = "registrationToken", required = true) String registrationToken
);


    @ApiOperation(value = "Create a customer User", nickname = "createCustomer", notes = "Create a new instance of `User` with type 'CUSTOMER' and status \"TO_CONFIRM\"", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response"),
        @ApiResponse(code = 400, message = "Request failed", response = ErrorResponse.class) })
    @RequestMapping(value = "/public/registration-customer",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createCustomer(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserRegistrationInfo body
);


    @ApiOperation(value = "Resend email", nickname = "resendConfirmRegistrationMail", notes = "Resend email with activation code to user", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response"),
        @ApiResponse(code = 400, message = "Request failed", response = ErrorResponse.class) })
    @RequestMapping(value = "/public/resend-confirm-registration-mail",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> resendConfirmRegistrationMail(@NotNull @ApiParam(value = "username of `User`", required = true) @Valid @RequestParam(value = "username", required = true) String username
);

}
