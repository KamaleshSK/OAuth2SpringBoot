package com.OAuth2.OAuth2Impl.Entity;

public class EncryptedResponseEntity {

	public EncryptedResponseEntity(String string, String string2) {
		RSAEncryptedPublicKey = string2;
		AESEncryptedData = string;
	}

	private String RSAEncryptedPublicKey;
	
	private String AESEncryptedData;

	public String getRSAEncryptedPublicKey() {
		return RSAEncryptedPublicKey;	
	}

	public void setRSAEncryptedPublicKey(String rSAEncryptedPublicKey) {
		RSAEncryptedPublicKey = rSAEncryptedPublicKey;
	}

	public String getAESEncryptedData() {
		return AESEncryptedData;
	}

	public void setAESEncryptedData(String aESEncryptedData) {
		AESEncryptedData = aESEncryptedData;
	}
	
	
	
}
