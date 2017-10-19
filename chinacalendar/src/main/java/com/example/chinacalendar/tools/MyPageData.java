package com.example.chinacalendar.tools;

import android.content.Context;
import android.widget.ImageView;

import com.example.chinacalendar.R;

import java.util.ArrayList;

/**
 * Created by Qiyan on 2017/8/28.
 */

public class MyPageData {
    private  static ImageView iv;
    public static ArrayList<ImageView> getPagerData(Context context){
        int[] imgs = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };

        ArrayList<ImageView> list = new ArrayList<ImageView>();

        for(int i = 0;i<imgs.length;i++){
            iv = new ImageView(context);
            iv.setBackgroundResource(imgs[i]);
            list.add(iv);
        }
        return list;
    }
}
