package com.fattazzo.pizzashop.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.repository.UserRepository;

@RestController
public class TestController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "public/v1/test", method = RequestMethod.GET)
	public ResponseEntity<?> getCategories(HttpServletResponse response) {
		return ResponseEntity.ok(userRepository.findByUsernameIgnoreCase("admin"));
	}

}
