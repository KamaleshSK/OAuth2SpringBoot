package com.OAuth2.OAuth2Impl.Repository;

import org.springframework.data.repository.CrudRepository;

import com.OAuth2.OAuth2Impl.Entity.Users;

public interface UserRepository extends CrudRepository<Users, Integer>{
	
	Users findByUsername(String username);
	
}
