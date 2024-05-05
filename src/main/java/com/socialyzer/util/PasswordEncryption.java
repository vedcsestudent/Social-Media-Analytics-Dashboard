package com.socialyzer.util;

import java.util.Base64;

public class PasswordEncryption {
	
	public static String getEncryptedPassword(String pwd)
	{
		Base64.Encoder en=Base64.getEncoder();
		byte[] arr= pwd.getBytes();
		String password=en.encodeToString(arr);
		return password;
	}

}
