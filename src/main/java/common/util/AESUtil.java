package common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    
    private static SecretKey key;
    
    static {
        try {
            //.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 根据ecnodeRules规则初始化密钥生成器
            // 生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom());
            // 产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            // 获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            // 根据字节数组生成AES密钥
            key = new SecretKeySpec(raw, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encode(String content) {
        try {
            // 获取并初始化Cipher
            Cipher encoder = Cipher.getInstance("AES");
            encoder.init(Cipher.ENCRYPT_MODE, key);
            byte[] contentByte = content.getBytes("utf-8");
            byte[] encoded = encoder.doFinal(contentByte);
            String encodedStr = Base64.getEncoder().encodeToString(encoded);
            return encodedStr;
        } catch (IllegalBlockSizeException | UnsupportedEncodingException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        // 如果有错就返回nulll
        return null;
    }
    
    public static String decode(String content) {
        try {
            // 获取并初始化Cipher
            Cipher decoder = Cipher.getInstance("AES");
            decoder.init(Cipher.DECRYPT_MODE, key);
            
            byte[] contentByte = Base64.getDecoder().decode(content);
            byte[] decoded = decoder.doFinal(contentByte);

            return new String(decoded, "utf-8");
        } catch (IllegalBlockSizeException | UnsupportedEncodingException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        // 如果有错就返回nulll
        return null;
    }
}
