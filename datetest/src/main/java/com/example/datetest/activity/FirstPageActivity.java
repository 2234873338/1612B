package com.example.datetest.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.datetest.R;
import com.example.datetest.tools.MySqliteDatabase;

/**
 * Created by Qiyan on 2017/8/28.
 */

public class FirstPageActivity extends Activity {
    private MySqliteDatabase sql;
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

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(getApplicationContext(), "用户名 密码不能为空", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT)
                            .show();
                    startActivityForResult(new Intent(getApplicationContext(),
                            DateActivity.class) , 10);
                }
            }
        });
    }
}
