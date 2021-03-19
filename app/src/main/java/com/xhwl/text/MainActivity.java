package com.xhwl.text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.xhwl.mylibrary.text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout image_back;
    private TextView tv_title;
    private ImageView image_scan;
    private ListView list_item;
    private boolean getUdpdataFlag=false;
    private String proto;
    private String gw_model_id;
    private List<String> udpDatas=new ArrayList<>();
    private String gateWayId;
    private String modelId;
    private String msg1;
    private int time=0;
    private  ArrayList<String> msgList;
    private TextView tv;
    private TimePicker mTimepicker;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View inflate = LayoutInflater.from(this).inflate(R.layout.set_time_new_wind,null,false);
        mPopupWindow = new PopupWindow(inflate,  ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.set_new_wind);
        text text = new text();

        tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
            }
        });
        msgList = new ArrayList<>();
        msgList.add("msg");
        mTimepicker = findViewById(R.id.timepicker);
        mTimepicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);  //设置点击事件不弹键盘
        mTimepicker.setIs24HourView(true);   //设置时间显示为24小时
        mTimepicker.setHour(0);  //设置当前小时
        mTimepicker.setMinute(30); //设置当前分（0-59）
        mTimepicker.setOnTimeChangedListener(mStartTimeChangedListener );

    }


    private TimePicker.OnTimeChangedListener mStartTimeChangedListener  =
            new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    Date startData = new Date();
                    updateDisplay(view, startData, hourOfDay, minute);

                }
            };
    private TimePicker.OnTimeChangedListener mNullTimeChangedListener =
            new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                }
            };

    private void updateDisplay(TimePicker timePicker, Date date, int hourOfDay, int minute) {

        // do calculation of next time
        int nextMinute = 0;
        if (minute >= 31 && minute <= 59)
            nextMinute = 60;
        else if(minute >0&&minute<=30)
            nextMinute = 30;
        else if(minute == 0)
            nextMinute = 0;


        // remove ontimechangedlistener to prevent stackoverflow/infinite loop
        timePicker.setOnTimeChangedListener(mNullTimeChangedListener);

        // set minute
        timePicker.setCurrentHour(hourOfDay);
        timePicker.setCurrentMinute(nextMinute);
        Log.e("TAG", "updateDisplay: "+hourOfDay+"--"+nextMinute );
        // hook up ontimechangedlistener again
        timePicker.setOnTimeChangedListener(mStartTimeChangedListener);
        // update the date variable for use elsewhere in code
        date.setMinutes(nextMinute);
        date.setHours(hourOfDay);
    }
}