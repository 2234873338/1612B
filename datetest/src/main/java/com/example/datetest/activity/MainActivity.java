package com.example.datetest.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.datetest.R;
import com.example.datetest.adapter.MyPagerAdapter;
import com.example.datetest.tools.MyPageData;
import com.example.datetest.tools.MySqliteDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button enter;
    private ViewPager viewPager;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        enter = (Button) findViewById(R.id.enter);

        final ArrayList<ImageView> list = MyPageData.getPagerData(mContext);

        viewPager.setAdapter(new MyPagerAdapter(mContext,list));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == list.size()-1){
                    enter.setVisibility(View.VISIBLE);

                    jump();
                }else{
                    enter.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void jump(){
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FirstPageActivity.class);
                startActivity(intent);
            }
        });

    }
}
