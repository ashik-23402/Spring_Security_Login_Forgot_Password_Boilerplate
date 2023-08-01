package com.ashik.bk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashik.bk.Entities.User;
import com.ashik.bk.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		User user = userRepo.findByEmail(email);
//		System.out.println(user);
		
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
			
		}
		else {
			
			CustomUser cuser = new CustomUser(user);
			
//			System.out.println(cuser);
			
			return cuser;
			
		}
		
		
		
		
		
	}

}
