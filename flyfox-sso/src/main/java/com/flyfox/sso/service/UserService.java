package com.flyfox.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.pojo.User;

public interface UserService {

	FlyfoxResult checkData(String content, Integer type);
	FlyfoxResult createUser(User user);
	FlyfoxResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	FlyfoxResult getUserByToken(String token);
}
