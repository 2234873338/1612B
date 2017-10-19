package com.bc.lkh.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class NewsAdapter extends BaseAdapter {
    Context con;
    News news;
    DisplayImageOptions options;

    public NewsAdapter(Context c, News n) {
        con = c;
        news = n;
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.mipmap.ic_launcher)             //加载图片出现问题，会显示该图片
                .cacheInMemory()                                  //缓存用
                .cacheOnDisc()                                   //缓存用
                .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                c)
                .memoryCacheExtraOptions(480, 800)// 缓存在内存的图片的宽和高度
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024) //缓存到内存的最大数据
                .discCacheSize(50 * 1024 * 1024).  //缓存到文件的最大数据
                discCacheFileCount(1000)            //文件数量
                .defaultDisplayImageOptions(options).  //上面的options对象，一些属性配置
                build();
        ImageLoader.getInstance().init(config); //初始化
    }

    @Override

    public int getCount() {
        return news.getResult().getData().size();
    }

    @Override
    public Object getItem(int i) {
        return news.getResult().getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MyHolder holder;
        if (view == null) {
            holder = new MyHolder();
            view = View.inflate(con, R.layout.adapter, null);
            holder.img = view.findViewById(R.id.img);
            holder.title = view.findViewById(R.id.title);
            holder.from = view.findViewById(R.id.from);
            holder.date = view.findViewById(R.id.date);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(news.getResult().getData().get(i).getThumbnail_pic_s());
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    con.setRequestMethod("GET");
//                    con.setConnectTimeout(10000);
//                    con.setReadTimeout(10000);
//                    con.connect();
//                    if (con.getResponseCode() == 200) {
//                        InputStream inputStream = con.getInputStream();
//                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                        byte[] buffer = new byte[512];
//                        int length;
//                        while ((length = inputStream.read(buffer)) != -1) {
//                            bs.write(buffer, 0, length);
//                            bs.flush();
//                        }
//                        //将字节信息转换成图片
//
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(bs.toByteArray(), 0, bs.toByteArray().length);
//                        holder.img.setImageBitmap(bitmap);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        ImageLoader.getInstance().displayImage(
                news.getResult().getData().get(i).getThumbnail_pic_s(),
                holder.img, options);
        holder.title.setText(news.getResult().getData().get(i).getTitle());
        holder.from.setText(news.getResult().getData().get(i).getAuthor_name());
        holder.date.setText(news.getResult().getData().get(i).getDate());
        return view;
    }

    class MyHolder {
        ImageView img;
        TextView title, from, date;
    }
}
