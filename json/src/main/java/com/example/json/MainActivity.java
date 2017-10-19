package com.example.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btCreate,btParse;
    TextView tx;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init(){
        btCreate = findViewById(R.id.button);
        btCreate.setOnClickListener(this);
        btParse = findViewById(R.id.button2);
        btParse.setOnClickListener(this);
        tx = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                createJson();
                break;
            case R.id.button2:
                getDataByNet();
                break;
        }
    }

    private void createJson() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("jo","jo");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("jsonObject1","子对象");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(1);
            jsonArray.put(2);
            jsonArray.put(3);
            jsonObject.put("joja",jsonArray);
            data = jsonObject.toString();
            tx.setText("生成的JSON字符串:\n"+jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataByNet() {
        
    }
    
}
