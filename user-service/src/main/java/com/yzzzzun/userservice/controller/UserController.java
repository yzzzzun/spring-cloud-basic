package com.yzzzzun.userservice.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yzzzzun.userservice.domain.dto.UserDto;
import com.yzzzzun.userservice.domain.vo.Greeting;
import com.yzzzzun.userservice.domain.vo.RequestUser;
import com.yzzzzun.userservice.domain.vo.ResponseUser;
import com.yzzzzun.userservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("/")
@RequiredArgsConstructor
public class UserController {
	private final Greeting greeting;

	private final UserService userService;

	@GetMapping("/health_check")
	public String status() {
		return "It's Working in User Service.";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return greeting.getMessage();
	}

	@PostMapping("/users")
	public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = mapper.map(user, UserDto.class);
		ResponseUser responseUser = mapper.map(userService.createUser(userDto), ResponseUser.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
	}
}
