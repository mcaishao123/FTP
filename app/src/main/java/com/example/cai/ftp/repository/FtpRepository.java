package com.example.cai.ftp.repository;

import com.example.cai.ftp.model.FtpServer;

import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class FtpRepository {

    public static FTPClient getInstance() {
        final FTPClient instance = new FTPClient();
        new Runnable(){

            @Override
            public void run() {
                try {
//            FtpServer ftpServer = FtpServer.getCurrentFtpServer();
                    FtpServer ftpServer = new FtpServer();
                    ftpServer.setHost("192.168.43.1");
                    ftpServer.setPort(3333);
                    ftpServer.setUsername("c");
                    ftpServer.setPassword("c");
                    if (ftpServer != null) {
                        instance.connect(ftpServer.getHost(), ftpServer.getPort());
                        instance.login(ftpServer.isAnonymous() ? "anonymous" : ftpServer.getUsername(), ftpServer.getPassword());

                    }
                } catch (IOException | FTPIllegalReplyException | FTPException e) {
                    e.printStackTrace();
                }

            }
        }.run();

        return instance;
    }
}
