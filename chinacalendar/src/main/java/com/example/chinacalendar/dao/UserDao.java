package com.example.chinacalendar.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.chinacalendar.bean.UserData;
import com.example.chinacalendar.tools.MySqliteDatabase;

import java.util.ArrayList;

/**
 * Created by Qiyan on 2017/9/4.
 */

public class UserDao {
    MySqliteDatabase userDao;

    public UserDao(Context context) {
        userDao = new MySqliteDatabase(context);
    }

    public boolean inserUser(String name, String psw) {
        boolean isExist = false;
        ArrayList<UserData> arrayList = selectUser();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getUserName().equals(name)) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            return false;
        } else {
            String sql = "insert into user (username,password) values (?,?)";
//            String sql = "insert into "+MySqliteDatabase.Tab_Name+" ( "+MySqliteDatabase.Tab_UserName+"，"+
//                    MySqliteDatabase.Tab_PassWord+" ) values ( "+name+","+psw+" )";

//            Log.d("data",name+","+psw);
            userDao.db.execSQL(sql,new String[]{name,psw});

            return true;
        }
    }

    public ArrayList<UserData> selectUser() {
        ArrayList<UserData> arrayList = new ArrayList<>();
        String sql = "select * from User";
        Cursor cursor = userDao.db.rawQuery(sql, null);

        if (cursor.getCount() != 0) {
            cursor.moveToPrevious();
            while (cursor.moveToNext()) {
                UserData userData = new UserData();
                userData.setUserName(cursor.getString(cursor.getColumnIndex("username")));
                userData.setPassWord(cursor.getString(cursor.getColumnIndex("password")));
                arrayList.add(userData);
            }
        }
        return arrayList;
    }

    public String judgeUserNameisExist(String userName,String passWord){
        boolean isExist = false;
        ArrayList<UserData> arrayList = selectUser();
        for (int i = 0;i<arrayList.size();i++){
            if (arrayList.get(i).getUserName().equals(userName)){
                isExist = true;
                break;
            }
        }
        if(isExist){
            String content = judgePassWordisRight(userName, passWord);
            return "登陆成功";
        }else {
            return "账号不存在";
        }

    }

    public String judgePassWordisRight(String userName,String passWord){
        String sql = "select * from User where username = ?";
        Cursor cursor = userDao.db.rawQuery(sql, new String[]{userName});
        cursor.moveToFirst();
        String pwd = cursor.getString(cursor.getColumnIndex("password"));
        if(pwd.equals(passWord)){
            return "登陆成功";
        }else {
            return "密码错误";
        }
    }
}

