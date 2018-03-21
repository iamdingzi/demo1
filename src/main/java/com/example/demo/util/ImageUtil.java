package com.example.demo.util;


import java.io.FileOutputStream;
import java.io.InputStream;

public class ImageUtil {
    /**
     * 将字符流图片进行解码，并保存到指定目录
     *
     * @param stream 文件字符流
     * @param path 文件存储的路径
     * @param imgName 保存的文件名
     * @return
     */
    @SuppressWarnings("unused")
    public static void saveFileFromInputStream(InputStream stream, String path, String imgName)
            throws Exception {
        FileUtil.creatFolder(path);
        FileOutputStream fs = new FileOutputStream(path + "/" + imgName);
        System.out.println("文件保存路径：" + path + imgName);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
        System.out.println("文件写入完毕。");
    }

}
