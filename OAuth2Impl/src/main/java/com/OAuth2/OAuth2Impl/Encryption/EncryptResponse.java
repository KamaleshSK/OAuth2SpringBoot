package com.OAuth2.OAuth2Impl.Encryption;

import org.springframework.stereotype.Component;

@Component
public class EncryptResponse {
	
	public String encrypt(String jsonInputString) {
		
		String encryptedResponse = "";
		EncryptionManager manager = new EncryptionManager();
		manager.initFromStrings();
		
		try {
			encryptedResponse = manager.encrypt(jsonInputString);
			System.out.println(jsonInputString);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return encryptedResponse;
	}
	
}
