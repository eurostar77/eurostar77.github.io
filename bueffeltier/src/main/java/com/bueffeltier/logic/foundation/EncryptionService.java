package com.bueffeltier.logic.foundation;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.bueffeltier.crosscutting.AppPropertyService;

public class EncryptionService
{
	private static EncryptionService instance;
	private static final String ALGORITHM = "AES";
	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();
	private SecretKeySpec secretKeySpec;

	private EncryptionService()
	{
		try
		{
			String key = appPropertyService.getAESSecret();
			byte[] keyBytes = hexToBytes(key);

			// Kürzen oder Verlängern des Schlüssels auf 32 Bytes (256 Bit)
			if (keyBytes.length != 32)
			{
				byte[] tempKeyBytes = new byte[32];
				System.arraycopy(
				    keyBytes, 0, tempKeyBytes, 0, Math.min(keyBytes.length, 32)
				);
				keyBytes = tempKeyBytes;
			}

			secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static EncryptionService getInstance()
	{
		if (instance == null)
		{
			instance = new EncryptionService();
		}
		return instance;
	}

	public String encryptData(String data) throws Exception
	{
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] encryptedBytes = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public String decryptData(String encryptedData) throws Exception
	{
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] decryptedBytes = cipher
		    .doFinal(Base64.getDecoder().decode(encryptedData));
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}

	private byte[] hexToBytes(String hexString)
	{
		int length = hexString.length();
		byte[] bytes = new byte[length / 2];
		for (int i = 0; i < length; i += 2)
		{
			bytes[i
			    / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
			        + Character.digit(hexString.charAt(i + 1), 16));
		}
		return bytes;
	}
}
