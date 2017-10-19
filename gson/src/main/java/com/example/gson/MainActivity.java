package com.example.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    Button btToJava,btToJson;
    Gson gson;
    String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init(){
        gson = new Gson();
        btToJava = (Button) findViewById(R.id.button);
        btToJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btToJson = (Button) findViewById(R.id.button2);
    }
}
