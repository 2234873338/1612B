package com.example.chinacalendar.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.chinacalendar.R;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    RotateAnimation rotateAnimation;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.animImg);
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotate);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(3000);
        imageView.startAnimation(rotateAnimation);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(3000);
                    isRun();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    public void isRun(){
        Intent i;
        if (sharedPreferences.getBoolean("isRun",false)){
            i = new Intent(this,FirstPageActivity.class);
        }else{
            i = new Intent(this,ViewPagerActivity.class);
            editor.putBoolean("isRun",true);
            editor.commit();
        }
        startActivity(i);
        finish();
    }
}