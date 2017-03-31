package com.ecc.core.utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作
 *
 * @author LIUAOZ
 * @version 1.0
 * @since 2016年12月23日 下午5:56:05
 */
public class FileUtil {

    private FileUtil() {
    }

    /**
     * 根据给定的文件路径，创建文件。如果父路径不存在，自动创建父目录
     *
     * @param filepath
     * @return
     * @throws IOException
     */
    public static boolean createFile(String filepath) throws IOException {

        File file = new File(filepath);

        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        return file.createNewFile();

    }

}
