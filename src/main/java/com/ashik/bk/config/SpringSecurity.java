package com.ashik.bk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurity {
	
	@Bean
	public PasswordEncoder passwordencoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getDetailservice() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthencateprovider() {
		
		DaoAuthenticationProvider daoauth = new DaoAuthenticationProvider();
		daoauth.setUserDetailsService(getDetailservice());
		daoauth.setPasswordEncoder(passwordencoder());
		
		return daoauth;
	}
	
	
	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {
		
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(authorize->
		authorize.requestMatchers("/public/**").permitAll()
		.anyRequest().authenticated())
		.formLogin(formLogin -> formLogin
				.loginPage("/public/login").defaultSuccessUrl("/").failureUrl("/public/login-error")
				.permitAll())
		.logout(logout-> logout.logoutSuccessUrl("/public/"));
		
		
		return http.build();
		
	}
	
	

}
