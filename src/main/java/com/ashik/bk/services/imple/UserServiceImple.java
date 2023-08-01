package com.ashik.bk.services.imple;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashik.bk.Dtos.UserDto;
import com.ashik.bk.Entities.Role;
import com.ashik.bk.Entities.User;
import com.ashik.bk.repository.UserRepo;
import com.ashik.bk.services.UserService;

@Service
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	
	
	
	

	@Override
	public void saveUser(UserDto userdto) {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setName(userdto.getFirstName() + " "+ userdto.getLastName());
		user.setEmail(userdto.getEmail());
		user.setPassword(passwordencoder.encode(userdto.getPassword()));
		
		
		
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		
	
		user.getRoles().add(role);
		
		userRepo.save(user);
		
		
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		
		return userRepo.findByEmail(email);
	}

	@Override
	public List<UserDto> findAllUser() {
		// TODO Auto-generated method stub
		
		List<User> alluser = userRepo.findAll();
		
		List<UserDto> alluserdto = alluser.stream().map((user)-> mapTouserDto(user)).collect(Collectors.toList());
		
		
		
		return alluserdto;
	}
	
	
	private UserDto mapTouserDto(User user) {
		
		UserDto userDto = new UserDto();
		String [] str = user.getName().split(" ");
		userDto.setFirstName(str[0]);
		userDto.setLastName(str[1]);
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		
		return userDto;
		
	}

	@Override
	public User UpdatePassword(String email, String password) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByEmail(email);
		user.setPassword(passwordencoder.encode(password));
		
		userRepo.save(user);
		
		
		return user;
	}
	
	
	
	
	

}
