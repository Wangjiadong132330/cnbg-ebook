package com.cnbg.zs.ebook.common.lang;

import com.cnbg.zs.ebook.common.constant.Constants;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/9 22:08
* @Description
*/
public class RESUtils {

private static final String KEY_AES = "AES";
    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static final String SECRET_KEY_SPEC = "asdfghjklqwertyu";

    /**
    * 加密
    * @param content 加密的字符串
    * @return
    * @throws Exception
    */
    public static String encrypt(String content) {
        String encryptString=null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY_SPEC.getBytes(), "AES"));
            byte[] b = cipher.doFinal(content.getBytes(Constants.HTTP_UTF8));
            // 采用base64算法进行转码,避免出现中文乱码
            encryptString = Base64.encodeBase64String(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
            return encryptString;
    }

    /**
    * 解密
    * @param encryptStr 解密的字符串
    * @return
    * @throws Exception
    */
    public static String decrypt(String encryptStr)  {
        byte[] decryptBytes = new byte[0];
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY_SPEC.getBytes(), KEY_AES));
            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.decodeBase64(encryptStr);
            decryptBytes = cipher.doFinal(encryptBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(decryptBytes);
    }


}
