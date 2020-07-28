/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.OrderLine;
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
@Api(value = "Orderslines", description = "the Orderslines API")
public interface OrderslinesApi {

    Logger log = LoggerFactory.getLogger(OrderslinesApi.class);

    default Optional<ObjectMapper> getObjectMapper(){
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest(){
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Create a OrderLine", nickname = "createOrderLine", notes = "Creates a new instance of a `OrderLine`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "orderslines", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response.") })
    @RequestMapping(value = "/orders/{orderId}/lines",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> createOrderLine(@ApiParam(value = "A new `OrderLine` to be created." ,required=true )  @Valid @RequestBody OrderLine body
,@ApiParam(value = "A unique identifier for a `Order`.",required=true) @PathVariable("orderId") Integer orderId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrderslinesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete a OrderLine", nickname = "deleteOrderLine", notes = "Deletes an existing `OrderLine`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "orderslines", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/orders/{orderId}/lines/{orderlineId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteOrderLine(@ApiParam(value = "A unique identifier for a `Order`.",required=true) @PathVariable("orderId") Integer orderId
,@ApiParam(value = "A unique identifier for a `OrderLine`.",required=true) @PathVariable("orderlineId") Integer orderlineId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrderslinesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Get a OrderLine", nickname = "getOrderLine", notes = "Gets the details of a single instance of a `OrderLine`.", response = OrderLine.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "orderslines", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns a single `OrderLine`.", response = OrderLine.class) })
    @RequestMapping(value = "/orders/{orderId}/lines/{orderlineId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<OrderLine> getOrderLine(@ApiParam(value = "A unique identifier for a `Order`.",required=true) @PathVariable("orderId") Integer orderId
,@ApiParam(value = "A unique identifier for a `OrderLine`.",required=true) @PathVariable("orderlineId") Integer orderlineId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"pizzaDough\" : {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  },\n  \"item\" : {\n    \"availablePrices\" : [ 6.027456183070403, 6.027456183070403 ],\n    \"imageUrl\" : \"imageUrl\",\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 0,\n    \"category\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 0,\n      \"type\" : \"PIZZA\",\n      \"enabled\" : true,\n      \"order\" : 6\n    },\n    \"enabled\" : true\n  },\n  \"total\" : 1.4658129805029452,\n  \"quantity\" : 6,\n  \"pizzaToppingExtras\" : [ {\n    \"extra\" : 2.3021358869347655,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    },\n    \"topping\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 6,\n      \"enabled\" : true\n    },\n    \"dough\" : {\n      \"extra\" : 5.962133916683182,\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 1,\n      \"enabled\" : true,\n      \"order\" : 5\n    },\n    \"enabled\" : true\n  }, {\n    \"extra\" : 2.3021358869347655,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    },\n    \"topping\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 6,\n      \"enabled\" : true\n    },\n    \"dough\" : {\n      \"extra\" : 5.962133916683182,\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 1,\n      \"enabled\" : true,\n      \"order\" : 5\n    },\n    \"enabled\" : true\n  } ],\n  \"customerNote\" : \"customerNote\",\n  \"id\" : 0,\n  \"pizzaPrice\" : {\n    \"price\" : 6.027456183070403,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    }\n  },\n  \"productPrice\" : {\n    \"price\" : 6.027456183070403,\n    \"id\" : 0,\n    \"variation\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 0,\n      \"enabled\" : true,\n      \"order\" : 6\n    }\n  }\n}", OrderLine.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrderslinesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "List All OrderLines", nickname = "getOrderLines", notes = "Gets a list of all `OrderLine` entities.", response = OrderLine.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "orderslines", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `OrderLine` entities.", response = OrderLine.class, responseContainer = "List") })
    @RequestMapping(value = "/orders/{orderId}/lines",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<OrderLine>> getOrderLines(@ApiParam(value = "A unique identifier for a `Order`.",required=true) @PathVariable("orderId") Integer orderId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {\n  \"pizzaDough\" : {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  },\n  \"item\" : {\n    \"availablePrices\" : [ 6.027456183070403, 6.027456183070403 ],\n    \"imageUrl\" : \"imageUrl\",\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 0,\n    \"category\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 0,\n      \"type\" : \"PIZZA\",\n      \"enabled\" : true,\n      \"order\" : 6\n    },\n    \"enabled\" : true\n  },\n  \"total\" : 1.4658129805029452,\n  \"quantity\" : 6,\n  \"pizzaToppingExtras\" : [ {\n    \"extra\" : 2.3021358869347655,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    },\n    \"topping\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 6,\n      \"enabled\" : true\n    },\n    \"dough\" : {\n      \"extra\" : 5.962133916683182,\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 1,\n      \"enabled\" : true,\n      \"order\" : 5\n    },\n    \"enabled\" : true\n  }, {\n    \"extra\" : 2.3021358869347655,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    },\n    \"topping\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 6,\n      \"enabled\" : true\n    },\n    \"dough\" : {\n      \"extra\" : 5.962133916683182,\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 1,\n      \"enabled\" : true,\n      \"order\" : 5\n    },\n    \"enabled\" : true\n  } ],\n  \"customerNote\" : \"customerNote\",\n  \"id\" : 0,\n  \"pizzaPrice\" : {\n    \"price\" : 6.027456183070403,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    }\n  },\n  \"productPrice\" : {\n    \"price\" : 6.027456183070403,\n    \"id\" : 0,\n    \"variation\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 0,\n      \"enabled\" : true,\n      \"order\" : 6\n    }\n  }\n}, {\n  \"pizzaDough\" : {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  },\n  \"item\" : {\n    \"availablePrices\" : [ 6.027456183070403, 6.027456183070403 ],\n    \"imageUrl\" : \"imageUrl\",\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 0,\n    \"category\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 0,\n      \"type\" : \"PIZZA\",\n      \"enabled\" : true,\n      \"order\" : 6\n    },\n    \"enabled\" : true\n  },\n  \"total\" : 1.4658129805029452,\n  \"quantity\" : 6,\n  \"pizzaToppingExtras\" : [ {\n    \"extra\" : 2.3021358869347655,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    },\n    \"topping\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 6,\n      \"enabled\" : true\n    },\n    \"dough\" : {\n      \"extra\" : 5.962133916683182,\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 1,\n      \"enabled\" : true,\n      \"order\" : 5\n    },\n    \"enabled\" : true\n  }, {\n    \"extra\" : 2.3021358869347655,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    },\n    \"topping\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 6,\n      \"enabled\" : true\n    },\n    \"dough\" : {\n      \"extra\" : 5.962133916683182,\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 1,\n      \"enabled\" : true,\n      \"order\" : 5\n    },\n    \"enabled\" : true\n  } ],\n  \"customerNote\" : \"customerNote\",\n  \"id\" : 0,\n  \"pizzaPrice\" : {\n    \"price\" : 6.027456183070403,\n    \"id\" : 0,\n    \"variationSize\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 7,\n      \"enabled\" : true,\n      \"order\" : 9\n    }\n  },\n  \"productPrice\" : {\n    \"price\" : 6.027456183070403,\n    \"id\" : 0,\n    \"variation\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : 0,\n      \"enabled\" : true,\n      \"order\" : 6\n    }\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrderslinesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update a OrderLine", nickname = "updateOrderLine", notes = "Updates an existing `OrderLine`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "orderslines", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Successful response.") })
    @RequestMapping(value = "/orders/{orderId}/lines/{orderlineId}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> updateOrderLine(@ApiParam(value = "Updated `OrderLine` information." ,required=true )  @Valid @RequestBody OrderLine body
,@ApiParam(value = "A unique identifier for a `Order`.",required=true) @PathVariable("orderId") Integer orderId
,@ApiParam(value = "A unique identifier for a `OrderLine`.",required=true) @PathVariable("orderlineId") Integer orderlineId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrderslinesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
