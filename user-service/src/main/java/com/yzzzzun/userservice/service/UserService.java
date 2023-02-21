package com.yzzzzun.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yzzzzun.userservice.domain.dto.UserDto;
import com.yzzzzun.userservice.domain.entity.UserEntity;
import com.yzzzzun.userservice.domain.entity.UserRepository;
import com.yzzzzun.userservice.domain.vo.ResponseOrder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder;

	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);
		userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

		userRepository.save(userEntity);

		return mapper.map(userEntity, UserDto.class);
	}

	public Iterable<UserEntity> getUserByAll() {
		return userRepository.findAll();
	}

	public UserDto getUserByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException("User not found");

		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		List<ResponseOrder> ordersList = new ArrayList<>();
		userDto.setOrders(ordersList);
		return userDto;
	}
}
