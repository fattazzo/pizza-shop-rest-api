/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.ShippingMethod;
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
@Api(value = "Shippingmethods", description = "the Shippingmethods API")
public interface ShippingmethodsApi {

    Logger log = LoggerFactory.getLogger(ShippingmethodsApi.class);

    default Optional<ObjectMapper> getObjectMapper(){
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest(){
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Create a ShippingMethod", nickname = "createShippingMethod", notes = "Creates a new instance of a `ShippingMethod`.", response = ShippingMethod.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response.", response = ShippingMethod.class) })
    @RequestMapping(value = "/shippingmethods",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<ShippingMethod> createShippingMethod(@ApiParam(value = "A new `ShippingMethod` to be created." ,required=true )  @Valid @RequestBody ShippingMethod body
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"id\" : 1,\n  \"title\" : \"Cash on delivery\",\n  \"description\" : \"Paying for goods when they are delivered.\",\n  \"type\" : \"CASH_ON_DELIVERY\",\n  \"enabled\" : true\n}", ShippingMethod.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ShippingmethodsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete a ShippingMethod", nickname = "deleteShippingMethod", notes = "Deletes an existing `ShippingMethod`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/shippingmethods/{shippingmethodId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteShippingMethod(@ApiParam(value = "A unique identifier for a `ShippingMethod`.",required=true) @PathVariable("shippingmethodId") Integer shippingmethodId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ShippingmethodsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Get a ShippingMethod", nickname = "getShippingMethod", notes = "Gets the details of a single instance of a `ShippingMethod`.", response = ShippingMethod.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns a single `ShippingMethod`.", response = ShippingMethod.class) })
    @RequestMapping(value = "/shippingmethods/{shippingmethodId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<ShippingMethod> getShippingMethod(@ApiParam(value = "A unique identifier for a `ShippingMethod`.",required=true) @PathVariable("shippingmethodId") Integer shippingmethodId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"id\" : 1,\n  \"title\" : \"Cash on delivery\",\n  \"description\" : \"Paying for goods when they are delivered.\",\n  \"type\" : \"CASH_ON_DELIVERY\",\n  \"enabled\" : true\n}", ShippingMethod.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ShippingmethodsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "List All shippingmethods", nickname = "getShippingMethods", notes = "Gets a list of all `ShippingMethod` entities.", response = ShippingMethod.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `ShippingMethod` entities.", response = ShippingMethod.class, responseContainer = "List") })
    @RequestMapping(value = "/shippingmethods",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<ShippingMethod>> getShippingMethods(@ApiParam(value = "If true, the list of all entities include enabled and disabled `Size`", defaultValue = "false") @Valid @RequestParam(value = "includeDisabled", required = false, defaultValue="false") Boolean includeDisabled
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {\n  \"id\" : 1,\n  \"title\" : \"Cash on delivery\",\n  \"description\" : \"Paying for goods when they are delivered.\",\n  \"type\" : \"CASH_ON_DELIVERY\",\n  \"enabled\" : true\n}, {\n  \"id\" : 1,\n  \"title\" : \"Cash on delivery\",\n  \"description\" : \"Paying for goods when they are delivered.\",\n  \"type\" : \"CASH_ON_DELIVERY\",\n  \"enabled\" : true\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ShippingmethodsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update a ShippingMethod", nickname = "updateShippingMethod", notes = "Updates an existing `ShippingMethod`.", response = ShippingMethod.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "shippingmethods", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Successful response.", response = ShippingMethod.class) })
    @RequestMapping(value = "/shippingmethods/{shippingmethodId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<ShippingMethod> updateShippingMethod(@ApiParam(value = "Updated `ShippingMethod` information." ,required=true )  @Valid @RequestBody ShippingMethod body
,@ApiParam(value = "A unique identifier for a `ShippingMethod`.",required=true) @PathVariable("shippingmethodId") Integer shippingmethodId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"id\" : 1,\n  \"title\" : \"Cash on delivery\",\n  \"description\" : \"Paying for goods when they are delivered.\",\n  \"type\" : \"CASH_ON_DELIVERY\",\n  \"enabled\" : true\n}", ShippingMethod.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ShippingmethodsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
