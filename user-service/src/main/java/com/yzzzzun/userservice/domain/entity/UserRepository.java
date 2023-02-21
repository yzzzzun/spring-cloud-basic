package com.yzzzzun.userservice.domain.entity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByUserId(String userId);

	UserEntity findByEmail(String username);
}
