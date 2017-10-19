package cn.xinxing.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import cn.xinxing.test.adapter.ImageAdapter;
import cn.xinxing.test.constant.Images;

public class MainActivity extends Activity {

    ImageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv);
        adapter = new ImageAdapter(MainActivity.this, Images.imageUrls, lv);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.mImageLoader.cancelAllTask();
    }
}
