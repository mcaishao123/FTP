package com.example.cai.ftp.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.cai.ftp.listener.ConnectHandler;
import com.example.cai.ftp.listener.FtpInfoHandler;
import com.example.cai.ftp.model.FtpInfo;
import com.example.cai.ftp.model.FtpServer;

import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class FtpRepository {
    private static FTPClient ftpClient;

    public static FTPClient getFTPClient() {
        if (ftpClient == null) {
            ftpClient = new FTPClient();
        }
        return ftpClient;
    }

    @SuppressLint("StaticFieldLeak")
    public static void connect(final FTPClient ftpClient, final ConnectHandler connectHandler) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
//            FtpServer ftpServer = FtpServer.getCurrentFtpServer();
                FtpServer ftpServer = new FtpServer();
                ftpServer.setHost("192.168.1.102");
                ftpServer.setPort(3333);
                ftpServer.setUsername("c");
                ftpServer.setPassword("c");
                if (ftpServer != null) {
                    try {
                        ftpClient.connect(ftpServer.getHost(), ftpServer.getPort());
                        ftpClient.login(ftpServer.isAnonymous() ? "anonymous" : ftpServer.getUsername(), ftpServer.getPassword());
                    } catch (IOException | FTPIllegalReplyException | FTPException e) {
                        e.printStackTrace();
                        return false;
                    } catch (IllegalStateException e){
                        return true;
                    }
                }
                return ftpClient.isConnected();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (connectHandler != null) {
                    if (aBoolean) {
                        connectHandler.onSuccessful();
                    } else {
                        connectHandler.onFailure();
                    }
                }
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public static void getFtpInfo(final FtpInfoHandler ftpInfoHandler) {
        new AsyncTask<Void, Void, FtpInfo>() {

            @Override
            protected FtpInfo doInBackground(Void... voids) {
                FtpInfo ftpInfo = new FtpInfo();
                try {
                    ftpInfo.setFtpFiles(ftpClient.list());
                    ftpInfo.setCurrentDir(ftpClient.currentDirectory());
                    ftpInfo.setHost(ftpClient.getHost());
                    ftpInfo.setPort(ftpClient.getPort());
                } catch (IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
                    e.printStackTrace();
                    return null;
                }

                return ftpInfo;
            }

            @Override
            protected void onPostExecute(FtpInfo ftpInfo) {
                if (ftpInfoHandler != null) ftpInfoHandler.getFtpInfo(ftpInfo);
            }
        }.execute();
    }
}
