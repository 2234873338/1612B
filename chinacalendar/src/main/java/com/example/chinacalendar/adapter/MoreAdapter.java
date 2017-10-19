package com.example.chinacalendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chinacalendar.R;

/**
 * Created by Qiyan on 2017/9/5.
 */

public class MoreAdapter extends BaseAdapter {
    private Context context;
    private String[] texts;
    private int[] imgs;
    public MoreAdapter(Context mContext,String[] text,int[] img){
        context = mContext;
        texts = text;
        imgs = img;
    }
    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_more,null);

        TextView textView = view.findViewById(R.id.tv);
        ImageView imageView = view.findViewById(R.id.iv);

        imageView.setImageResource(imgs[position]);
        textView.setText(texts[position]);
        return view;
    }
}
