package com.example.cai.ftp.model;

import it.sauronsoftware.ftp4j.FTPFile;

/**
 * Created by cai on 2018/1/11.
 */

public class FtpInfo {
    private FTPFile[] ftpFiles;
    private String currentDir;
    private String host;
    private int port;

    public FTPFile[] getFtpFiles() {
        return ftpFiles;
    }

    public void setFtpFiles(FTPFile[] ftpFiles) {
        this.ftpFiles = ftpFiles;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return "ftp://" + host + ":" + port + currentDir;
    }
}
