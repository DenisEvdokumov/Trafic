package tapmoney;

import java.math.BigInteger;
//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KeyexResp {
	
	//static String keyAES = "gdiQme6DWaA4kDJT";
	//static String keyMAC = "ITq5I16FOKQEaDX6";
	
	public static void main(String[] args) {
		
		//String respSetKeys = "";
		//String respSetKeys = "";
		//String respSetKeys = "";
		//String respSetKeys = "";
		//String respSetKeys = "";
		//String respSetKeys = "";
		//String respSetKeys = "{\"string\":\"BrDlxVmTanJDo4t6UgyaJzNMCmpx7Fd351ux6-UaIIplvmlOfz\",\"string_MAC\":\"\",\"idSession\":105,\"idSession_MAC\":\"\",\"KeyMAC\":\"6ThzliEtOJcnNz2nI5htmA17SASz/EFZD6IavNPck+k=\",\"KeyMAC_MAC\":\"\"}";
		//KeyexResp.setKeys(respSetKeys);
		
		//String respConfirm = "";
		//String respConfirm = "";
		//String respConfirm = "";
		//String respConfirm = "";
		String respConfirm = "{\"string\":\"mKgWvLrwR-8OGhCHbSDUNWsIvaifZOnU3b7uy5dvqg17-KZl1f\",\"string_MAC\":\"PZQKHNRTlrZxv6Yxo7Jed5wRSguaufqo5xRkafhPtHUYypAGWY/HVSyy0XLikrnaiO/3hxCFQbScuDIK9yp/aA==\",\"idSession\":151,\"idSession_MAC\":\"vXPqT6U7qJmLwoKcByRNSg==\",\"KeyMAC\":\"4HEapI9XxyGUeG7x8OVd5o0U/wUv6cstWiUbPQCIZOU=\",\"KeyMAC_MAC\":\"RCa/YGu84nQem46QDFM7sOzDHxTr/LRHoY/qstje/aTVWcvryrIh1ND9Cg187HTa\"}";
		//String respConfirm = "{\"keysConfirm\":1,\"KeyMAC\":\"Kih7M5R6xnzCydPWBKyWQQ17SASz/EFZD6IavNPck+k=\",\"KeyMAC_MAC\":\"ejUDPmNBgDP8cdc+M6S72JPm/jIT7XJ0vCjH+r7ZTBlSNkdKUFeIPjdz+8pPtW5q\"}";
		//String respConfirm = "{\"keysConfirm\":1,\"KeyMAC\":\"DK7Y9LgYKwfpF4i7vXx2x7tuY2wcV2j6xrPPME3yZCw=\",\"KeyMAC_MAC\":\"kDUx7WR7SNHFUmfgfj/qvY5cGS3TFBxxPuzeV9AEDLmX3t064+DhRM1smB0j5Bk+\"}";
		KeyexResp.setKeys(respConfirm);
		
	}
	
	public static void setKeys(String resp) {
		
		String keyAES = "xWgymGKhPfkiLM5x";
		String keyMAC = "CdzmQv7kUeNnkjuo";
		//String keyAES = "";
		//String keyMAC = "";
		
		JSONParser parser = new JSONParser();
		//String resp = "{\"string\":\"JYfQkmu4RnzuhhxkuxPrgqEEHeZv_7oK9cFx-RdO8KLxmRvafB\",\"stringMAC\":\"2J1JksoLQGfiu3ywaloDjHtHEaC7izhcyrCoprDKADlwJY1dyy5SxFoMikRqzWIUFmCa9n/FaAZRVj/iv7Iaqw==\",\"idSession\":94,\"idSessionMAC\":\"ASmcGq1J3Zo6a+Nc37XOGQ==\",\"KeyMAC\":\"KqxW/ZmQWs2mH2t0SRp86+5uueUPgrJzzb8QDBzpWZY=\",\"KeyMAC_MAC\":\"NW488xiIP5t3jJ07Gns09migAgT8fPMsQT5+Gn0S+p8Ls0eHjLfUVZ2jTMc7cGFz\"}";
		//String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
		
		
		JSONObject jsonObject = (JSONObject) JSONValue.parse(resp);
		
		
		//jsonObject.get("KeyMAC");
		//System.out.println(jsonObject.get("KeyMAC"));
		//JSONObject myjson = new JSONObject(response);
		
		String string = jsonObject.get("string").toString();
		String stringMAC = jsonObject.get("string_MAC").toString();
		String idSession = jsonObject.get("idSession").toString();
		String idSessionMAC = jsonObject.get("idSession_MAC").toString();
		String KeyMAC = jsonObject.get("KeyMAC").toString();
		String KeyMAC_MAC = jsonObject.get("KeyMAC_MAC").toString();
		
		String string_from_MAC = Security.decrypt(stringMAC, keyMAC);
		if(string.equals(string_from_MAC)) {
			System.out.println("string = true");
		}else {
			System.out.println("string = false");
		}
		
		String idSession_from_MAC = Security.decrypt(idSessionMAC, keyMAC);
		if(idSession.equals(idSession_from_MAC)) {
			System.out.println("idSession = true");
		}else {
			System.out.println("idSession = false");
		}
		
		String KeyMAC_from_MAC = Security.decrypt(KeyMAC_MAC, keyMAC);
		if(KeyMAC.equals(KeyMAC_from_MAC)) {
			System.out.println("KeyMAC = true");
		}else {
			System.out.println("KeyMAC = false");
		}
		
		String KeyMAC_real = Security.decrypt(KeyMAC, keyAES);
		//String sum_AES = Security.encrypt(sum, keyAES);
		//String sum_AES_MAC = Security.encrypt(sum_AES, keyMAC);
		
		//System.out.println(string);
		
		//System.out.println("sequence - "+sequence);
		//System.out.println("keyAES - "+keyAES);
		//System.out.println("keyMAC - "+keyMAC);
		
		System.out.println("\n----------- Ответ ----------\n");
		
		System.out.println("idSession - "+idSession);
		System.out.println("KeyMAC - "+KeyMAC_real);
		//System.out.println("encryptedKeyAES - "+encryptedKeyAES);
		//System.out.println("encryptedKeyMAC - "+encryptedKeyMAC);
		
	}
	
	public static void confirm(String resp) {
		
		String keyAES = "bLjjECyVP021BgKB";
		String keyMAC = "SmVYpKHM7HdXMPIg";
		//String keyAES = "";
		//String keyMAC = "";
		
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObject = (JSONObject) JSONValue.parse(resp);
		
		String keysConfirm = jsonObject.get("keysConfirm").toString();
		String KeyMAC = jsonObject.get("KeyMAC").toString();
		String KeyMAC_MAC = jsonObject.get("KeyMAC_MAC").toString();
		
		String KeyMAC_from_MAC = Security.decrypt(KeyMAC_MAC, keyMAC);
		if(KeyMAC.equals(KeyMAC_from_MAC)) {
			System.out.println("KeyMAC = true");
		}else {
			System.out.println("KeyMAC = false");
		}
		
		
		String KeyMAC_real = Security.decrypt(KeyMAC, keyAES);
		
		System.out.println("\n----------- Ответ ----------\n");
		
		//System.out.println("idSession - "+idSession);
		System.out.println("KeyMAC - "+KeyMAC_real);
		
	}
	
}