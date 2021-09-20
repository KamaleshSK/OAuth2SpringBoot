package com.OAuth2.OAuth2Impl.Service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.OAuth2.OAuth2Impl.Entity.Users;

@Repository
public interface UserService {

	public List<Users> getAllUsers();
	
	public Users getUserById(int userId);
	
	public Users addOrUpdateUser(Users user);
	
	public Users deleteUser(int userId) throws Exception;
}
