package com.huage.processor;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class DownloadUtil {
    // 下载图片
    public static void downloadPicture(String fileUrl, String fileName) throws Exception {
        if (fileUrl == null || "".equals(fileUrl)) {
            return;
        }
        URL url = new URL(fileUrl);
        String fullFileName = "D:/image/" + fileName + ".jpg";
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fullFileName));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dataInputStream.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.toByteArray();
        fileOutputStream.write(output.toByteArray());
        dataInputStream.close();
        fileOutputStream.close();
    }
    // 测试
    public static void main(String[] args) throws  Exception {
        String url = "http://pic1.win4000.com/wallpaper/2019-11-22/5dd7593f10b9c.jpg";
        String fileName = "英雄联盟新英雄厄斐琉斯桌面壁纸";
        DownloadUtil.downloadPicture(url, fileName);
    }
}
