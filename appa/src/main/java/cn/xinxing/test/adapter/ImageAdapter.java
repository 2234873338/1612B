package cn.xinxing.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import cn.xinxing.test.R;
import cn.xinxing.test.http.ImageLoader;

/**
 * 图片适配器
 */
public class ImageAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private Context mContext;
    public ImageLoader mImageLoader;//图片处理类，包含图片缓存 下载等
    private int mStart;// 第一张可见图片的下标
    private int mEnd;// 最后一张可见图片的下标
    public static String[] URLS;//图片下载路径集合
    private boolean mFirstIn;//记录是否刚打开程序

    public ImageAdapter(Context context, String[] data, ListView listView) {
        URLS = data;
        mContext = context;
        mImageLoader = new ImageLoader(listView);
        mFirstIn = true;
        listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return URLS.length;
    }

    @Override
    public Object getItem(int position) {
        return URLS[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolader holader = null;
        if (convertView == null) {
            holader = new ViewHolader();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_layout, null);
            convertView.setTag(holader);
        } else {
            holader = (ViewHolader) convertView.getTag();
        }
        holader.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
        holader.iv.setImageResource(R.mipmap.ic_launcher);
        String imageUrl = URLS[position];
        holader.iv.setTag(imageUrl);
        mImageLoader.showImage(holader.iv, imageUrl);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            //加载可见项
            mImageLoader.showIamges(mStart, mEnd);
        } else {
            // 停止任务
            mImageLoader.cancelAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        //第一次显示时候调用
        if (mFirstIn && visibleItemCount > 0) {
            mImageLoader.showIamges(mStart, mEnd);
            mFirstIn = false;
        }
    }

    class ViewHolader {
        public ImageView iv;
    }

}
