package com.luyucheng.weather;

import android.content.*;
import android.graphics.*;
import android.util.*;

import java.util.*;


public class CloudyView extends BaseWeatherView {
    private static final int BACKGROUND_COLOR = Color.parseColor("#4a97d2");
    private static final int BACKGROUND_NIGHT_COLOR = Color.parseColor("#051325");
    private boolean mAnimate;
    private ArrayList<Cloud> mCloudList;
    private float mDeltaAngleY;
    private float mDeltaAngleZ;
    private Stars mStars;

    public CloudyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        init();
    }

    public CloudyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCloudList = new ArrayList();
        this.mAnimate = false;
        this.mDeltaAngleZ = 0.0f;
        this.mDeltaAngleY = 0.0f;
        init();
    }

    public CloudyView(Context context, boolean z) {
        super(context, z);
        this.mCloudList = new ArrayList();
        this.mAnimate = false;
        this.mDeltaAngleZ = 0.0f;
        this.mDeltaAngleY = 0.0f;
        init();
        setDayBackgroundColor(BACKGROUND_COLOR);
        setNightBackgroundColor(BACKGROUND_NIGHT_COLOR);
    }

    private void init() {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 250.0f), UIUtil.dip2px(getContext(), 93.0f), UIUtil.dip2px(getContext(), 634.0f), UIUtil.dip2px(getContext(), 50.0f), Color.rgb(255, 210, 0)).setAlpha(204).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 400.0f)).setRadiusScale(1.0f).setStep(60).setLevel(4).setHeightRadio(0.0f).setNightColor(Color.rgb(251, 255, 192)).setDensity(f).setNightAlpha(255));
        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 333.0f), UIUtil.dip2px(getContext(), 73.0f), UIUtil.dip2px(getContext(), 602.0f), UIUtil.dip2px(getContext(), 91.0f), Color.rgb(255, 255, 255)).setAlpha(102).setAnimation(true).setRadiusScale(1.1f).setStep(120).setLevel(4).setMaxY((float) UIUtil.dip2px(getContext(), 400.0f)).setHeightRadio(0.8f).setNightColor(Color.rgb(25, 48, 78)).setDensity(f).setNightAlpha(102));
        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 184.0f), -UIUtil.dip2px(getContext(), 16.0f), UIUtil.dip2px(getContext(), 600.0f), UIUtil.dip2px(getContext(), 158.0f), Color.rgb(255, 255, 255)).setAlpha(102).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 400.0f)).setRadiusScale(1.1f).setStep(120).setLevel(4).setHeightRadio(0.8f).setNightColor(Color.rgb(40, 74, 118)).setDensity(f).setNightAlpha(102));
        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 333.0f), -UIUtil.dip2px(getContext(), 200.0f), UIUtil.dip2px(getContext(), 600.0f), UIUtil.dip2px(getContext(), 133.0f), Color.rgb(255, 255, 255)).setAlpha(102).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 366.0f)).setRadiusScale(1.1f).setStep(120).setLevel(6).setHeightRadio(0.8f).setNightColor(Color.rgb(48, 87, 130)).setDensity(f).setNightAlpha(102));
        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 300.0f), -UIUtil.dip2px(getContext(), 116.0f), UIUtil.dip2px(getContext(), 400.0f), UIUtil.dip2px(getContext(), 200.0f), Color.rgb(255, 255, 255)).setAlpha(102).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 366.0f)).setRadiusScale(1.1f).setStep(120).setLevel(4).setHeightRadio(0.8f).setNightColor(Color.rgb(40, 74, 118)).setDensity(f).setNightAlpha(178));
        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 96.0f), -UIUtil.dip2px(getContext(), 86.0f), UIUtil.dip2px(getContext(), 598.0f), UIUtil.dip2px(getContext(), 200.0f), Color.rgb(255, 255, 255)).setAlpha(153).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 333.0f)).setRadiusScale(1.1f).setStep(200).setLevel(4).setHeightRadio(0.8f).setNightColor(Color.rgb(39, 71, 107)).setDensity(f).setNightAlpha(178));
        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), (float) (-UIUtil.dip2px(getContext(), 11.0f))), -UIUtil.dip2px(getContext(), 110.0f), UIUtil.dip2px(getContext(), 300.0f), UIUtil.dip2px(getContext(), 200.0f), Color.rgb(255, 255, 255)).setAlpha(102).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 333.0f)).setRadiusScale(1.1f).setStep(140).setLevel(5).setHeightRadio(0.8f).setNightColor(Color.rgb(12, 35, 65)).setDensity(f).setNightAlpha(102));
//        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 297.0f), -UIUtil.dip2px(getContext(), 162.0f), UIUtil.dip2px(getContext(), 200.0f), UIUtil.dip2px(getContext(), 216.0f), Color.rgb(255, 255, 255)).setAlpha(102).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 366.0f)).setRadiusScale(1.1f).setStep(WeatherCircleView.START_ANGEL_180).setLevel(6).setHeightRadio(0.25f).setNightColor(Color.rgb(57, 103, 142)).setDensity(f).setNightAlpha(153));
//        this.mCloudList.add(new Cloud(UIUtil.dip2px(getContext(), 10.0f), -UIUtil.dip2px(getContext(), 230.0f), UIUtil.dip2px(getContext(), 100.0f), UIUtil.dip2px(getContext(), 216.0f), Color.rgb(255, 255, 255)).setAlpha(153).setAnimation(true).setMaxY((float) UIUtil.dip2px(getContext(), 366.0f)).setRadiusScale(1.1f).setStep(160).setLevel(5).setHeightRadio(0.3f).setNightColor(Color.rgb(69, 122, 156)).setDensity(f).setNightAlpha(204));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mAnimate) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.mCloudList.size()) {
                    break;
                }
                Cloud cloud = (Cloud) this.mCloudList.get(i2);
                cloud.setHeight(getHeight());
                cloud.setWidth(getWidth());
                cloud.setDay(isDay());
                cloud.updateRoatationInfo(0.0f, this.mDeltaAngleY, this.mDeltaAngleZ);
                cloud.draw(canvas);
                i = i2 + 1;
            }
            if (!isDay()) {
                if (this.mStars == null) {
                    this.mStars = new Stars();
                    this.mStars.init(getContext(), getWidth(), (getHeight() * 2) / 3);
                }
                this.mStars.next();
                this.mStars.draw(canvas);
            }
            invalidate();
        }
    }

    protected void onCreateOrientationInfoListener() {
        this.mListener = (f, f2, f3) -> {
            CloudyView.this.mDeltaAngleZ = f3;
            if (f2 < 0.0f) {
                CloudyView.this.mDeltaAngleY = -f2;
            } else {
                CloudyView.this.mDeltaAngleY = f2;
            }
        };
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void startAnimate() {
        this.mAnimate = true;
        invalidate();
    }

    public void stopAnimate() {
        this.mAnimate = false;
    }
} 
 

