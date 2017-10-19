package com.example.myrobot1612b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

  Button btSend;
  EditText editText;
  ListView listView;
  ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
  MyAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }
  void init(){
    btSend = (Button) findViewById(R.id.button);
    btSend.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        sendMessageToNet();
      }
    });
    editText = (EditText) findViewById(R.id.editText);
    listView = (ListView) findViewById(R.id.listview);
    adapter = new MyAdapter(MainActivity.this,arrayList);
    listView.setAdapter(adapter);
  }

  void sendMessageToNet(){
    final String data = editText.getText().toString();
    if(data == null  ||  data.equals("")){
      Toast.makeText(this, "消息不能为空", Toast.LENGTH_SHORT).show();
    }else{
      HashMap<String,String> map = new HashMap<>();
      map.put("label","user");
      map.put("words",data);
      arrayList.add(map);
      //刷新适配器
      adapter.updateAdapter(arrayList);

      new Thread(){
        public void run(){
          try {
            URL url = new URL("http://op.juhe.cn/robot/index");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.connect();
            //用post向服务器发送数据
            OutputStream os = con.getOutputStream();
            //info=%E4%BD%A0%E5%A5%BD&dtype=&loc=&userid=&key=761522f85f516959958a310457c29735
            os.write(("info="+data+
                "&dtype=&loc=&userid=&key=761522f85f516959958a310457c29735").getBytes("UTF-8"));
            os.flush();

            if(con.getResponseCode() == 200){
              InputStream is = con.getInputStream();
              ByteArrayOutputStream bs = new ByteArrayOutputStream();
              byte buffer[] = new byte[512];
              int length;
              while((length = is.read(buffer))!=-1){
                bs.write(buffer,0,length);
                bs.flush();
              }
              JSONObject jo = new JSONObject(bs.toString());
              JSONObject jojo = jo.getJSONObject("result");
              String content = jojo.getString("text");
              HashMap<String,String> map = new HashMap<>();
              map.put("label","robot");
              map.put("words",content);
              arrayList.add(map);
              runOnUiThread(new Thread(){
                public void run(){
                  adapter.updateAdapter(arrayList);
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
}
