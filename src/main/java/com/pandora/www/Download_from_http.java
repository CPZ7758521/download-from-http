package com.pandora.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 从网络Url中下载文件
 */
public class Download_from_http {

    private static Logger LOG = LoggerFactory.getLogger(Download_from_http.class);

    public static void downloadFromUrl(String urlStr, String fileName, String savePath) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //设置超时时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        //获取字节数组
        byte[] getData = readInputStream(inputStream);

        //文件保存的位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter fow = new OutputStreamWriter(fos);
        fos.write(getData);
        fow.flush();
        if (fos != null) {
            fow.close();
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        LOG.info(url + "download success!");

    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
