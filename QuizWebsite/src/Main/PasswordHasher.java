package Main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Simple Password Hashing Tool
 * 
 * @author LADO MELIKIDZE
 */
public class PasswordHasher {
	
	private static final String HASH_ALGORITHM = "SHA";
	private static final String POSSIBLE_SALT_CHARS = "qwertyuioplkjhgfdsazxcvbnm";
	private static final int SALT_LENGTH = 20;
	
	/**
	 *  A teaspoon of salt for your freshly made soup!
	 *	 
	 * @return salt - Literally salt!
	 */
	public String getRandomSalt(){
		Random rand = new Random();
		String salt = "";
		for (int i=0;i<SALT_LENGTH;i++){
			salt += POSSIBLE_SALT_CHARS.charAt(rand.nextInt(POSSIBLE_SALT_CHARS.length()));
		}
		return salt;
	}
	
	/*
	 * Converts a hex code to String
	 * Code Copied From Assignment 4
	 * 
	 * Given a byte[] array, produces a hex String,
	 * such as "234a6f". with 2 chars for each byte in the array.
	 * (provided code)
	 */
	private String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/**
	 * Pretty self explanatory
	 * 
	 * @param password Password as a String
	 * @return Hashed Password
	 */
	public String hashPassword(String password){
		byte[] bytes = password.getBytes();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(HASH_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexToString(md.digest(bytes));
	}
}
