package com.userfront.service;

import java.util.Set;

import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;

public interface UserService {
	User findByUsername(String username);
	User findByEmail(String email);
	boolean checkUserExits(String username, String email);
	boolean checkUserExits(String username);
	boolean checkEmailExits(String email);
	
	void save (User user);
	User createUser(User user, Set<UserRole> userRoles);
}
