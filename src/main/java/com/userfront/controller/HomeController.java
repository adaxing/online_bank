package com.userfront.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;
import com.userfront.service.UserService;
import com.userfront.service.Dao.RoleDao;

@Controller
public class HomeController {
	@Autowired 
	private UserService userService;
	
	// better to use role service, which connect to roledao
	@Autowired
	private RoleDao roleDao; 
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	// web-browser always use get method
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user =new User();
		
		model.addAttribute("user",user);
		
		return "signup";
				
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model) {
		if (userService.checkUserExits(user.getUsername(), user.getEmail())) {
			if (userService.checkEmailExits(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			if (userService.checkUserExits(user.getUsername())) {
				model.addAttribute("usernameExists",true);
			}
			return "signup";
		} else {
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
			//userService.createUser(user, userRoles);
			userService.createUser(user,userRoles);
			
			return "redirect:/";
		}
	}
	
	//principal is the person who has logged in
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();
		
		model.addAttribute("primaryAccount", primaryAccount);
		model.addAttribute("savingsAccount", savingsAccount);
		
		return "userFront";

	}
	
	

}
