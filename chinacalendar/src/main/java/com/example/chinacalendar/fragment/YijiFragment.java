package com.example.chinacalendar.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chinacalendar.R;
import com.example.chinacalendar.bean.DateData;

/**
 * Created by Qiyan on 2017/8/28.
 */

public class YijiFragment extends Fragment {
    TextView xiong,yi,ji;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = View.inflate(getActivity(), R.layout.fragment_yiji,null);
        xiong = (TextView) v.findViewById(R.id.xiong);
        yi = (TextView) v.findViewById(R.id.yi);
        ji = (TextView) v.findViewById(R.id.ji);
        return v;
    }
    public void updateUI(DateData data){
        DateData.ResultBean resultBean = data.getResult();
        String xiongData = resultBean.getXiongshen();
        String yiData = resultBean.getYi();
        String jiData = resultBean.getJi();
        xiong.setText(xiongData);
        yi.setText(yiData);
        ji.setText(jiData);
    }
}
