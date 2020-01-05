package com.luyucheng.weather;

import android.content.*;
import android.hardware.*;
import android.util.*;

import java.lang.ref.*;
import java.util.*;

public class OrientationSensorUtil {
    private static final String TAG = "OrientationSensorUtil";
    private static List<WeakReference<OrientationInfoListener>> mOrientationInfoListener = new ArrayList();
    private static Sensor mSensor = null;
    private static SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            OrientationSensorUtil.notifySensor(sensorEvent);
        }
    };
    private static SensorManager mSensorManager = null;

    public interface OrientationInfoListener {
        void onOrientationInfoChange(float f, float f2, float f3);
    }

    public static void addOrientationInfoListener(OrientationInfoListener orientationInfoListener) {
        mOrientationInfoListener.add(new WeakReference(orientationInfoListener));
    }

    private static void notifySensor(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        List<WeakReference> arrayList = new ArrayList();
        arrayList.addAll(mOrientationInfoListener);
        for (WeakReference weakReference : arrayList) {
            OrientationInfoListener orientationInfoListener = (OrientationInfoListener) weakReference.get();
            if (orientationInfoListener == null) {
                mOrientationInfoListener.remove(weakReference);
            } else {
                orientationInfoListener.onOrientationInfoChange(f, f2, f3);
            }
        }
    }

    public static void releaseSensor() {
        synchronized (OrientationSensorUtil.class) {
            try {
                Log.i(TAG, "releaseSensor");
                if (mSensorManager != null) {
                    mSensorManager.unregisterListener(mSensorEventListener, mSensor);
                }
                mSensorManager = null;
                mSensor = null;
                List<WeakReference<OrientationInfoListener>> obj
                        = mOrientationInfoListener;
                obj.clear();
            } finally {
                Class cls = OrientationSensorUtil.class;
            }
        }
    }

    public static void removeOrientationInfoListener(OrientationInfoListener orientationInfoListener) {
        for (WeakReference weakReference : mOrientationInfoListener) {
            if (weakReference.get() == orientationInfoListener) {
                mOrientationInfoListener.remove(weakReference);
                return;
            }
        }
    }

    public static void requestSensor(Context context) {
        synchronized (OrientationSensorUtil.class) {
            try {
                Log.i(TAG, "requestSensor");
                if (context != null && mSensorManager == null) {
                    mSensorManager = (SensorManager) context.getApplicationContext().getSystemService("sensor");
                    mSensor = mSensorManager.getDefaultSensor(3);
                    mSensorManager.registerListener(mSensorEventListener, mSensor, 1);
                }
            } catch (Throwable th) {
                Class cls = OrientationSensorUtil.class;
            }
        }
    }
} 
 

