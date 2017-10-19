package com.example.chinacalendar.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.chinacalendar.R;
import com.example.chinacalendar.adapter.MoreAdapter;
import com.example.chinacalendar.tools.MyGridViewData;

/**
 * Created by Qiyan on 2017/8/29.
 */

public class MoreFragment extends Fragment {
    GridView gv;
    Context mContext;
    public MoreFragment(Context c){
        mContext = c;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = View.inflate(getActivity(), R.layout.fragment_more,null);
        gv = v.findViewById(R.id.gv);

        String[] texts = MyGridViewData.getMyGridViewTexts();
        int[] imgs = MyGridViewData.getMyGridViewImgs();

        gv.setAdapter(new MoreAdapter(mContext,texts,imgs));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
        return v;
    }
}
