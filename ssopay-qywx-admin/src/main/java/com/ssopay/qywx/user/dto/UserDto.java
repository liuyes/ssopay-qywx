package com.ssopay.qywx.user.dto;

import com.ssopay.qywx.base.BaseDto;
import com.ssopay.qywx.user.bean.User;

public class UserDto extends BaseDto<User> {
	private String username;//登录名
	private String password;//密码
	private String name;//真实姓名
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
