/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.fattazzo.pizzashop.controller.api;

import com.fattazzo.pizzashop.model.api.Dashboard;
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
@Api(value = "Dashboard", description = "the Dashboard API")
public interface DashboardApi {

    @ApiOperation(value = "Get a Dashboard", nickname = "getDashboard", notes = "Gets a `Dashboard`.", response = Dashboard.class, authorizations = {
        @Authorization(value = "BearerAuth")    }, tags={ "dashboard", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - return the `Dashboard` entity.", response = Dashboard.class) })
    @RequestMapping(value = "/dashboard",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Dashboard> getDashboard();

}
