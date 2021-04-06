package com.cnbg.zs.ebook.common.lang;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/11/27 11:13
* @Description
*/
public class RSAUtils {
    /**
    * 生成公私钥
    */
    public static Map<String,Object> genKeyPair() throws NoSuchAlgorithmException {
        Map<String,Object> keyMaps = new HashMap<>();
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到
        keyMaps.put("publicKey",publicKeyString);
        // 0表示公钥
        keyMaps.put("privateKey",privateKeyString);
        return keyMaps;
    }

    /**
    * 加密
    * @param str
    * @param publicKey
    * @return
    * @throws Exception
    */
    public static String encrypt(String str, String publicKey) throws Exception {
        // base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
        .generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
    * 解密
    * @param str
    * @param privateKey
    * @return
    * @throws Exception
    */
    public static String decrypt(String str, String privateKey) {
        // 64位解码加密后的字符串
        String outStr = null;
        try {
                byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
                // base64编码的私钥
                byte[] decoded = Base64.decodeBase64(privateKey);
                RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
                // RSA解密
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, priKey);
                outStr = new String(cipher.doFinal(inputByte));
            } catch (Exception e) {
                e.printStackTrace();
            }
        return outStr;
    }
}
