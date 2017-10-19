package com.example.newgamedoom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * Created by Qiyan on 2017/9/15.
 */

public class InstructionsActivity extends Activity {
    TextView textView;
    Shader shader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        textView = findViewById(R.id.instructionsTv);
        shader = new LinearGradient(400/4,0,400,0, Color.parseColor("#007F66"),Color.parseColor("#A9765B"),Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
        textView.setText("2XXX年，第三次世界大战爆发，各大国之间皆动用了核武器，" +
                "核战争，完全可以靠当前的科技修复地球去除核辐射，人们天真的以为躲到核战争结束就好了" +
                "然而，最大的威胁并不是核武器，而是一处被核武器意外炸开的异次元裂缝，人类的自由" +
                "就此被异生物剥夺");
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(3000);
        textView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Thread.sleep(1000);
                    AlphaText();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void AlphaText(){
        final AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(3000);
        textView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Thread.sleep(3000);
                    textView.setText("");
                    Intent i = new Intent(InstructionsActivity.this,ChoiceActivity.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
