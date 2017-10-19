package com.example.myxutils;

import android.app.Application;
import org.xutils.x;

/**
 * Created by wangye on 2017/9/25.
 */

public class MyApp extends Application{
  public int a = 1;
  @Override
  public void onCreate() {
    super.onCreate();
    x.Ext.init(this);
    // 是否输出debug日志, 开启debug会影响性能.
    x.Ext.setDebug(BuildConfig.DEBUG);

  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }
}
