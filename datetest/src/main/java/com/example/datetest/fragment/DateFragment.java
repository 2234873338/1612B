package com.example.datetest.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.datetest.R;
import com.example.datetest.activity.DateActivity;
import com.example.datetest.bean.DateData;

/**
 * Created by Qiyan on 2017/8/8.
 */

public class DateFragment extends Fragment{
    Button submit;
    EditText date;
    TextView yangli,yinli;
    String data;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = View.inflate(getActivity(), R.layout.fragment_date,null);
        submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d;
                if(date.getText()!=null&&date.getText().toString()!=null) {
                    d = date.getText().toString().trim();
                    data = d;
                }else{
                    d = "2017-01-01";
                    data = d;
                }
            }
        });
        date = (EditText) v.findViewById(R.id.date);
        yangli = (TextView) v.findViewById(R.id.yangli);
        yinli = (TextView) v.findViewById(R.id.yinli);
        return v;
    }

    public void updateUI(DateData data){
        DateData.ResultBean resultBean = data.getResult();
        String yangliData = resultBean.getYangli();
        String yinliData = resultBean.getYinli();
        yangli.setText(yangliData);
        yinli.setText(yinliData);
    }
    public String Date(){

        return data;
    }


}
