package com.example.cai.ftp;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by cai on 2017/12/28.
 */

public class FtpApp extends Application {
    private static FtpApp instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static FtpApp getInstance() {
        return instance;
    }

    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

}
