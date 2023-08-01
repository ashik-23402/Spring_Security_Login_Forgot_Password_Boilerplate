package com.ashik.bk.PublicController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ashik.bk.Entities.User;
import com.ashik.bk.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	UserRepo userRepo;
    	

	@GetMapping("/")
	public String home(Principal principal,HttpServletRequest req, HttpSession session) {
		
		String email = principal.getName();
		User user = userRepo.findByEmail(email);
		
		session.setAttribute("username", user.getName());
		
		
		
		
		
		return "home";
	}

}
