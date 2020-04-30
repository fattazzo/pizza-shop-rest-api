/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.Product;
import com.fattazzo.pizzashop.model.api.ProductDetails;
import org.springframework.core.io.Resource;
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
@Api(value = "Products", description = "the Products API")
public interface ProductsApi {

    @ApiOperation(value = "Create a Product", nickname = "createProduct", notes = "Creates a new instance of a `Product`.", response = ProductDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response.", response = ProductDetails.class) })
    @RequestMapping(value = "/products",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ProductDetails> createProduct(@ApiParam(value = "A new `Product` to be created." ,required=true )   @RequestBody ProductDetails body
);


    @ApiOperation(value = "Delete a Product", nickname = "deleteProduct", notes = "Deletes an existing `Product`.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/products/{productId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProduct(@ApiParam(value = "A unique identifier for a `Product`.",required=true) @PathVariable("productId") Integer productId
);


    @ApiOperation(value = "Delete a Product image", nickname = "deleteProductImage", notes = "Deletes an existing `Product` image.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response.") })
    @RequestMapping(value = "/products/{productId}/image",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProductImage(@ApiParam(value = "A unique identifier for a `Product`.",required=true) @PathVariable("productId") Integer productId
);


    @ApiOperation(value = "Get a Product", nickname = "getProduct", notes = "Gets the details of a single instance of a `Product`.", response = ProductDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns a single `Product`.", response = ProductDetails.class) })
    @RequestMapping(value = "/products/{productId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ProductDetails> getProduct(@ApiParam(value = "A unique identifier for a `Product`.",required=true) @PathVariable("productId") Integer productId
);


    @ApiOperation(value = "Get a Product image", nickname = "getProductImage", notes = "Gets a `Product` image.", response = Resource.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "`Product` image", response = Resource.class),
        @ApiResponse(code = 204, message = "`Product` image not present") })
    @RequestMapping(value = "/products/{productId}/image",
        produces = { "image/png" }, 
        method = RequestMethod.GET)
    ResponseEntity<Resource> getProductImage(@ApiParam(value = "A unique identifier for a `Product`.",required=true) @PathVariable("productId") Integer productId
);


    @ApiOperation(value = "List All products", nickname = "getProducts", notes = "Gets a list of all `Product` entities.", response = Product.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `Product` entities.", response = Product.class, responseContainer = "List") })
    @RequestMapping(value = "/products",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Product>> getProducts(@ApiParam(value = "If true, the list of all entities include enabled and disabled `Product`" ) @RequestHeader(value="includeDisabled", required=false) Boolean includeDisabled
);


    @ApiOperation(value = "Update a Product", nickname = "updateProduct", notes = "Updates an existing `Product`.", response = ProductDetails.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Successful response.", response = ProductDetails.class) })
    @RequestMapping(value = "/products/{productId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ProductDetails> updateProduct(@ApiParam(value = "Updated `Product` information." ,required=true )   @RequestBody ProductDetails body
,@ApiParam(value = "A unique identifier for a `Product`.",required=true) @PathVariable("productId") Integer productId
);


    @ApiOperation(value = "Update a Product image", nickname = "updateProductImage", notes = "Updates an existing `Product` image.", authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful response") })
    @RequestMapping(value = "/products/{productId}/image",
        consumes = { "multipart/form-data" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateProductImage(@ApiParam(value = "file detail")  @RequestPart("file") MultipartFile file
,@ApiParam(value = "A unique identifier for a `Product`.",required=true) @PathVariable("productId") Integer productId
);

}