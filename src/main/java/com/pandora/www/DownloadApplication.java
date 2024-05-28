package com.pandora.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DownloadApplication {
    private static Logger LOG = LoggerFactory.getLogger(DownloadApplication.class);
    public static void main(String[] args) throws IOException {

        String url = "http://142.171.173.150:7759/#/";
        long currentTimestamp = System.currentTimeMillis();
        String fileName = "log" + currentTimestamp + ".log";
        String savePath = "F:\\download\\test";
        Download_from_http.downloadFromUrl(url, fileName, savePath);
        LOG.info(fileName + "download success!");
    }
}
