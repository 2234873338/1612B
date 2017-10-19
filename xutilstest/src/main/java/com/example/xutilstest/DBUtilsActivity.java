package com.example.xutilstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Qiyan on 2017/10/9.
 */
@ContentView(R.layout.dbutils)
public class DBUtilsActivity extends AppCompatActivity{
    @ViewInject(R.id.button3)
    Button button3;
    @ViewInject(R.id.listView)
    ListView listView;
    ArrayList<Student> arrayList;
    MyAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initDB();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }
    @Event(value = R.id.button3)
    public void button3(View v){
        arrayList = new ArrayList<>();
        arrayList.add(new Student("张三","18"));
        arrayList.add(new Student("李四","18"));
        arrayList.add(new Student("王二","19"));
        arrayList.add(new Student("麻子","19"));
        try {
            db.save(arrayList);
            adapter.notifyDataSetChanged();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    @Event(type = ListView.OnItemClickListener.class,value = R.id.listView)
    public void itemClickListener(View v){

    }
    @Event(type = ListView.OnItemLongClickListener.class,value = R.id.listView)
    public boolean itemLongClickListener(View v){
        
    return true;
    }

    DbManager db ;

    void initDB(){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig().setDbName("xutils.db").setTableCreateListener(new DbManager.TableCreateListener() {
            @Override
            public void onTableCreated(DbManager db, TableEntity<?> table){

            }
        })
                //设置是否允许事务，默认true
                .setAllowTransaction(true)
                //设置数据库的版本号
                .setDbVersion(1)
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion,
                                          int newVersion) {
                    }
                })
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
       db = x.getDb(daoConfig);
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tx = new TextView(DBUtilsActivity.this);
            tx.setText(arrayList.get(i).getName()+"        "+arrayList.get(i).getAge());
            return null;
        }
    }
}