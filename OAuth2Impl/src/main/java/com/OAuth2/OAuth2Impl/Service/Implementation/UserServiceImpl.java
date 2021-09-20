package com.OAuth2.OAuth2Impl.Service.Implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.OAuth2.OAuth2Impl.Entity.Users;
import com.OAuth2.OAuth2Impl.Repository.UserRepository;
import com.OAuth2.OAuth2Impl.Service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Users> getAllUsers() {
		return (List<Users>) userRepository.findAll();
	}

	@Override
	public Users getUserById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public Users addOrUpdateUser(Users user) {
		return userRepository.save(user);
	}

	@Override
	public Users deleteUser(int userId) throws Exception {
		Users deletedUser = null;
		try {
			deletedUser = userRepository.findById(userId).orElse(null);
			if (deletedUser == null) {
				throw new Exception("user not available");
			} else {
				userRepository.deleteById(userId);
			}
		} catch(Exception ex) {
			throw ex;
		}
		return deletedUser;
	}
	
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(userId);
		if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getUserId()), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
