package com.example.toutiaotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.ArrayList;

/**
 * Created by wangye on 2017/8/2.
 */

public class ViewPagerActivity extends Activity {
ViewPager viewPager;
  ArrayList<ImageView> arrayList = new ArrayList<>();
  int [] images = {R.drawable.vp1,R.drawable.vp2,R.drawable.vp3};
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.viewpageractivity);
    viewPager = findViewById(R.id.vp);
    for(int i = 0;i<3;i++){
      ImageView imageView = new ImageView(ViewPagerActivity.this);
      //适应图片原本的宽高
      imageView.setScaleType(ScaleType.FIT_XY);
      imageView.setImageResource(images[i]);
      arrayList.add(imageView);
    }
    viewPager.setAdapter(new PagerAdapter() {
      @Override
      public int getCount() {
        return arrayList.size();
      }

      @Override
      public boolean isViewFromObject(View view, Object object) {
        return view == object;
      }

      @Override
      public Object instantiateItem(ViewGroup container, int position) {
        container.addView(arrayList.get(position));
        return arrayList.get(position);
      }

      @Override
      public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(arrayList.get(position));
      }
    });
  }
}
