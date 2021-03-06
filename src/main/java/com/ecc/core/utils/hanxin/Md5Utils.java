package com.ecc.core.utils.hanxin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Base64;

/**
 * MD5摘要工具类
 */
public class Md5Utils {
    private Md5Utils() {}

    public static String md5ToBas64(String data) {
        MessageDigest md = null;
        String str = null;
        try {
            md = MessageDigest.getInstance("MD5");
            str = new String(Base64.encode(md.digest(data.getBytes("UTF-8"))), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String md5ToHexStr(String data) {
        MessageDigest md = null;
        String str = null;
        try {
            md = MessageDigest.getInstance("MD5");
            // str = Hex.toHexString(md.digest(data.getBytes("UTF-8")));
            str = bytesToHex(md.digest(data.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
