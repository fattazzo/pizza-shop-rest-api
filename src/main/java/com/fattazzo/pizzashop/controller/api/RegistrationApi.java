/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.ErrorResponse;
import com.fattazzo.pizzashop.model.api.UserRegistrationInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Api(value = "Registration", description = "the Registration API")
public interface RegistrationApi {

    Logger log = LoggerFactory.getLogger(RegistrationApi.class);

    default Optional<ObjectMapper> getObjectMapper(){
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest(){
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Perform a confirm for a User registration", nickname = "confirmRegistration", notes = "Complete `User` registration and change status to 'Active'", response = String.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = String.class),
        @ApiResponse(code = 400, message = "Confirm registration failed", response = ErrorResponse.class) })
    @RequestMapping(value = "/public/confirm-registration/{username}",
        produces = { "text/xml", "application/json" }, 
        method = RequestMethod.POST)
    default ResponseEntity<String> confirmRegistration(@ApiParam(value = "Username of `User` entity",required=true) @PathVariable("username") String username
,@NotNull @ApiParam(value = "Valid token required to confirm registration", required = true) @Valid @RequestParam(value = "registrationToken", required = true) String registrationToken
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default RegistrationApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Create a customer User", nickname = "createCustomer", notes = "Create a new instance of `User` with type 'CUSTOMER' and status \"TO_CONFIRM\"", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response"),
        @ApiResponse(code = 400, message = "Request failed", response = ErrorResponse.class) })
    @RequestMapping(value = "/public/registration-customer",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> createCustomer(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserRegistrationInfo body
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default RegistrationApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Resend email", nickname = "resendConfirmRegistrationMail", notes = "Resend email with activation code to user", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response"),
        @ApiResponse(code = 400, message = "Request failed", response = ErrorResponse.class) })
    @RequestMapping(value = "/public/resend-confirm-registration-mail",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    default ResponseEntity<Void> resendConfirmRegistrationMail(@NotNull @ApiParam(value = "username of `User`", required = true) @Valid @RequestParam(value = "username", required = true) String username
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default RegistrationApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
