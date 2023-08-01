package com.ashik.bk.PublicController;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashik.bk.Dtos.UserDto;
import com.ashik.bk.Entities.User;
import com.ashik.bk.repository.UserRepo;
import com.ashik.bk.services.EmailDetails;
import com.ashik.bk.services.EmailService;
import com.ashik.bk.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/public")
public class Pcontroller {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userServices;
	
	@Autowired
	private EmailService emailService;
	
	

	
	
	
	@Autowired
	private UserService userServiice;
	
	
	public void AutoLogin(HttpServletRequest req, String  username , String password) {
		
		try {
			req.login(username, password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	

	
	
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	

	
	
	
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}
	
	
	@GetMapping("/success")
	public String sucess() {
		
		return "success";
	}
	
	
	
	
	@PostMapping("/save")
	public String SaveUser(HttpServletRequest req ,@ModelAttribute UserDto userDto, HttpSession session,Model m) {
	
		
		String email = userDto.getEmail();
		
		User user = userRepo.findByEmail(email);
		
		
		if(user != null ) {
			
			m.addAttribute("error", true);
			System.out.println("you have account alrady");
			return "register";
			
		}
		else {
			
			this.userServiice.saveUser(userDto);
			
//			System.out.println(userDto);
			
			
			
			AutoLogin(req, userDto.getEmail(), userDto.getPassword());
			
			session.setAttribute("username", userDto.getFirstName());
		
			return "home";
			
		}
		
		
		
		
		
		
		
	}
	
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	
	@GetMapping("/login-error")
	public String loginEror(Model model) {
		
		model.addAttribute("loginError", true);
		
		return "login";
		
	}
	
	
	
	
	
	@GetMapping("/updatepass1")
	public String updatepass(Model model) {
		
		model.addAttribute("upError", true);
		
		
		return "updatePassword.html";
		
	}
	
	
	
	
	
	@GetMapping("/updatepass2")
	public String updatepassword() {
		
		
		
		
		return "updatePassword.html";
		
	}
	
	
	
	
	
	
	@GetMapping("/forgotPassword")
	public String ForgotPassword() {
		
		return "forgotPassword";
	}
	
	
	
	
	
	@PostMapping("/forgotPassword")
	public String SendingOtp(HttpServletRequest req,HttpSession  session,Model m) {
		
		String email = req.getParameter("username");
//		System.out.println(email);
		
		//validate user
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			
		    session.setAttribute("sendError", email);
			
			return "forgotPassword";
			
		}
		
		// generate otp
		
		Random random = new Random();
		int otp = random.nextInt(8999)+1000;
		
		System.out.println(otp);
		session.setAttribute("email", email);
		
		
		
		//send email
		
		EmailDetails emaildetails = new EmailDetails();
		
		emaildetails.setFrom("techAshik");
		emaildetails.setTo(email);
		emaildetails.setSubject("Otp from techashik");
		String mssg = "<h1> "+ otp + "</h1>";
		emaildetails.setMssgContent(mssg);
		
		emailService.SendEmail(emaildetails);
		
		
		
		session.setAttribute("otp", otp);
		
		return "OtpValidate";
	}
	
	
	
	
	
	
	@PostMapping("/vaildateOtp")
	public String ValidateOtp(HttpServletRequest req,HttpSession session) {
		
		String otpstr = req.getParameter("otp");
		int otp = Integer.valueOf(otpstr);
		
		
		System.out.println(otp);
		
		
		
		String email =(String)session.getAttribute("email");
		int otpold = (int) session.getAttribute("otp");
		
		System.out.println(email);
		System.out.println(otpold);
		
		
		if(otp != otpold) {
			//error
			session.setAttribute("otpError", true);
			return "OtpValidate";
		}
		
		
		
		
		
		
		return "updatePassword";
	}
	
	
	
	
	
	
	@PostMapping("/updatePass")
	public String changepass(HttpServletRequest req, HttpSession session) {
		
		String newpass1 = req.getParameter("pass1");
		String newpass2 = req.getParameter("pass2");
		
		System.out.println(newpass1);
		System.out.println(newpass1);
		
		if(newpass1.equals(newpass2)) {
			
			System.out.println("love");
		
		String email =(String)session.getAttribute("email");
		
		User user = userRepo.findByEmail(email);
		
		System.out.println(user);
		
		//update 
		
		User updatePassword = userServices.UpdatePassword(email, newpass1);
		System.out.println(updatePassword);
		
		
		}
		
		else {
			
			
			return "redirect:/public/updatepass1";
		}
		
		
		
		return "login";
		
	}
	
	
	
	

	

}
