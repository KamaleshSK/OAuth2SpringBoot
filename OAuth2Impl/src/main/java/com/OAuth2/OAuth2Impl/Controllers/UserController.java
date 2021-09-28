package com.OAuth2.OAuth2Impl.Controllers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OAuth2.OAuth2Impl.Encryption.DoubleEncryption;
import com.OAuth2.OAuth2Impl.Encryption.EncryptResponse;
import com.OAuth2.OAuth2Impl.Encryption.EncryptionManager;
import com.OAuth2.OAuth2Impl.Entity.EncryptedResponseEntity;
import com.OAuth2.OAuth2Impl.Entity.Users;
import com.OAuth2.OAuth2Impl.Service.UserService;
import com.OAuth2.OAuth2Impl.Utils.JavaObjectToJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EncryptResponse encryptResponse;
	
	@Autowired
	private JavaObjectToJson javaObjectToJsonConverter;
	
	@Autowired 
	private DoubleEncryption doubleEncryptResponse;
	
	//ResponseEntity<List<Users>>
	@GetMapping("/allUsers")
	public ResponseEntity<EncryptedResponseEntity> getAllUsers() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		
		List<Users> users = null;
		
		try {
			users = userService.getAllUsers();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		String json = new String(javaObjectToJsonConverter.convert(users));
		
		EncryptedResponseEntity encryptedResponse = new EncryptedResponseEntity(doubleEncryptResponse.encrpytUsingAES(json), 
				doubleEncryptResponse.encryptUsingRSA());
		
		return new ResponseEntity<EncryptedResponseEntity>(encryptedResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Users> getUsersById(@PathVariable("id") int userId) {
		Users user = null;
		try {
			user = userService.getUserById(userId);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@PostMapping("/addOrUpdate")
	public ResponseEntity<Users> addOrUpdate(@RequestBody Users user) {
		try {
			user = userService.addOrUpdateUser(user);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Users> deleteUserById(@PathVariable("id") int userId) {
		Users user = null;
		try {
			user = userService.deleteUser(userId);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
}
