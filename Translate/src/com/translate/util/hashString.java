package com.translate.util;

import java.security.MessageDigest;

public class hashString {

	private static final String getInstance = "SHA-256";

	
	public static String hash(String str) {
		
		String hashString = "";
			
		try {
			
	        MessageDigest md2 = MessageDigest.getInstance(getInstance);
	        md2.update(str.getBytes()); 
	        byte byteData[] = md2.digest();	 
	        
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	        hashString = sb.toString();	 
	        
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		return hashString;
	}	

	public static void main(String a[]) {
		hashString hashObj = new hashString();
		System.out.println(hashObj.hash("getInstance"));	
	}
	
}