package com.example.toutiaotest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangye on 2017/8/2.
 */

public class NewsActivity extends Activity {
  ImageView mImageView;
  private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离
  private HorizontalScrollView mHorizontalScrollView;//上面的水平滚动控件
  private RadioGroup myRadioGroup;
  private LinearLayout layout,titleLayout;
  FragmentManager fm;
  FragmentTransaction ft;
  NewsFragment newsFragment;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.newsactivity);
    getTitleInfo();
    initGroup();
    fm = getFragmentManager();
    ft = fm.beginTransaction();
    newsFragment = new NewsFragment();
    ft.add(R.id.content,newsFragment);
    ft.show(newsFragment);
    ft.commit();
  }
  private List<Map<String, Object>> titleList = new ArrayList<Map<String,Object>>();
  private void getTitleInfo(){
    Map<String, Object> map = new HashMap<>();
    map.put("id", 0);
    map.put("title", "默认");
    titleList.add(map);
    map = new HashMap<>();
    map.put("id", 1);
    map.put("title", "社会");
    titleList.add(map);

    map = new HashMap<>();
    map.put("id", 2);
    map.put("title", "国内");
    titleList.add(map);

    map = new HashMap<>();
    map.put("id", 3);
    map.put("title", "国际");
    titleList.add(map);

    map = new HashMap<>();
    map.put("id", 4);
    map.put("title", "娱乐");
    titleList.add(map);

    map = new HashMap<>();
    map.put("id", 5);
    map.put("title", "体育");
    titleList.add(map);

    map = new HashMap<>();
    map.put("id", 6);
    map.put("title", "军事");
    titleList.add(map);

    map = new HashMap<>();
    map.put("id", 7);
    map.put("title", "科技");
    titleList.add(map);

    map = new HashMap<>();
    map.put("id", 8);
    map.put("title", "财经");
    titleList.add(map);
    map = new HashMap<>();
    map.put("id", 9);
    map.put("title", "时尚");
    titleList.add(map);
  }
  int _id = 1000;
  private void initGroup(){

    titleLayout =  findViewById(R.id.title_lay);
    layout =  findViewById(R.id.lay);

    //mImageView = new ImageView(this);

    mImageView = findViewById(R.id.img1);
    mHorizontalScrollView = findViewById(R.id.horizontalScrollView);


    myRadioGroup = new RadioGroup(this);
    myRadioGroup.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.MATCH_PARENT));
    myRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
    layout.addView(myRadioGroup);
    for (int i = 0; i <titleList.size(); i++) {
      Map<String, Object> map = titleList.get(i);
      RadioButton radio = new RadioButton(this);

      radio.setBackgroundResource(R.drawable.radiobtn_selector);
      radio.setButtonDrawable(android.R.color.transparent);
      LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);
      radio.setLayoutParams(l);
      radio.setGravity(Gravity.CENTER);
      radio.setPadding(20, 15, 20, 15);
      radio.setId(_id+i);
      //radio.setPadding(left, top, right, bottom)
      radio.setText(map.get("title")+"");
      radio.setTextColor(Color.WHITE);
      radio.setTag(map);
      if (i == 0) {
        radio.setChecked(true);
        int itemWidth = (int) radio.getPaint().measureText(map.get("title")+"");
        mImageView.setLayoutParams(new  LinearLayout.LayoutParams(itemWidth+radio.getPaddingLeft()+radio.getPaddingRight(),4));
      }
      myRadioGroup.addView(radio);
    }
    myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
          case 1000:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();

            break;
          case 1001:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=shehui&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1002:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=guonei&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1003:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=guoji&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1004:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=yule&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1005:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=tiyu&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1006:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=junshi&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1007:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=keji&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1008:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=caijing&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
          case 1009:
            new Thread(){
              public void run(){
                newsFragment.getData("http://v.juhe.cn/toutiao/index?"
                    + "type=shishang&key=2ca3a5b1cb6edf55250bff550ac34325");
              }
            }.start();
            break;
        }
        //Map<String, Object> map = (Map<String, Object>) group.getChildAt(checkedId).getTag();
        int radioButtonId = group.getCheckedRadioButtonId();
        //根据ID获取RadioButton的实例
        RadioButton rb = findViewById(radioButtonId);

        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation;
        translateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, rb.getLeft(), 0f, 0f);
        animationSet.addAnimation(translateAnimation);
        animationSet.setFillBefore(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(300);

        mImageView.startAnimation(animationSet);//开始上面蓝色横条图片的动画切换
        mCurrentCheckedRadioLeft = rb.getLeft();//更新当前蓝色横条距离左边的距离
        mHorizontalScrollView.smoothScrollTo((int)mCurrentCheckedRadioLeft-(int)getResources().getDimension(R.dimen.rdo2), 0);

        mImageView.setLayoutParams(new  LinearLayout.LayoutParams(rb.getRight()-rb.getLeft(),4));

      }
    });

  }
}
