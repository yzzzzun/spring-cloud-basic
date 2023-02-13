package com.yzzzzun.firstservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/first-service")
@RequiredArgsConstructor
public class FirstServiceController {

	private final Environment env;

	@GetMapping("/welcome")
	public String welcome(){
		return "Welcome to the First service.";
	}

	@GetMapping("/message")
	public String message(@RequestHeader("first-request") String header){
		return header;
	}

	@GetMapping("/check")
	public String check(HttpServletRequest request){

		log.info("Server port={}", request.getServerPort());

		return String.format("Hi, first service PORT %s", env.getProperty("local.server.port"));

	}
}
