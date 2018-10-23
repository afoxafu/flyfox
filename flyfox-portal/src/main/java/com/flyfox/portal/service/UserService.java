package com.flyfox.portal.service;

import com.flyfox.pojo.User;

public interface UserService {

	User getUserByToken(String token);
}
