package com.ashik.bk.services;

import java.util.List;

import com.ashik.bk.Dtos.UserDto;
import com.ashik.bk.Entities.User;

public interface UserService {
	
	void saveUser(UserDto userdto);
	
	User findUserByEmail(String email);
	
	List<UserDto> findAllUser();
	
	User UpdatePassword(String email, String password);

}
