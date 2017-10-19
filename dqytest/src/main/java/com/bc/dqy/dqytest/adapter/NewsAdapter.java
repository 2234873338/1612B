package com.bc.dqy.dqytest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.dqy.dqytest.R;
import com.bc.dqy.dqytest.bean.NewsBean;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by Qiyan on 2017/10/17.
 */

public class NewsAdapter extends BaseAdapter{
    Context context;
    NewsBean newsBean;
    public NewsAdapter(Context c,NewsBean n){
        this.context = c;
        this.newsBean = n;
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory().cacheOnDisc().displayer(new RoundedBitmapDisplayer(10)).build();
        ImageLoaderConfiguration config2 = new ImageLoaderConfiguration.Builder(c)
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config2);
    }
    public int getCount(){
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(context,R.layout.adapter_news,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.imageLogo);
            viewHolder.txTitle = view.findViewById(R.id.text_title);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txTitle.setText(newsBean.getResult().getData().get(i).getTitle());
        ImageLoader.getInstance().displayImage(
                newsBean.getResult().getData().get(i).getThumbnail_pic_s()
                , viewHolder.imageView);
        return view;
    }
    class ViewHolder{
        private ImageView imageView;
        private TextView txTitle;
    }
    public void updateAdapter(NewsBean newsBean){
        this.newsBean = newsBean;
        notifyDataSetChanged();
    }
}
