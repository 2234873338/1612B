package com.bc.duanqiyan_test.Activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bc.duanqiyan_test.Adapter.NewsAdapter;
import com.bc.duanqiyan_test.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        isRun();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
    }

    void isRun(){
        Intent i = new Intent(MainActivity.this,NewsActivity.class);
        startActivity(i);
    }
}

