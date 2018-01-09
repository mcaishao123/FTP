package com.example.cai.ftp.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.cai.ftp.listener.ConnectHandler;
import com.example.cai.ftp.model.FtpServer;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

public class FtpRepository {

    @SuppressLint("StaticFieldLeak")
    public static void getInstance(final ConnectHandler connectHandler) {
        new AsyncTask<Void, Void, FTPFile[]>() {

            @Override
            protected FTPFile[] doInBackground(Void... voids) {
                final FTPClient instance = new FTPClient();
                try {
//            FtpServer ftpServer = FtpServer.getCurrentFtpServer();
                    FtpServer ftpServer = new FtpServer();
                    ftpServer.setHost("192.168.1.102");
                    ftpServer.setPort(3333);
                    ftpServer.setUsername("c");
                    ftpServer.setPassword("c");
                    if (ftpServer != null) {
                        instance.connect(ftpServer.getHost(), ftpServer.getPort());
                        instance.login(ftpServer.isAnonymous() ? "anonymous" : ftpServer.getUsername(), ftpServer.getPassword());
                    }
                    if (instance.isConnected()) {
                        return instance.list();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(FTPFile[] ftpFiles) {
                if (ftpFiles != null) {
                    connectHandler.onSuccessful(ftpFiles);
                } else {
                    connectHandler.onFailure(new Exception("Unable to connect"));
                }
            }
        }.execute();
    }
}
