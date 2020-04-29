package com.fattazzo.pizzashop.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.DashboardApi;
import com.fattazzo.pizzashop.model.api.Dashboard;
import com.fattazzo.pizzashop.service.dashboard.DashboardService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "dashboard" })
public class DashboardController implements DashboardApi {

	@Autowired
	private DashboardService dashboardService;

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'WEB_ADMIN'})")
	public ResponseEntity<Dashboard> getDashboard() {
		return ResponseEntity.ok(dashboardService.load());
	}

}
