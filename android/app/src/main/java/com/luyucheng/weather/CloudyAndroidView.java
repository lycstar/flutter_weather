package com.luyucheng.weather;

import android.content.Context;
import android.view.View;

import java.util.Map;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.platform.PlatformView;

public class CloudyAndroidView implements PlatformView {
    private CloudyView cloudyView;

    CloudyAndroidView(final Context context,
                      BinaryMessenger messenger,
                      int id,
                      Map<String, Object> params,
                      View containerView) {
        cloudyView = new CloudyView(context, false);
        cloudyView.startAnimate();
    }

    @Override
    public View getView() {
        return cloudyView;
    }

    @Override
    public void dispose() {
        OrientationSensorUtil.releaseSensor();
        cloudyView = null;
    }
}