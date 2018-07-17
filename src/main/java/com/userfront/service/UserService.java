package com.userfront.service;

import com.userfront.domain.User;

public interface UserService {
	User findByUsername(String username);
	User findByEmail(String email);
	boolean checkUserExits(String username, String email);
	boolean checkUserExits(String username);
	boolean checkEmailExits(String email);
	
	void save (User user);
}
