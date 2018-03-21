package com.example.demo.util;


import java.io.File;

public class FileUtil {
    /**
     * 创建文件夹路径
     * （给定全路径，该方法逐级创建文件夹）
     * @param  sPath
     * 			文件夹路径
     * @return 创建成功返回true，否则返回false
     */
    public static boolean creatFolder(String sPath) throws Exception {
        File firstFolder;
        String[] pathArr = sPath.split("/");
        String checkPath = "";
        for(String subPath : pathArr){
            checkPath += subPath + "\\";
            firstFolder = new File(checkPath);
            if(!firstFolder.exists() && !firstFolder.isDirectory()) {
                System.out.println("//文件夹不存在" + checkPath);
                firstFolder.mkdir();
            }else{
                System.out.println("//文件夹存在" + checkPath);
            }
        }

        return true;
    }
}
