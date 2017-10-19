package com.example.asynctask_runonuithread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    Button bt1,bt2,bt3;
    TextView tx1,tx2;
    MyTask myTask;
    int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTask = new MyTask();
                myTask.execute("http://mail.163.com/");
            }
        });
        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUIByrunOnUIThread();
            }
        });
        bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tx1 = (TextView) findViewById(R.id.textView);
        tx2 = (TextView) findViewById(R.id.textView2);
    }

    class MyTask extends AsyncTask<String,Integer,String>{

        @Override
        protected void onPreExecute() {
            tx1.setText("准备下载...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                //设置此参数 获取网址 或服务器提供的数据长度服务
                conn.setRequestProperty("Accept-Encoding","identity");
                conn.connect();


                if(conn.getResponseCode() == 200){
                    InputStream inputStream = conn.getInputStream();
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    byte buffer[] = new byte[2048];
                    int length;
                    //获取所下载数据的长度 需要网站或服务器支持 不支持 获取的结果为-1
                    int total = conn.getContentLength();
                    int now = 0;//统计当前一共下载的数据
                    while((length = inputStream.read(buffer)) != -1){
                        bs.write(buffer,0,length);
                        bs.flush();
                        now += length;
                        publishProgress((int)(now/(float)total * 100));
                        Thread.sleep(500);
                    }
                    return bs.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        //接收下载进度
        @Override
        protected void onProgressUpdate(Integer... values) {
            tx1.setText("当前下载进度"+values[0]+"%");
        }

        @Override
        protected void onPostExecute(String s) {
            tx2.setText("下载的数据:\n"+s);
        }

        @Override
        protected void onCancelled() {
            tx1.setText("下载已取消");
        }
    }
    void updateUIByrunOnUIThread(){
        new Thread(){
            public void run(){
                try {
                    URL url = new URL("http://www.baidu.com");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.connect();
                    if(conn.getResponseCode() == 200){
                        InputStream inputStream = conn.getInputStream();
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte buffer[] = new byte[2048];
                        int length;
                        while((length = inputStream.read(buffer)) != -1){
                            byteArrayOutputStream.write(buffer,0,length);
                            byteArrayOutputStream.flush();
                        }
                        //在子线程中直接调用此方法更新UI
                        //参数为子线程对象 创建对象时 重写run方法 将更新UI的代码，注意，不要调用start方法
                        runOnUiThread(new Thread(){
                            public void run(){
                                tx2.setText("下载的数据:\n"+byteArrayOutputStream.toString());
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
