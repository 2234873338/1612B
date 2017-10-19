package com.example.slidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建SlidingMenu对象
        SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置滑动SlidingMenu的位置 LEFT左滑 RIGHT右滑 LEFT_RIGHT左右皆可
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

        //设置触摸屏幕的模式
        //设置要使菜单滑动,触碰屏幕的范围 三种响应模式TOUCHMODE_FULLSCREEN全屏响应 TOUCHMODE_MARGIN边界响应 TOUCHMODE_NONE不响应
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影图片的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        //设置阴影的图片资源
        slidingMenu.setShadowDrawable(R.drawable.shadow);

        //设置menu划出时主页面剩余空间
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置渐入渐出(透明度)效果
        slidingMenu.setFadeDegree(0.5f);

        //绑定到Activity上
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //给menu设置布局
        slidingMenu.setMenu(R.layout.menu_left);

        slidingMenu.setSecondaryShadowDrawable(R.drawable.shadow);
        slidingMenu.setSecondaryMenu(R.layout.menu_left);
    }
}
