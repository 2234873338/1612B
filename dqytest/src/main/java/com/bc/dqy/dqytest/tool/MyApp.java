package com.bc.dqy.dqytest.tool;

import android.app.Application;
import com.yanzhenjie.nohttp.BuildConfig;
import com.yanzhenjie.nohttp.NoHttp;
import org.xutils.x;

/**
 * Created by Qiyan on 2017/10/17.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
