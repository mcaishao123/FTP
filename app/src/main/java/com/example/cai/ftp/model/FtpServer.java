package com.example.cai.ftp.model;


import com.example.cai.ftp.FtpApp;
import com.example.cai.ftp.helper.Config;
import com.google.gson.Gson;

public class FtpServer {
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean isAnonymous;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public static FtpServer getCurrentFtpServer(){
        String ftpServerJson = FtpApp.getInstance().getPreferences().getString(Config.FTP_SERVER,"");
        try{
            return new Gson().fromJson(ftpServerJson,FtpServer.class);
        }catch (Exception  e){
            e.printStackTrace();
            return null;
        }
    }
}
