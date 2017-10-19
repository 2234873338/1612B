package com.example.xutilstest;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Qiyan on 2017/10/9.
 */

public class MyApp extends Application{
    public int i = 1;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //是否输出debug日志，开启debug会影响性能
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
