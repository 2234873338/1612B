package com.example.myxutils;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  @ViewInject(R.id.button)
  Button bt1;
  @ViewInject(R.id.button2)
  Button bt2;
  Callback.Cancelable cancelable;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
    //让Activity识别注解
    x.view().inject(this);
  }
  //绑定短按监听  type可以省略
  @Event(type = View.OnClickListener.class,value = R.id.button)
  private void MyonClickListener(View v){
//    Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
    //get
    String url = "http://v.juhe.cn/toutiao/index";
    RequestParams params = new RequestParams(url);
    params.addQueryStringParameter("type","");
    params.addQueryStringParameter("key","2ca3a5b1cb6edf55250bff550ac34325");
    //NetWorkonMainThread
     cancelable = x.http().get(params ,new Callback.CommonCallback<String>(){
      @Override
      public void onSuccess(String result) {
        Log.d("data",result);
        Log.d("data","1");

      }
      @Override
      public void onError(Throwable ex, boolean isOnCallback) {
      }
      @Override
      public void onCancelled(CancelledException cex) {
        Log.d("data","3");
      }

      @Override
      public void onFinished() {
        bt2.setText("获取结束");
        Log.d("data","2");
      }
    });
  }
  @Event(type = View.OnLongClickListener.class,value = R.id.button)
  private boolean MyonLongClickListenerBt1(View v){
//    Toast.makeText(this, "2222", Toast.LENGTH_SHORT).show();
cancelable.cancel();
    return  true;
  }

  //长按监听  type不可省略
  @Event(type = View.OnLongClickListener.class,value = R.id.button2)
  private boolean MyonLongClickListener(View v){
    bt1.setText("你点击了按钮2");
    return true;
  }
}
