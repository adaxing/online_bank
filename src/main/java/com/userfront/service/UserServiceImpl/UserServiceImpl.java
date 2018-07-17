package com.userfront.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfront.domain.User;
import com.userfront.service.UserService;
import com.userfront.service.Dao.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	//based on dependency, when you need something, ask 
	// then spring boot give to you
	@Autowired
	private UserDao userDao;
	
	public void save(User user) {
		userDao.save(user);
	}
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public boolean checkUserExits(String username, String email) {
		if (checkUserExits(username) || checkEmailExits(email)) {
			return true;
		}else {
			return false;
		}
	}

	public boolean checkEmailExits(String email) {
		if (null != findByEmail(email)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkUserExits(String username) {
		if (null != findByUsername(username) ) {
			return true;
		} else {
			return false;
		}
	}
	

}
