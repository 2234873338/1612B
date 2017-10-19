package com.example.toutiaotest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView imageView = (ImageView) findViewById(R.id.imageView);
    RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
        MainActivity.this,R.anim.rotate);
    rotateAnimation.setFillAfter(true);
    rotateAnimation.setDuration(3000);
    imageView.startAnimation(rotateAnimation);
    sharedPreferences = getSharedPreferences("1611",MODE_PRIVATE);
    new Thread(){
      public void run(){
        try {
          sleep(3000);
          if(sharedPreferences.getBoolean("isRun",false)){//true代表运行过
            Intent i = new Intent(MainActivity.this,NewsActivity.class);
            startActivity(i);
            finish();
          }else{
            editor = sharedPreferences.edit();
            editor.putBoolean("isRun",true);
            editor.commit();
            Intent i = new Intent(MainActivity.this,ViewPagerActivity.class);
            startActivity(i);
            finish();
          }

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }.start();

  }
}
