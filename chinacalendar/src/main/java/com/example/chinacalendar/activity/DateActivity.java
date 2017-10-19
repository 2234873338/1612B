package com.example.chinacalendar.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chinacalendar.R;
import com.example.chinacalendar.bean.DateData;
import com.example.chinacalendar.fragment.DateFragment;
import com.example.chinacalendar.fragment.MoreFragment;
import com.example.chinacalendar.fragment.LifeFragment;
import com.example.chinacalendar.fragment.YijiFragment;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static android.R.attr.button;

/**
 * Created by Qiyan on 2017/8/28.
 */
@ContentView(R.layout.activity_date)
public class DateActivity extends Activity {
    @ViewInject(R.id.button1)
    Button bt1;
    @ViewInject(R.id.button2)
    Button bt2;
    @ViewInject(R.id.button3)
    Button bt3;
    @ViewInject(R.id.button4)
    Button bt4;
    FragmentManager fm;
    FragmentTransaction ft;
    DateFragment df;
    LifeFragment lf;
    YijiFragment yf;
    MoreFragment mf;
    URL url;
    HttpURLConnection conn;
    ByteArrayOutputStream bs;
    DateData dateData;
    Callback.Cancelable cancelable;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0x111) {
                df.updateUI(dateData);
            }
            if (msg.what == 0x112) {
                lf.updateUI(dateData);
            }
            if (msg.what == 0x113) {
                yf.updateUI(dateData);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        df = new DateFragment(this);
        lf = new LifeFragment();
        yf = new YijiFragment();
        mf = new MoreFragment(this);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.linapp, df);
        ft.add(R.id.linapp, lf);
        ft.add(R.id.linapp, yf);
        ft.add(R.id.linapp, mf);
        ft.show(df);
        ft.hide(lf);
        ft.hide(yf);
        ft.hide(mf);
        ft.commit();
        bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        parseAlmanac(df.Date(),0x111);

                    }
                }.start();
                ft = fm.beginTransaction();
                ft.show(df);
                ft.hide(lf);
                ft.hide(yf);
                ft.hide(mf);
                ft.commit();
            }
        });
        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        parseAlmanac(df.Date(),0x112);

                    }
                }.start();
                ft = fm.beginTransaction();
                ft.hide(df);
                ft.show(lf);
                ft.hide(yf);
                ft.hide(mf);
                ft.commit();
            }
        });
        bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        parseAlmanac(df.Date(),0x113);

                    }
                }.start();
                ft = fm.beginTransaction();
                ft.hide(df);
                ft.hide(lf);
                ft.show(yf);
                ft.hide(mf);
                ft.commit();
            }
        });
        bt4 = (Button) findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = fm.beginTransaction();
                ft.hide(df);
                ft.hide(lf);
                ft.hide(yf);
                ft.show(mf);
                ft.commit();
            }
        });
        //创建SlidingMenu对象
    SlidingMenu slidingMenu = new SlidingMenu(this);
    //设置滑动SlidingMenu的位置 LEFT左滑 RIGHT右滑 LEFT_RIGHT左右皆可
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

    //设置触摸屏幕的模式
    //设置要使菜单滑动,触碰屏幕的范围 三种响应模式TOUCHMODE_FULLSCREEN全屏响应 TOUCHMODE_MARGIN边界响应 TOUCHMODE_NONE不响应
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    //设置阴影图片的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
    //设置阴影的图片资源
        slidingMenu.setShadowDrawable(R.drawable.shadow);

    //设置menu划出时主页面剩余空间
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
    //设置渐入渐出(透明度)效果
        slidingMenu.setFadeDegree(0.5f);

    //绑定到Activity上
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    //给menu设置布局
        slidingMenu.setMenu(R.layout.menu_left);

        slidingMenu.setSecondaryShadowDrawable(R.drawable.shadow);
        slidingMenu.setSecondaryMenu(R.layout.menu_left);
}

    public void parseAlmanac(String d,int what) {
        try {
            String date = URLEncoder.encode(d, "UTF-8");
            String key = "39be059038a58ac9007f72dde14f2a14";
            url = new URL("http://v.juhe.cn/laohuangli/d?date=" + date + "&key=" + key);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                bs = new ByteArrayOutputStream();
                byte buffer[] = new byte[512];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    bs.write(buffer, 0, length);
                    bs.flush();
                }
            }
            Gson gson = new Gson();
            Log.d("date",bs.toString());
            dateData = gson.fromJson(bs.toString(), DateData.class);
           handler.sendEmptyMessage(what);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String url = "http://v.juhe.cn/laohuangli/d";
//        RequestParams params = new RequestParams(url);
//        params.addQueryStringParameter("date",d);
//        params.addQueryStringParameter("key","39be059038a58ac9007f72dde14f2a14");
//        handler.sendEmptyMessage(what);
//        dateData = new DateData();
//        cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//                Gson gson = new Gson();
//                dateData = gson.fromJson(result, DateData.class);
//
////                1获取
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
////                错误
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
////                3取消
//            }
//
//            @Override
//            public void onFinished() {
//
////                2结束
//            }
//        });
    }
}
