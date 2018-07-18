package com.userfront.service.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userfront.domain.User;
import com.userfront.service.Dao.UserDao;

@Service
public class UserSecurityService implements UserDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		// using Dao to find username, username come from login
		User user = userDao.findByUsername(username);
		// if not found, then return 
		if (null == user) {
			LOG.warn("Username {} not found", username);
			throw new UsernameNotFoundException("Username "+ username + " not found");
		}
		return user;
	}
	

}
