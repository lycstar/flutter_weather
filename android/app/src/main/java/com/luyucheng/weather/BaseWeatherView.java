package com.luyucheng.weather;


import android.content.*;
import android.util.*;
import android.view.*;
import com.luyucheng.weather.OrientationSensorUtil.*;

public abstract class BaseWeatherView extends View  { 
    private boolean mDay; 
    private int mDayBackgroundColor; 
    protected OrientationInfoListener mListener; 
    private int mNightBackgroundColor; 

    public BaseWeatherView(Context context, AttributeSet attributeSet) { 
        super(context, attributeSet); 
        onCreateOrientationInfoListener(); 
    } 

    public BaseWeatherView(Context context, AttributeSet attributeSet, int i) { 
        super(context, attributeSet, i); 
        onCreateOrientationInfoListener(); 
    } 

    public BaseWeatherView(Context context, boolean z) { 
        super(context, null); 
        onCreateOrientationInfoListener(); 
        this.mDay = z; 
    } 

    public boolean isDay() { 
        return this.mDay; 
    } 

    protected void onAttachedToWindow() { 
        super.onAttachedToWindow(); 
        OrientationSensorUtil.addOrientationInfoListener(this.mListener); 
    } 

    protected abstract void onCreateOrientationInfoListener(); 

    protected void onDetachedFromWindow() { 
        OrientationSensorUtil.removeOrientationInfoListener(this.mListener); 
        super.onDetachedFromWindow(); 
    } 

    public void onViewPause() { 
        OrientationSensorUtil.removeOrientationInfoListener(this.mListener); 
    } 

    public void onViewStart() { 
        OrientationSensorUtil.addOrientationInfoListener(this.mListener); 
    } 

    public void setDay(boolean z) { 
        if (isDay() != z) { 
            this.mDay = z; 
        } 
    } 

    public void setDayBackgroundColor(int i) { 
        this.mDayBackgroundColor = i; 
    } 

    public void setNightBackgroundColor(int i) { 
        this.mNightBackgroundColor = i; 
    } 
} 
 

