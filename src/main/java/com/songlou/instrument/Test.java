package com.songlou.instrument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) throws Exception {  
		/*String plainText = "test";
		String encryptStr = new DesHelper().encrypt(plainText);
		String decryptStr = new DesHelper().decrypt(encryptStr);

		System.out.println("encryptStr:" + encryptStr);
		System.out.println("decryptStr:" + decryptStr);*/
		
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
	}
}
