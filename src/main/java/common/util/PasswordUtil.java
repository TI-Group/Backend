package common.util;

public class PasswordUtil {
    static public String getEncryptedPassword(String plainPassword) {
        return MD5Util.encoderByMd5(plainPassword).toLowerCase();
    }
    static public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return getEncryptedPassword(plainPassword).equals(encryptedPassword);
    }
}