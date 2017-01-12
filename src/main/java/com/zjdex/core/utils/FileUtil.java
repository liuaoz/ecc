package com.zjdex.core.utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作
 * 
 * @author LIUAOZ
 * @since 2016年12月23日 下午5:56:05
 * @version 1.0
 */
public class FileUtil {

    public static void main(String[] args) throws IOException {

        String filePath =
                "f:" + File.separator + "test" + File.separator + "xxx" + File.separator + "a.txt";
        createFile(filePath);
    }


    /**
     * 根据给定的文件路径，创建文件。如果父路径不存在，自动创建父目录
     * 
     * @param filepath
     * @return
     * @throws IOException
     */
    public static boolean createFile(String filepath) throws IOException {

        boolean result = false;

        File file = new File(filepath);

        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        result = file.createNewFile();

        return result;
    }

}
