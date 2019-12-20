package com.huage.processor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class URLDownloadUtil {
    // 通过get请求得到读取器响应数据的数据流
    public static InputStream getInputStreamByGet(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                return inputStream;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将服务器响应的数据流存到本地文件
    public static void saveData(InputStream is, File file) {
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

        }
    }

    public static void main(String[] args) {
        String url = "http://pic1.win4000.com/wallpaper/2019-11-22/5dd7560672d32.jpg";
        String fileName = "08.jpg";
        File file = new File("D:/image/", fileName);
        InputStream inputStream = URLDownloadUtil
                .getInputStreamByGet(url);
        URLDownloadUtil.saveData(inputStream, file);
    }
}
