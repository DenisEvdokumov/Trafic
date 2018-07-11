package tapmoney;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

//import one.FirstStep;

public class Security {
	
	
	public static String encryptRSA(String keyAES, BigInteger keyExp, BigInteger keyMod)
    {
		
		String encryptedKey = "";
		for(int i=0; i < keyAES.length(); i++) {
			char symbol = keyAES.charAt(i);
			
			String symbolEnc = Security.work(symbol, keyExp, keyMod);
			encryptedKey += symbolEnc;
			
			if(i<(keyAES.length()-1)) {
				encryptedKey += "|";
			}
		}
		return encryptedKey;
    }
 
	
	public static String encrypt(String input, String key){
	  byte[] crypted = null;
	  try{
	    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, skey);
	      crypted = cipher.doFinal(input.getBytes());
	    }catch(Exception e){
	    	System.out.println(e.toString());
	    }
	    return new String(Base64.encodeBase64(crypted));
	}
	

	public static String decrypt(String input, String key){
	    byte[] output = null;
	    try{
	      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(Cipher.DECRYPT_MODE, skey);
	      output = cipher.doFinal(Base64.decodeBase64(input));
	    }catch(Exception e){
	      System.out.println(e.toString());
	    }
	    return new String(output);
	}
	
	
	/*public static String work(char character, BigInteger keyExp, BigInteger keyMod) {
		
		int symbNum = (int) character;
		
		BigInteger num = new BigInteger(Integer.toString(symbNum));
		
		String output = "" + num.modPow(keyExp, keyMod);
		
		return output;
	}*/
	
	
	public static String getKey() {
		
		char[] symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		
		StringBuilder b = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < 16; i++) {
			b.append(symbols[r.nextInt(62)]);
		}
		return b.toString();
		
	}
	
	
	
	public static String getString() {
		
		char[] symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		
		StringBuilder b = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			b.append(symbols[r.nextInt(62)]);
		}
		//System.out.println(b.toString());
		return b.toString();
		
	}
	
	
	
	public static Integer getSequence() {
		
		Random r = new Random();
		return r.nextInt(1000);
		
	}
	
	
}