package com.ashik.bk.PublicController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class PrivateController {
	
	@GetMapping("/prod")
	public String register() {
		
		return "prod";
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin1")
	public String admin1() {
		
		return "admin1";
	}

}
