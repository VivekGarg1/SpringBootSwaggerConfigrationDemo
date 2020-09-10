package com.home.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	//Swagger Testing Url's
	//http://localhost:9090/v2/api-docs
	//http://localhost:9090/swagger-ui.html
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Vivek!!!";
	}
}
