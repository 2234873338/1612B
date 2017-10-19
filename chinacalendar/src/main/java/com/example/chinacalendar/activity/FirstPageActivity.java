package com.example.chinacalendar.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chinacalendar.R;
import com.example.chinacalendar.dao.UserDao;

/**
 * Created by Qiyan on 2017/8/28.
 */

public class FirstPageActivity extends Activity {
    UserDao dao;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow();
            }
        });
        dao = new UserDao(FirstPageActivity.this);

    }
    private void popWindow(){
        View view = LayoutInflater.from(this).inflate(R.layout.pop_login, null);
        PopupWindow pop = new PopupWindow(view, 500, 400);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
        final EditText et_user = (EditText) view.findViewById(R.id.user);
        final EditText et_pwd = (EditText) view.findViewById(R.id.password);

        view.findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String user = et_user.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                if((user != null && !user.equals("") && (pwd != null && !pwd.equals("")))){
                    String data = dao.judgeUserNameisExist(user,pwd);
                    Toast.makeText(FirstPageActivity.this,data,Toast.LENGTH_SHORT).show();
                    if(data.equals("登陆成功")){
                        Intent i = new Intent(FirstPageActivity.this,DateActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
        view.findViewById(R.id.registerbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow2();
            }
        });
    }
    private void popWindow2(){
        View view = LayoutInflater.from(this).inflate(R.layout.pop_login2, null);
        PopupWindow pop = new PopupWindow(view, 500, 400);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
        final EditText et_user = (EditText) view.findViewById(R.id.registeruser);
        final EditText et_pwd = (EditText) view.findViewById(R.id.registerpassword);
        view.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_user != null && !et_user.equals("")){
                    if(et_pwd != null && !et_pwd.equals("")){
                        boolean isExist = dao.inserUser(et_user.getText().toString(),et_pwd.getText().toString());
                        if (isExist){
                            Intent i = new Intent(FirstPageActivity.this,DateActivity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(FirstPageActivity.this,"账号已存在",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(FirstPageActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(FirstPageActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
