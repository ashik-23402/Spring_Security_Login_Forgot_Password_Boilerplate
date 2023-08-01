package com.ashik.bk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashik.bk.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
