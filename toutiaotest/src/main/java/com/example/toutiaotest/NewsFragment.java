package com.example.toutiaotest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wangye on 2017/8/2.
 */

public class NewsFragment extends Fragment {

  NewsBean newsBean = null;
  FragmentAdapter adapter;
  ListView listView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = View.inflate(getActivity(), R.layout.newfragment, null);
    listView = view.findViewById(R.id.listview);

    adapter = new FragmentAdapter(getActivity(), newsBean);
    listView.setAdapter(adapter);
    new Thread() {
      public void run() {
        getData("http://v.juhe.cn/toutiao/index?"
            + "type=&key=2ca3a5b1cb6edf55250bff550ac34325");
      }
    }.start();

    return view;
  }

  public void getData(String path) {
    try {
      URL url = new URL(path);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setReadTimeout(10000);
      con.setConnectTimeout(10000);
      con.connect();
      if (con.getResponseCode() == 200) {
        InputStream inputStream = con.getInputStream();
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        byte buffer[] = new byte[512];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
          bs.write(buffer, 0, length);
          bs.flush();
        }
        Gson gson = new Gson();
        newsBean = gson.fromJson(bs.toString(), NewsBean.class);
        getActivity().runOnUiThread(new Thread() {
          public void run() {
                 adapter.updateAdapter(newsBean);
          }
        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
