package com.songlou.instrument;

public class Test {
	public static void main(String[] args) throws Exception {  
		String plainText = "test";
		String encryptStr = new DesHelper().encrypt(plainText);
		String decryptStr = new DesHelper().decrypt(encryptStr);

		System.out.println("encryptStr:" + encryptStr);
		System.out.println("decryptStr:" + decryptStr);
	}
}
