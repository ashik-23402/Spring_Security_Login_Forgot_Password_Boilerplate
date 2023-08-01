package com.ashik.bk.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ashik.bk.Entities.Role;
import com.ashik.bk.Entities.User;


public class CustomUser implements UserDetails{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	

	public CustomUser(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		// TODO Auto-generated method 
		
		List<SimpleGrantedAuthority>rlist = new ArrayList<>();
		
		for(int i = 0;i<user.getRoles().size();i++) {
			
			Role role = user.getRoles().get(i);
			
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.getName());
			rlist.add(auth);
			
		}
		

				return rlist;
				
				
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
