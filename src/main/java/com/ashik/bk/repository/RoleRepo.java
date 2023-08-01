package com.ashik.bk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashik.bk.Entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	
	Role findByName(String name);

}
