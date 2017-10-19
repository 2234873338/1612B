package com.example.chinacalendar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chinacalendar.R;
import com.example.chinacalendar.adapter.MyPagerAdapter;
import com.example.chinacalendar.tools.MyPageData;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {
    private Button enter;
    private ViewPager viewPager;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        mContext = ViewPagerActivity.this;
        viewPager = (ViewPager) findViewById(R.id.v4ViewPager);
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