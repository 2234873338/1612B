package com.example.chinacalendar.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.chinacalendar.R;
import com.example.chinacalendar.bean.DateData;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Qiyan on 2017/8/28.
 */

public class DateFragment extends Fragment{
    Button submit;
    TextView yangli,yinli;
    String date;
    Context mContext;
    public DateFragment(Context c){
        mContext = c;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View v = View.inflate(getActivity(), R.layout.fragment_date,null);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate =  new Date();
        String year = formatter.format(curDate);
        yangli = (TextView) v.findViewById(R.id.yangli);
        yangli.setText(year);
        yinli = (TextView) v.findViewById(R.id.yinli);
        submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogPick();
            }
        });
        return v;
    }

    private void showDialogPick() {
        final StringBuffer time = new StringBuffer();
        //获取Calendar对象,用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override//
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                String d;
                String i3;
                String i4;
                if (i1<10&&i2<10){
                    i3 = "0"+(i1+1);
                    i4 = "0"+i2;
                    time.append(i+"-"+i3+"-"+i4);
                }else if(i1<10){
                    i3 = "0"+(i1+1);
                    time.append(i+"-"+i3+"-"+i2);
                }else if(i2<10){
                    i4 = "0"+i2;
                    time.append(i+"-"+(i1+1)+"-"+i4);
                }else{
                    time.append(i+"-"+(i1+1)+"-"+i2);
                }
                d = time.toString().trim();
                yangli.setText(d);
                date = yangli.getText().toString();
            }
        },year,month,day);
        //弹出选择日期对话框
        datePickerDialog.show();

    }

    public void updateUI(DateData data){
        DateData.ResultBean resultBean = data.getResult();
        String yangliData = resultBean.getYangli();
        String yinliData = resultBean.getYinli();
        yangli.setText(yangliData);
        yinli.setText(yinliData);
    }
    public String Date(){

        return date;
    }



}
