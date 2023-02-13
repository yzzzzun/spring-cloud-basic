package com.yzzzzun.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzzzzun.userservice.vo.Greeting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("/")
@RequiredArgsConstructor
public class UserController {
	private final Greeting greeting;

	@GetMapping("/health_check")
	public String status() {
		return "It's Working in User Service.";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return greeting.getMessage();
	}
}
