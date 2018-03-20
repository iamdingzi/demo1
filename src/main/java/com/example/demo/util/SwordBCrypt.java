package com.example.demo.util;

import java.util.Arrays;
import java.util.Random;

/**
 * 对BCrypt算法修改
 * @author lilinfeng
 */
public class SwordBCrypt extends BCrypt {
	
	static private final char sword_code[] = {
		'.', '/', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
		'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
		'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9'
	};
	
	public static String hashpw(String password){
		String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12)).replaceFirst("\\$2a\\$12\\$","yuantou");
		int i = new Random().nextInt(sword_code.length);
		String myhashpw = hashpw + sword_code[i];
		return myhashpw;
	}
	public static boolean checkpw(String plaintext, String hashed) {
		char[] hashedcharArray = hashed.toCharArray();
		char[] lfsword = "yuantou".toCharArray();
		char[] replace = "$2a$12$".toCharArray();
		for (int i = 0; i < replace.length;i++) {
			if (hashedcharArray[i]==lfsword[i]) {
				hashedcharArray[i]=replace[i];
			}else {
				return false;
			}
		}
		char[] really = Arrays.copyOfRange(hashedcharArray, 0, hashedcharArray.length-1);
		return BCrypt.checkpw(plaintext, new String(really));
	}
}
