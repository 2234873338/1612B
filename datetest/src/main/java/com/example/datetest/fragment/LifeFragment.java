package com.example.datetest.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.datetest.R;
import com.example.datetest.bean.DateData;

/**
 * Created by Qiyan on 2017/8/8.
 */

public class LifeFragment extends Fragment{
    TextView chongsha,jishen,baiji,wuxing;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = View.inflate(getActivity(), R.layout.fragment_life,null);
        chongsha = (TextView) v.findViewById(R.id.chongsha1);
        jishen = (TextView) v.findViewById(R.id.jishen1);
        baiji = (TextView) v.findViewById(R.id.baiji1);
        wuxing = (TextView) v.findViewById(R.id.wuxing1);
        return v;
    }
    public void updateUI(DateData data){
        DateData.ResultBean resultBean = data.getResult();
        String chongshaData = resultBean.getChongsha();
        String jishenData = resultBean.getJishen();
        String baijiData = resultBean.getBaiji();
        String wuxingData = resultBean.getWuxing();

        chongsha.setText(chongshaData);
        jishen.setText(jishenData);
        baiji.setText(baijiData);
        wuxing.setText(wuxingData);
    }
}
