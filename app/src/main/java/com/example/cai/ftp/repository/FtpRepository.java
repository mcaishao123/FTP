package com.example.cai.ftp.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.cai.ftp.listener.ConnectHandler;
import com.example.cai.ftp.model.FtpServer;

import it.sauronsoftware.ftp4j.FTPClient;

public class FtpRepository {

    @SuppressLint("StaticFieldLeak")
    public static void getInstance(final ConnectHandler connectHandler) {
        final FTPClient ftpClient = new FTPClient();

        new AsyncTask<Void, Void, FTPClient>() {

            @Override
            protected FTPClient doInBackground(Void... voids) {
                try {
//            FtpServer ftpServer = FtpServer.getCurrentFtpServer();
                    FtpServer ftpServer = new FtpServer();
                    ftpServer.setHost("192.168.1.102");
                    ftpServer.setPort(3333);
                    ftpServer.setUsername("c");
                    ftpServer.setPassword("c");
                    if (ftpServer != null) {
                        ftpClient.connect(ftpServer.getHost(), ftpServer.getPort());
                        ftpClient.login(ftpServer.isAnonymous() ? "anonymous" : ftpServer.getUsername(), ftpServer.getPassword());
                    }
                    if (ftpClient.isConnected()) {
                        return ftpClient;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(FTPClient ftpClient) {

                if (ftpClient != null) {

                    try {
                        connectHandler.onSuccessful(ftpClient.list());
                    } catch (Exception e) {
                        e.printStackTrace();
                        connectHandler.onFailure(e);

                    }
                } else {
                    connectHandler.onFailure(new Exception("Unable to connect"));
                }
            }
        }.execute();

    }
}
