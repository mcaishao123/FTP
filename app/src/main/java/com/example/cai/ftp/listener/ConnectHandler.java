package com.example.cai.ftp.listener;

import it.sauronsoftware.ftp4j.FTPFile;

/**
 * Created by cai on 2018/1/9.
 */

public interface ConnectHandler {
    void onSuccessful(FTPFile[] ftpFiles);

    void onFailure(Exception e);
}
