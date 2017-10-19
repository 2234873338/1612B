package com.example.json1612b;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements OnClickListener{
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
//        parseJson(data);
        getDataByNet();
        break;

    }
  }

  void createJson(){
    try {
      //根据数据的总体形式   创建Json对象或者数据   一般情况创建JsonObject
      JSONObject  jsonObject = new JSONObject();
      //向对象中添加基本键值对信息
      jsonObject.put("jo","jo");
      //向对象中添加子对象
      JSONObject jsonObject1 = new JSONObject();
      //向子对象中添加数据
      jsonObject1.put("jsonObject1","子对象");
      //将子对象添加至父对象中
      jsonObject.put("jojo",jsonObject1);
      //向父对象中添加子数组
      JSONArray jsonArray = new JSONArray();
      jsonArray.put(1);
      jsonArray.put(2);
      jsonArray.put(3);
      //将子数组添加至父对象中
      jsonObject.put("joja",jsonArray);
      data = jsonObject.toString();
      tx.setText("生成的JSON字符串：\n"+jsonObject.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void parseJson(String json){
    String content="解析的数据：\n";
    try {
      //接收的数据整体是什么形式就创建什么对象接收
      JSONObject jsonObject = new JSONObject(json);
      //提取的信息是什么类型的就创建什么对象接收
      String jojo = jsonObject.getString("jo");
      content += jojo+"\n";//content = content + jojo;
      JSONObject jsonObject1 = jsonObject.getJSONObject("jojo");
      String jojoString = jsonObject1.getString("jsonObject1");
      content += jojoString+"\n";
      JSONArray jsonArray = jsonObject.getJSONArray("joja");
      //解析数组时  数据规范的前提下才能用循环  不规范不能用
      for(int i = 0;i<jsonArray.length();i++){
        int ja = jsonArray.getInt(i);
        content += ja+"\n";
      }
      tx.setText(content);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  void getDataByNet(){
    new Thread(){
      public void run(){
        try {
          URL url = new URL("http://op.juhe.cn/robot/index?"
              + "info="+"你是谁"+"&dtype=&loc=&userid=&key=761522f85f516959958a310457c29735");
          HttpURLConnection con = (HttpURLConnection) url.openConnection();
          con.setRequestMethod("GET");
          con.setConnectTimeout(10000);
          con.setReadTimeout(10000);
          con.connect();
          if(con.getResponseCode() == 200){
            InputStream inputStream = con.getInputStream();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            byte buffer[] = new byte[512];
            int length;
            while( (length = inputStream.read(buffer))!= -1){
              bs.write(buffer,0,length);
              bs.flush();
            }
//            {
//              "reason":"成功的返回",
//                "result":{
//                  "code":100000,
//                  "text":"你好呀～找我干嘛？"
//            },
//              "error_code":0
//            }

            String data = bs.toString();
            //用字符串的方式解析

//            //从头开始查找
//            int start = data.indexOf("\"text\":\"");
//            //从固定位置开始查找
//            int end = data.indexOf("\"",start+8);
//            Log.d("ddddd","start:"+start+",end:"+end);
//            //截取字符串   包括开始（从start+8的位置开始） 不包括结束（到end-1结束）
//            final String text = data.substring(start+8,end);
//            runOnUiThread(new Thread(){
//              public void run(){
//                tx.setText("截取的数据：\n"+text);
//              }
//            });

            //用JSON解析
            JSONObject jsonObject = new JSONObject(data);
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
            final String text = jsonObject1.getString("text");
            runOnUiThread(new Thread(){
              public void run(){
                tx.setText("JSON解析到的数据："+text);
              }
            });
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }.start();
  }
}
