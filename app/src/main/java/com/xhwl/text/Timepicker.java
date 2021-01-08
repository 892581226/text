package com.xhwl.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class Timepicker extends TimePicker {

    private NumberPicker mMinuteSpinner;

    public Timepicker(Context context) {
        super(context);
    }

    public Timepicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("WrongViewCast")
    public Timepicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMinuteSpinner = (NumberPicker)findViewById(R.id.timepicker);
        mMinuteSpinner.setMinValue(0);
        mMinuteSpinner.setMaxValue(2);
        mMinuteSpinner.setDisplayedValues(new String[]{"0", "30", "60"});
        mMinuteSpinner.setOnLongPressUpdateInterval(100);

    }

    public Timepicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
