package com.example.chinacalendar.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

/**
 * Created by Qiyan on 2017/8/28.
 */

public class MySqliteDatabase extends SQLiteOpenHelper {
    public static final String DB_Name = "users.db";
    public static final String Tab_Name = "User";
    public static final String Tab_UserName = "username";
    public static final String Tab_PassWord = "password";
    public SQLiteDatabase db = getWritableDatabase();

    public MySqliteDatabase(Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + Tab_Name +
                "(_id integer primary key autoincrement," +
                "username varchar(20), password varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    DbManager db ;
//
//    void initDB(){
//        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("xutils.db").setTableCreateListener(new DbManager.TableCreateListener() {
//            @Override
//            public void onTableCreated(DbManager db, TableEntity<?> table){
//
//            }
//        })
//                //设置是否允许事务，默认true
//                .setAllowTransaction(true)
//                //设置数据库的版本号
//                .setDbVersion(1)
//                //设置数据库更新的监听
//                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//                    @Override
//                    public void onUpgrade(DbManager db, int oldVersion,
//                                          int newVersion) {
//                    }
//                })
//                //设置数据库打开的监听
//                .setDbOpenListener(new DbManager.DbOpenListener() {
//                    @Override
//                    public void onDbOpened(DbManager db) {
//                        //开启数据库支持多线程操作，提升性能
//                        db.getDatabase().enableWriteAheadLogging();
//                    }
//                });
//        db = x.getDb(daoConfig);
//    }
}
