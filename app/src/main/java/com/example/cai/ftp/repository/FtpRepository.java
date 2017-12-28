package com.example.cai.ftp.repository;

import com.example.cai.ftp.model.FtpServer;

import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class FtpRepository {

    private FTPClient getInstance() {
        FTPClient instance = new FTPClient();
        try {
            FtpServer ftpServer = FtpServer.getCurrentFtpServer();
            if (ftpServer != null) {
                instance.connect(ftpServer.getHost(), ftpServer.getPort());
                instance.login(ftpServer.isAnonymous() ? "anonymous" : ftpServer.getUsername(), ftpServer.getPassword());

            }
        } catch (IOException | FTPIllegalReplyException | FTPException e) {
            e.printStackTrace();
        }

        return instance;
    }
}
