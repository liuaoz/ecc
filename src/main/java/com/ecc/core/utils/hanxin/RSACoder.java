package com.ecc.core.utils.hanxin;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * @author LIUAOZ
 * @version 1.0
 */
public abstract class RSACoder {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    private static final int KEY_SIZE = 512;

    // 私钥解密
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    // 私钥解密2
    public static String decryptByPrivateKey(String data, String key) throws Exception {
        Decoder dec = Base64.getDecoder();
        byte[] dataByte = decryptByPrivateKey(dec.decode(data), dec.decode(key));
        return new String(dataByte);
    }

    // 公钥解密
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    // 公钥解密2
    public static String decryptByPublicKey(String data, String key) throws Exception {
        Decoder dec = Base64.getDecoder();
        byte[] dataByte = decryptByPublicKey(dec.decode(data), dec.decode(key));
        return new String(dataByte);
    }

    // 公钥加密
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    // 公钥加密2
    public static String encryptByPublicKey(String data, String key) throws Exception {
        Decoder dec = Base64.getDecoder();
        Encoder enc = Base64.getEncoder();
        byte[] signByte = encryptByPublicKey(data.getBytes("utf-8"), dec.decode(key));
        return enc.encodeToString(signByte);
    }

    // 私钥加密
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    // 私钥加密2
    public static String encryptByPrivateKey(String data, String key) throws Exception {
        Decoder dec = Base64.getDecoder();
        Encoder enc = Base64.getEncoder();
        byte[] signByte = encryptByPrivateKey(data.getBytes(), dec.decode(key));
        return enc.encodeToString(signByte);
    }

    // 私钥验证公钥密文
    public static boolean checkPublicEncrypt(String data, String sign, String pvKey)
            throws Exception {
        return data.equals(decryptByPrivateKey(sign, pvKey));
    }

    public static boolean checkPrivateEncrypt(String data, String sign, String pbKey)
            throws Exception {
        return data.equals(decryptByPublicKey(sign, pbKey));
    }

    // 取得私钥
    public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    // 取得公钥
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    // 初始化密�?
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        keyPairGen.initialize(KEY_SIZE);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) {

        try {
            Encoder enc = Base64.getEncoder();
            //
            Map<String, Object> mp = initKey();
            byte[] publicKey = getPublicKey(mp);
            byte[] privateKey = getPrivateKey(mp);
            String pbkey = enc.encodeToString(publicKey);
            String prkey = enc.encodeToString(privateKey);

            System.out.println("公钥:" + pbkey);
            System.out.println("私钥:" + prkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
