package com.yzzzzun.userservice.domain.dto;

import java.util.Date;
import java.util.List;

import com.yzzzzun.userservice.domain.vo.ResponseOrder;

import lombok.Data;

@Data
public class UserDto {

	private String email;
	private String name;
	private String pwd;
	private String userId;
	private Date createAt;
	private String encryptedPwd;

	private List<ResponseOrder> orders;
}
