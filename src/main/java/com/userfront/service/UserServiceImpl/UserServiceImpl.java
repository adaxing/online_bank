package com.userfront.service.UserServiceImpl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;
import com.userfront.service.UserService;
import com.userfront.service.Dao.RoleDao;
import com.userfront.service.Dao.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	//based on dependency, when you need something, ask 
	// then spring boot give to you
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	public void save(User user) {
		userDao.save(user);
	}
	
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userDao.findByUsername(user.getUsername());
		
		if (localUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());	
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			for (UserRole ur: userRoles) {
				roleDao.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			user.setPrimaryAccount(accountService.createPrimaryAccount());
			user.setSavingsAccount(accountService.createSavingsAccount());
			
			localUser = userDao.save(user);

		}
		return localUser;
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
