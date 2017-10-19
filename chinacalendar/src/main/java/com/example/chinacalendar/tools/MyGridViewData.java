package com.example.chinacalendar.tools;

import com.example.chinacalendar.R;

/**
 * Created by Qiyan on 2017/9/5.
 */

public class MyGridViewData {
    public static String[] getMyGridViewTexts(){
        String[] texts = new String[]{
                "星座",
                "测试",
                "姓名测试",
                "起名",
                "八字"
        };
        return texts;
    }

    public static int[] getMyGridViewImgs(){
        int[] imgs = new int[]{
                R.drawable.xingzuo,
                R.drawable.ceshi,
                R.drawable.xingmingceshi,
                R.drawable.qiming,
                R.drawable.bazi
        };
        return imgs;
    }
}
