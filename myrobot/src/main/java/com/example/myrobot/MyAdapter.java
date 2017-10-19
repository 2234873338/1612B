package com.example.myrobot;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Qiyan on 2017/8/23.
 */

public class MyAdapter extends BaseAdapter {
    Context c;
    ArrayList<HashMap<String,String>> arrayList;
    MyAdapter(Context c,ArrayList<HashMap<String,String>> arrayList){
        this.c = c;
        this.arrayList = arrayList;
    }


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

    @Override//组件复用(判断view是否为空) ViewHolder:减少findViewById的时间
    public View getView(int i, View view, ViewGroup viewGroup) {
        //根据不同人物说的话 选择不同的布局文件
        //用户说的话
        if(arrayList.get(i).get("label").equals("user")){
            view = View.inflate(c,R.layout.me,null);
            TextView tx = view.findViewById(R.id.txMyself);
            tx.setText(arrayList.get(i).get("words"));
        }
        if(arrayList.get(i).get("label").equals("robot")){
            view = View.inflate(c,R.layout.robot,null);
            TextView tx = view.findViewById(R.id.txRobot);
            tx.setText(arrayList.get(i).get("words"));
        }
        return view;
    }

    public void updateAdapter(ArrayList<HashMap<String,String>> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
}
