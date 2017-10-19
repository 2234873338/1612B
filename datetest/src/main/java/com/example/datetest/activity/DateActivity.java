package com.example.datetest.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.datetest.R;
import com.example.datetest.bean.DateData;
import com.example.datetest.fragment.DateFragment;
import com.example.datetest.fragment.FengshuiFragment;
import com.example.datetest.fragment.LifeFragment;
import com.example.datetest.fragment.YijiFragment;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Qiyan on 2017/8/8.
 */

public class DateActivity extends Activity{
    Button bt1,bt2,bt3;
    FragmentManager fm;
    FragmentTransaction ft;
    DateFragment df;
    LifeFragment lf;
    YijiFragment yf;
    FengshuiFragment ff;
    URL url;
    HttpURLConnection conn;
    ByteArrayOutputStream bs;
    DateData dateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        df = new DateFragment();
        lf = new LifeFragment();
        yf = new YijiFragment();
        ff = new FengshuiFragment();
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.linapp, df);
        ft.add(R.id.linapp, lf);
        ft.add(R.id.linapp, yf);
        ft.add(R.id.linapp, ff);
        ft.show(df);
        ft.hide(lf);
        ft.hide(yf);
        ft.hide(ff);
        ft.commit();
        bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        parseAlmanac(df.Date());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                df.updateUI(dateData);
                            }
                        });
                    }
                }.start();
                ft = fm.beginTransaction();
                ft.show(df);
                ft.hide(lf);
                ft.hide(yf);
                ft.commit();
            }
        });
        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        parseAlmanac(df.Date());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lf.updateUI(dateData);
                            }
                        });
                    }
                }.start();
                ft = fm.beginTransaction();
                ft.hide(df);
                ft.show(lf);
                ft.hide(yf);
                ft.commit();
            }
        });
        bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        parseAlmanac(df.Date());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                yf.updateUI(dateData);
                            }
                        });
                    }
                }.start();
                ft = fm.beginTransaction();
                ft.hide(df);
                ft.hide(lf);
                ft.show(yf);
                ft.commit();
            }
        });
    }

    public void parseAlmanac(String d){
        try {
            String date = URLEncoder.encode(d, "UTF-8");
            String key = "39be059038a58ac9007f72dde14f2a14";
            url = new URL("http://v.juhe.cn/laohuangli/d?date="+date+"&key="+key);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            if(conn.getResponseCode()==200){
                InputStream is = conn.getInputStream();
                bs = new ByteArrayOutputStream();
                byte buffer[] = new byte[512];
                int length;
                while ((length = is.read(buffer)) != -1){
                    bs.write(buffer, 0, length);
                    bs.flush();
                }
            }
//            Gson gson = new Gson();
//            dateData = gson.fromJson(bs.toString(), DateData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
