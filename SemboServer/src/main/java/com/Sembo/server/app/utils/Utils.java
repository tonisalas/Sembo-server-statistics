package com.Sembo.server.app.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	public static String encryptSHA1(String input) {
		String hash = "";

		try {

			MessageDigest md = MessageDigest.getInstance("SHA-1");

			byte[] messageDigest = md.digest(input.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			hash = no.toString(16);

			while (hash.length() < 32) {
				hash = "0" + hash;
			}

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return hash;
	}
	
}
