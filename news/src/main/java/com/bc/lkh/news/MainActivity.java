package com.bc.lkh.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    com.bc.lkh.news.NewsAdapter adapter;
    ListView lv;
    TextView defaule, shehui, guon, junshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        lv = (ListView) findViewById(R.id.listview);
        defaule = (TextView) findViewById(R.id.tx_default);
        shehui = (TextView) findViewById(R.id.tx_shehui);
        guon = (TextView) findViewById(R.id.tx_guonei);
        junshi = (TextView) findViewById(R.id.tx_junshi);
        defaule.setOnClickListener(this);
        shehui.setOnClickListener(this);
        guon.setOnClickListener(this);
        junshi.setOnClickListener(this);
        new Thread() {
            @Override
            public void run() {
                getDataByType("http://v.juhe.cn/toutiao/index?type=&key=2ca3a5b1cb6edf55250bff550ac34325");
            }
        }.start();
    }

    void getDataByType(String type) {
        try {
            URL url = new URL(type);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.connect();
            if (con.getResponseCode() == 200) {
                InputStream inputStream = con.getInputStream();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                byte[] buffer = new byte[512];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    bs.write(buffer, 0, length);
                    bs.flush();
                }
                final News news = new Gson().fromJson(bs.toString(), new TypeToken<News>() {
                }.getType());
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                        intent.putExtra("url",news.getResult().getData().get(i).getUrl());
                        startActivity(intent);
                    }
                });
                adapter = new NewsAdapter(MainActivity.this, news);
                runOnUiThread(new Thread() {
                    @Override
                    public void run() {
                        lv.setAdapter(adapter);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tx_default:
                new Thread() {
                    @Override
                    public void run() {
                        getDataByType("http://v.juhe.cn/toutiao/index?type=&key=2ca3a5b1cb6edf55250bff550ac34325");
                    }
                }.start();
                break;
            case R.id.tx_shehui:
                new Thread() {
                    @Override
                    public void run() {
                        getDataByType("http://v.juhe.cn/toutiao/index?"
                                + "type=shehui&key=2ca3a5b1cb6edf55250bff550ac34325");
                    }
                }.start();
                break;
            case R.id.tx_guonei:
                new Thread() {
                    @Override
                    public void run() {
                        getDataByType("http://v.juhe.cn/toutiao/index?"
                                + "type=guonei&key=2ca3a5b1cb6edf55250bff550ac34325");
                    }
                }.start();
                break;
            case R.id.tx_junshi:
                new Thread() {
                    @Override
                    public void run() {
                        getDataByType("http://v.juhe.cn/toutiao/index?"
                                + "type=junshi&key=2ca3a5b1cb6edf55250bff550ac34325");
                    }
                }.start();
                break;
        }
    }
}
