package com.professor.traficinspiration.utils;

import android.util.Log;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.model.messages.EncryptionRequestMessage;
import com.professor.traficinspiration.model.messages.EncryptionRequestMessage2;
import com.professor.traficinspiration.model.messages.EncryptionResponseMessage;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
//import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

//import javax.crypto.spec.SecretKeySpec;

//import javax.crypto.KeyGenerator;
//import java.security.SecureRandom;

public class FirstStep2 {


	private static Base64 ed = new Base64 ();





	private static final String ACTION_CONFIRM = "confirm";
	private static SecretKeySpec secretKey;
	private static byte[] key;
    
	public static EncryptionRequestMessage2 genetateEncryptionRequestMessage (EncryptionResponseMessage encryptionResponseMessage) {
		
		//Сюда нужно подставить параметр из ответа - string
		String string = encryptionResponseMessage.getString();
		//Сюда нужно подставить параметр из ответа - stringMAC
		String string_MAC = encryptionResponseMessage.getString_MAC();

		//Сюда нужно подставить параметр из ответа - idSession
		String idSession = encryptionResponseMessage.getIdSession().toString();
		//Сюда нужно подставить параметр из ответа - idSessionMAC
		String idSessionMAC = encryptionResponseMessage.getIdSession_MAC();
		//Сюда нужно подставить параметр из ответа - KeyMAC
		String keyMAC = encryptionResponseMessage.getKeyMAC();
		//Сюда нужно подставить параметр из ответа - KeyMAC_MAC
		String keyMAC_MAC = encryptionResponseMessage.getKeyMAC_MAC();
		
		
		//Сюда нужно подставить keyAES сгенерированый при запросе из файла FirstStep
		String keyAESold = ApplicationContext.getKeyAES();
		//Сюда нужно подставить keyMAC сгенерированый при запросе из файла FirstStep
		String keyMACold = ApplicationContext.getKeyMAC();

		Log.i("1",string_MAC+ " " + keyMAC);
		String string_form_MAC = FirstStep2.decrypt(string_MAC, keyMACold);
		//Проверка равен ли string к stringMACdecoded

		if(string.equals(string_form_MAC)) {
			Log.i("1","string == stringMACdecoded");
		}else {
			return null;
		}
		
		String idSession_from_MAC = FirstStep2.decrypt(idSessionMAC, keyMACold);
		//Проверка равен ли string к idSessionDecoded
		if(idSession.equals(idSession_from_MAC )) {
			Log.i("1","idSession == idSessionDecoded");
		}else {
			return null;
		}
		
		String KeyMAC_from_MAC = FirstStep2.decrypt(keyMAC_MAC, keyMACold);
		//Проверка равен ли KeyMAC к KeyMAC_MACdecoded
		if(keyMAC.equals(KeyMAC_from_MAC)) {
			Log.i("1","KeyMAC == KeyMAC_MACdecoded");
		}else {
			return null;
		}
		
		
		//String keyAES = FirstStep2.getKey();
		String keyMAC_real = FirstStep2.decrypt(keyMAC, keyAESold);
		Log.i("1","keyMAC_real " + keyMAC_real);
		

		String newString = FirstStep2.getString();
		String newStringMAC = FirstStep2.encrypt(newString, keyMAC_real);

		//sequence должен быть на единицу больше чем был сгенерированый
		//при первом запросе в файле FirstStep
		String sequence = ApplicationContext.getSequence();


		//Шифрует sequence
		String sequenceAES = FirstStep2.encrypt(sequence, keyAESold);
		//Подписывает sequenceAES
		String sequenceMAC = FirstStep2.encrypt(sequenceAES, keyMAC_real);


		String idSessionNewMAC = FirstStep2.encrypt(idSession, keyMAC_real);
		
		//BigInteger keyMod = new BigInteger("4128129803496668451833756170118141868851614705806314994203614389510613404539543352564468242326067417323817062813109724033546102916918446969287233378526867");
		//BigInteger keyExp = new BigInteger("3127925502487553883617082248057751761281245073620963837458066168905990700262438767821460201762431802142515638993587131725483191226686428040565351502696063");
		
		
		//String encryptedKeyAES = FirstStep2.encryptRSA(keyAES, keyExp, keyMod);
		//String encryptedKeyMAC = FirstStep2.encryptRSA(keyMAC, keyExp, keyMod); 
		
		//System.out.println("keyAES - "+keyAES);
		//System.out.println("keyMAC - "+keyMAC);
		
//		Log.i("1","\n----------- Параметры для второго запроса - для подтверждения ключей ----------\n");
//
//		Log.i("1","stringMAC - "+ newStringMAC);
//		Log.i("1","sequence - "+sequenceAES);
//		Log.i("1","idSessionMAC - "+idSessionNewMAC);
//		Log.i("1","string - "+newString);
//		Log.i("1","keysConfirm - "+1);
//		Log.i("1","sequence_MAC - "+sequenceMAC);
//		Log.i("1","action - "+ACTION_CONFIRM);
//		Log.i("1","idSession -" + idSession);

		EncryptionRequestMessage2 encryptionRequestMessage2 = new EncryptionRequestMessage2();

		encryptionRequestMessage2.setStringMAC(newStringMAC);
		encryptionRequestMessage2.setSequence(sequenceAES);
		encryptionRequestMessage2.setIdSessionMAC(idSessionNewMAC);
		encryptionRequestMessage2.setString(newString);
		encryptionRequestMessage2.setKeysConfirm(1);
		encryptionRequestMessage2.setSequenceMAC(sequenceMAC);
		encryptionRequestMessage2.setAction(ACTION_CONFIRM);
		encryptionRequestMessage2.setIdSession(idSession);

		ApplicationContext.setIdSession(idSession);
		ApplicationContext.setKeyMAC(keyMAC_real);

		return encryptionRequestMessage2;
		
	}
	
	
	public static String encryptRSA(String keyAES, BigInteger keyExp, BigInteger keyMod)
    {
		
		String encryptedKey = "";
		for(int i=0; i < keyAES.length(); i++) {
			char symbol = keyAES.charAt(i);
			
			String symbolEnc = FirstStep2.work(symbol, keyExp, keyMod);
			encryptedKey += symbolEnc;
			
			if(i<(keyAES.length()-1)) {
				encryptedKey += "|";
			}
		}
		return encryptedKey;
    }
	
	
	public static void setKey(String myKey)
    {
        MessageDigest sha = null; 
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
//			String decoded = new String (ed.decode (input.getBytes ()));
			output = cipher.doFinal(ed.decode (input.getBytes ()));
//			output = cipher.doFinal(Base64.decodeBase64(input));
		}catch(Exception e){
			Log.i("1",e.getMessage());
		}
		return new String(output);
	}
//	private static byte[] decodeBase64(String dataToDecode)
//	{
//		byte[] dataDecoded = Base64.decode(dataToDecode, Base64.d);
//		return dataDecoded;
//	}
	
	public static String work(char character, BigInteger keyExp, BigInteger keyMod) {
		
		int symbNum = (int) character;
		
		BigInteger num = new BigInteger(Integer.toString(symbNum));
		
		String output = "" + num.modPow(keyExp, keyMod);
		
		return output;
	}
	
	
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