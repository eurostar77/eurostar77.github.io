package com.bueffeltier.logic.foundation;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGeneratorApp
{
	private static final String ALGORITHM = "AES";
	private static final int KEY_SIZE = 256;

	public static void main(String[] args)
	{
		try
		{
			SecretKey key = generateKey();
			String keyString = bytesToHex(key.getEncoded());
			System.out.println("Generated Key: " + keyString);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
	}

	public static SecretKey generateKey() throws NoSuchAlgorithmException
	{
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		keyGenerator.init(KEY_SIZE);
		return keyGenerator.generateKey();
	}

	public static String bytesToHex(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
		{
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
}
