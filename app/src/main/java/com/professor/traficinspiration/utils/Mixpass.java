package com.professor.traficinspiration.utils;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class Mixpass {
	
	public static void main(String[] args) {
		
		Mixpass mixpass = new Mixpass();
		String mixedPassword = mixpass.mix("777thePaswordForTesting777", 2);
		System.out.println(mixedPassword);
		
	}


	public String mix(String password, int n) {
		
		
		//Initial variables pass, number
		//pass - a password which has typed user
		//number - casual number, which has generated at server and client sides
		String pass = password;
		int number = n;


		
		//Array, will contain user password
		//one item of array will represent one character of user password
		ArrayList passArray;
		passArray = new ArrayList();
		
		//retrieving every character from string 'pass' and adding to 'passArray'
		for(int i = 0; i < pass.length(); i++) {
			passArray.add(pass.charAt(i));
		}
		
		//newString - will contain the end result of mixed user password 
		String newString = "";

		
		//checkArray - will contain indexes of passArray which we will delete after
		//program has go through all string at first time and every next times
		ArrayList<Integer> checkArray;
		checkArray = new ArrayList();
		
		// k - initially will contain a number(casual number)
		// than every time when program will get new index of passArray (which will then delete him),
		// k increases on number(k += number) 	
		int k = number;
		
		//System.out.println((passArray.size() - newString.length()));
		
		//passArrayCount - contain initial length of user password
		int passArrayCount = passArray.size();
		
		//setting the loop until newString(mixed password) will be the same length as user password
		while(newString.length() < passArrayCount) {
			
			if(passArray.size() >= k) {
				
				int kk = k-1;
				newString += passArray.get(kk);
				checkArray.add(kk);
				k += number;
			
			}else {
				
				k =  k % passArray.size();
				if(k == 0) {
					k = 1;
				}
				for(int i = (checkArray.size()-1); i>=0; i--) {
					int inddex = checkArray.get(i);
					passArray.remove(inddex);
				}
				
				checkArray.clear();
			}
			
		}
		
		return newString;
	}
	
}