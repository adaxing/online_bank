package com.userfront.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private static final String SALT = "salt";
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
	
	private static final String[] PUBLIC_MATCHERS = {
			"/webjars/**",
			"/css/**",
			"/images/**",
			"/",
			"/about/**",
			"/contact/**",
			"/error/**/*",
			"/console/**",
			"/signup"
	};
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().
			antMatchers(PUBLIC_MATCHERS).
			permitAll().anyRequest().authenticated();
		http 
			.csrf().disable().cors().disable()
			.formLogin().failureUrl("/index?error").defaultSuccessUrl(defaultSuccessUrl)
	}
	

}
