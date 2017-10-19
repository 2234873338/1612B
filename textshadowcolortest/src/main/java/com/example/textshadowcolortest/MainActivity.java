package com.example.textshadowcolortest;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_text_horizontal = (TextView) findViewById(R.id.tv_text_horizontal);
        Shader shader_horizontal= new LinearGradient(600/4, 0, 600, 0, Color.parseColor("#FFFFFF"), Color.GREEN, Shader.TileMode.CLAMP);
        tv_text_horizontal.getPaint().setShader(shader_horizontal);
    }
}
