package com.yzzzzun.userservice.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

	private String email;
	private String name;
	private String pwd;
	private String userId;
	private Date createAt;
	private String encryptedPwd;
}
