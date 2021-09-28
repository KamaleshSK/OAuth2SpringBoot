package com.OAuth2.OAuth2Impl.Encryption;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

@Component
public class DoubleEncryption {
	
	private SecretKey aesPublicKey;
	
	private PublicKey publicKey;
	
	private static final String PUBLIC_KEY_STRING =  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYQYM3FwZuQNLK7xRbuBbAcviy1vl9LB//Ubz+NFSBjgrrGNPwqgOuWF1qskBOY0AnoAZpwHlWEryvtz1OGNX5q9boqrhOrGQebJfek9JGvjysz3+KCqAIup8C1Enp4+cUxYy7BRorjF6wqTAjyJn/SQVZPPUyVeN17Nw0jSqI9QIDAQAB";

	public String encrpytUsingAES(String inputJsonString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(128); // The AES key size in number of bits
		aesPublicKey = generator.generateKey();
		
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, aesPublicKey);
		byte[] byteCipherText = aesCipher.doFinal(inputJsonString.getBytes());
		
		return encode(byteCipherText);
	}
	
	public String encryptUsingRSA() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		
		X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY_STRING));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpecPublic);

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.PUBLIC_KEY, publicKey);
		byte[] encryptedKey = cipher.doFinal(this.aesPublicKey.getEncoded());
		
		return encode(encryptedKey);
	}
	
	private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

	private static String encode(byte[] data) {
	    return Base64.getEncoder().encodeToString(data);
	}
	
	public SecretKey getAesPublicKey() {
		return aesPublicKey;
	}

	public void setAesPublicKey(SecretKey aesPublicKey) {
		this.aesPublicKey = aesPublicKey;
	}
	
}
