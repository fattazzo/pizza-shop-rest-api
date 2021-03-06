/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.Category;
import com.fattazzo.pizzashop.model.api.CategoryDetails;
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
@Api(value = "Pizzacategories", description = "the Pizzacategories API")
public interface PizzacategoriesApi {

    Logger log = LoggerFactory.getLogger(PizzacategoriesApi.class);

    default Optional<ObjectMapper> getObjectMapper(){
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest(){
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Create a pizza Category", nickname = "createPizzaCategory", notes = "Creates a new instance of a pizza `Category`.", response = CategoryDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "pizzacategories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response.", response = CategoryDetails.class) })
    @RequestMapping(value = "/pizza/categories",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<CategoryDetails> createPizzaCategory(@ApiParam(value = "A new pizza `Category` to be created." ,required=true )  @Valid @RequestBody CategoryDetails body
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"sizes\" : [ {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 7,\n    \"enabled\" : true,\n    \"order\" : 9\n  }, {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 7,\n    \"enabled\" : true,\n    \"order\" : 9\n  } ],\n  \"doughs\" : [ {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  }, {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  } ]\n}", CategoryDetails.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default PizzacategoriesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete a pizza Category", nickname = "deletePizzaCategory", notes = "Deletes an existing pizza `Category`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "pizzacategories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/pizza/categories/{categoryId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deletePizzaCategory(@ApiParam(value = "A unique identifier for a `Category`.",required=true) @PathVariable("categoryId") Integer categoryId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default PizzacategoriesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "List All pizza categories", nickname = "getPizzaCategories", notes = "Gets a list of all pizza `Category` entities.", response = Category.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "pizzacategories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `Category` entities.", response = Category.class, responseContainer = "List") })
    @RequestMapping(value = "/pizza/categories",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Category>> getPizzaCategories(@ApiParam(value = "If true, the list of all entities include enabled and disabled `Category`" ) @RequestHeader(value="includeDisabled", required=false) Boolean includeDisabled
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {\n  \"name\" : \"name\",\n  \"description\" : \"description\",\n  \"id\" : 0,\n  \"type\" : \"PIZZA\",\n  \"enabled\" : true,\n  \"order\" : 6\n}, {\n  \"name\" : \"name\",\n  \"description\" : \"description\",\n  \"id\" : 0,\n  \"type\" : \"PIZZA\",\n  \"enabled\" : true,\n  \"order\" : 6\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default PizzacategoriesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Get a pizza Category", nickname = "getPizzaCategory", notes = "Gets the details of a single instance of a pizza `Category`.", response = CategoryDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "pizzacategories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns a single `Category`.", response = CategoryDetails.class) })
    @RequestMapping(value = "/pizza/categories/{categoryId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<CategoryDetails> getPizzaCategory(@ApiParam(value = "A unique identifier for a `Category`.",required=true) @PathVariable("categoryId") Integer categoryId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"sizes\" : [ {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 7,\n    \"enabled\" : true,\n    \"order\" : 9\n  }, {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 7,\n    \"enabled\" : true,\n    \"order\" : 9\n  } ],\n  \"doughs\" : [ {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  }, {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  } ]\n}", CategoryDetails.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default PizzacategoriesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update a pizza Category", nickname = "updatePizzaCategory", notes = "Updates an existing pizza `Category`.", response = CategoryDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "pizzacategories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Successful response.", response = CategoryDetails.class) })
    @RequestMapping(value = "/pizza/categories/{categoryId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<CategoryDetails> updatePizzaCategory(@ApiParam(value = "Updated `Category` information." ,required=true )  @Valid @RequestBody CategoryDetails body
,@ApiParam(value = "A unique identifier for a `Category`.",required=true) @PathVariable("categoryId") Integer categoryId
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"sizes\" : [ {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 7,\n    \"enabled\" : true,\n    \"order\" : 9\n  }, {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 7,\n    \"enabled\" : true,\n    \"order\" : 9\n  } ],\n  \"doughs\" : [ {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  }, {\n    \"extra\" : 5.962133916683182,\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : 1,\n    \"enabled\" : true,\n    \"order\" : 5\n  } ]\n}", CategoryDetails.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default PizzacategoriesApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
