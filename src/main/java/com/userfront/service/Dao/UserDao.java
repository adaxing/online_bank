package com.userfront.service.Dao;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.User;

public interface UserDao extends CrudRepository<User, Long> {
	// spring boot can automatically find based on fingby 
	User findByUsername(String username);
	User findByEmail(String email);
}
