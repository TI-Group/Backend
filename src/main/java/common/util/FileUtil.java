package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
    public static byte[] getFileContent(File file) {
        try {
            long fileSize = file.length();
            FileInputStream fi = new FileInputStream(file);
            byte[] buffer = new byte[(int) fileSize];
            fi.read(buffer);
            fi.close();
            return buffer;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("getFileContent exception");
        }
        return null;
    }
    public static boolean saveFileContent(byte[] content, File file) {
        try {
            if(!file.exists()) {
                if(!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content);
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("saveFileContent exception");
        }
        return false;
    }
}