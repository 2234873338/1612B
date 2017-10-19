package com.example.datetest.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Qiyan on 2017/8/8.
 */

public class MySqliteDatabase extends SQLiteOpenHelper {
    public static final String DB_Name = "user.db";
    public static final String Tab_Name = "user";

    public MySqliteDatabase(Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table" + Tab_Name + "(_id integer primary key autoincrement,username varchar(20), password varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
