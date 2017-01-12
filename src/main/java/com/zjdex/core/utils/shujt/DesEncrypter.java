package com.zjdex.core.utils.shujt;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class DesEncrypter {

	private static byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
			(byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };

	/**
	 * 加密
	 * 
	 * @param str
	 *            要加密的字符串
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str,String apikey) throws Exception {
		int iterationCount = 2;
		KeySpec keySpec = new PBEKeySpec(apikey.toCharArray(), salt,
			iterationCount);
		SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
			.generateSecret(keySpec);
		Cipher  ecipher = Cipher.getInstance(key.getAlgorithm());
		AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
			iterationCount);
		ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		str = new String(str.getBytes(), "UTF-8");
		return Base64.encodeBase64String(ecipher.doFinal(str.getBytes()));
	}

	/**
	 * 解密
	 * 
	 * @param str
	 *            要解密的字符串
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String str,String apikey) throws Exception {
		int iterationCount = 2;
		KeySpec keySpec = new PBEKeySpec(apikey.toCharArray(), salt,
			iterationCount);
		SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
			.generateSecret(keySpec);
		Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
		AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
			iterationCount);
		dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		return new String(dcipher.doFinal(Base64.decodeBase64(str)), "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		// str为加密前的参数字符串 王娟.431121199003108447
		String str = "idCardName=王娟&idCardCode=431121199003108447";
		String apikey="c8d7ac55b22faa229a7fb9243b017f31";
		System.out.println(DesEncrypter.encrypt(str,apikey));
	}
}


