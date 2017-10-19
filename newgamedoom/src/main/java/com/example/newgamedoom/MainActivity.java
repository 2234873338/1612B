package com.example.newgamedoom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button pBtn,lBtn,mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pBtn = (Button) findViewById(R.id.playBtn);
        pBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), InstructionsActivity.class);
                startActivity(i);
            }
        });
        lBtn = (Button) findViewById(R.id.loadBtn);
        mBtn = (Button) findViewById(R.id.moreBtn);
    }
}
