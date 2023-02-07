package com.yzzzzun.firstservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
public class FirstServiceController {

	@GetMapping("/welcome")
	public String welcome(){
		return "Welcome to the First service.";
	}

	@GetMapping("/message")
	public String message(@RequestHeader("first-request") String header){
		return header;
	}
}
