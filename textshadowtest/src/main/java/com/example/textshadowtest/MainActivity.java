package com.example.textshadowtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView addTitle = (TextView)findViewById(R.id.addTitle);
        addTitle.setText("[标题]AngelaBaby日本民族风");
        AlphaAnimation alp = new AlphaAnimation(0.0f,1.0f);
        alp.setDuration(3000);
        addTitle.setAnimation(alp);

        alp.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationStart(Animation animation)
            {

            }

            public void onAnimationRepeat(Animation animation)
            {

            }

            public void onAnimationEnd(Animation animation)
            {
                addTitle.setVisibility(View.INVISIBLE);
            }
        });

    }
}
