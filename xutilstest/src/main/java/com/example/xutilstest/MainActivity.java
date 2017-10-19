package com.example.xutilstest;

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
    Button button;
    @ViewInject(R.id.button2)
    Button button2;
    Callback.Cancelable cancelable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        让Activity识别注解
        x.view().inject(this);
    }

    //绑定短按监听 type可以省略
    @Event(type = View.OnClickListener.class,value = R.id.button)
    private void Button(View v){
//        Toast.makeText(this,"111",Toast.LENGTH_SHORT).show();
        String url = "http://v.juhe.cn/laohuangli/d";
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("date","2014-09-09");
        params.addQueryStringParameter("key","39be059038a58ac9007f72dde14f2a14");
        cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("data",result);
//                1获取
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

//                错误
            }

            @Override
            public void onCancelled(CancelledException cex) {

//                3取消
            }

            @Override
            public void onFinished() {
                button.setText("结果");
//                2结束
            }
        });
    }

    @Event(type = View.OnLongClickListener.class,value = R.id.button)
    private boolean Button1(View v){
        Toast.makeText(this,"222",Toast.LENGTH_SHORT).show();
        return true;
    }

    //绑定长按监听 type不可省略
    @Event(type = View.OnLongClickListener.class,value = R.id.button2)
    private boolean Button2(View v){
        button2.setText("你点击了按钮2");
        return true;
    }
}
