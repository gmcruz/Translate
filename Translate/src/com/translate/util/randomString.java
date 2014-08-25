package com.translate.util;

import java.util.Random;

public class randomString {

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMBER_CHAR_LIST = "12345678901234567890123456789012345678901234567890";
	private static final int RANDOM_STRING_LENGTH = 4;

	/**
	 * This method generates random string
	 * 
	 * @return
	 */
	public static String generateRandomString() {

		StringBuffer randStr = new StringBuffer();
		for(int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}
	
	public String generateRandomNumber() {

		StringBuffer randStr = new StringBuffer();
		for(int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = NUMBER_CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * This method generates random numbers
	 * 
	 * @return int
	 */
	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(NUMBER_CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public static void main(String a[]) {
		randomString msr = new randomString();
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		
		System.out.println(msr.generateRandomNumber());
		System.out.println(msr.generateRandomNumber());
		System.out.println(msr.generateRandomNumber());
		System.out.println(msr.generateRandomNumber());
		System.out.println(msr.generateRandomNumber());
		System.out.println(msr.generateRandomNumber());
		System.out.println(msr.generateRandomNumber());
	}
}