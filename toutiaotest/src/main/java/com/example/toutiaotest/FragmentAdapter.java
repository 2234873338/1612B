package com.example.toutiaotest;

import static com.example.toutiaotest.R.id.imageView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by wangye on 2017/8/2.
 */

public class FragmentAdapter extends BaseAdapter {

  Context c;
  NewsBean newsBean;

  public FragmentAdapter(Context c, NewsBean newsBean) {
    this.c = c;
    this.newsBean = newsBean;
    DisplayImageOptions  options = new DisplayImageOptions.Builder()
        .showStubImage(R.mipmap.ic_launcher)// 加载开始默认的图片
        .showImageForEmptyUri(R.mipmap.ic_launcher)     //url爲空會显示该图片，自己放在drawable里面的
        .showImageOnFail(R.mipmap.ic_launcher)                //加载图片出现问题，会显示该图片
        .cacheInMemory()                                               //缓存用
        .cacheOnDisc()                                                  //缓存用
        .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
        .build();

    ImageLoaderConfiguration config2 = new ImageLoaderConfiguration.Builder(c)
        .defaultDisplayImageOptions(options)
        .threadPriority(Thread.NORM_PRIORITY - 2)
        .denyCacheImageMultipleSizesInMemory()
        .discCacheFileNameGenerator(new Md5FileNameGenerator())
        .tasksProcessingOrder(QueueProcessingType.LIFO)
        .enableLogging() //  1.8.6,把这句删除
        .build();
    ImageLoader.getInstance().init(config2);
  }

  @Override
  public int getCount() {
    if(newsBean == null){
      return 0;
    }
    return newsBean.getResult().getData().size();
  }

  @Override
  public Object getItem(int i) {
    return newsBean.getResult().getData().get(i);
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(final int i, View view, ViewGroup viewGroup) {
    final ViewHolder holder;
    if(view == null){
      view = View.inflate(c,R.layout.adapter,null);
      holder = new ViewHolder();
      holder.imageView = view.findViewById(R.id.imageLogo);
      holder.txTitle = view.findViewById(R.id.text_title);
      holder.txTime = view.findViewById(R.id.text_time);
      holder.txAuthor = view.findViewById(R.id.text_author);
      view.setTag(holder);
    }else{
      holder = (ViewHolder) view.getTag();
    }
    holder.txTitle.setText(newsBean.getResult().getData().get(i).getTitle());
    holder.txTime.setText(newsBean.getResult().getData().get(i).getDate());
    holder.txAuthor.setText(newsBean.getResult().getData().get(i).getAuthor_name());
    ImageLoader.getInstance().displayImage(
        newsBean.getResult().getData().get(i).getThumbnail_pic_s()
    ,holder.imageView);
//    new Thread(){
//      public void run(){
//        try {
//          URL url = new URL(newsBean.getResult().getData().get(i).getThumbnail_pic_s());
//          HttpURLConnection con = (HttpURLConnection) url.openConnection();
//          con.setRequestMethod("GET");
//          con.setReadTimeout(10000);
//          con.setConnectTimeout(10000);
//          con.connect();
//          if(con.getResponseCode() == 200) {
//            InputStream inputStream = con.getInputStream();
//            ByteArrayOutputStream bs = new ByteArrayOutputStream();
//            byte buffer[] = new byte[512];
//            int length;
//            while ((length = inputStream.read(buffer)) != -1) {
//              bs.write(buffer, 0, length);
//              bs.flush();
//            }
//            holder.imageView.setImageBitmap(BitmapFactory.
//                decodeByteArray(bs.toByteArray(),0,bs.toByteArray().length));
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//
//    }.start();

    return view;
  }
  class ViewHolder{
    private ImageView imageView;
    private TextView txTitle,txTime,txAuthor;
  }

  public void updateAdapter(NewsBean newsBean){
    this.newsBean = newsBean;
    notifyDataSetChanged();
    Log.d("ddd","刷新  刷新");
  }


}
