package com.luyucheng.weather;


import android.os.*;

import java.util.*;

public abstract class BaseAnimation {
    protected static int INDEX = 0;
    private static float mCenterX = 1.0f;
    private static float mCenterY = 2.0f;
    private static float mCenterZ = 1.0f;
    private static float mRangeX = 1.0f;
    private static float mRangeY = 2.0f;
    private static float mRangeZ;

    protected static Random getRandom() {
        nextSeed();
        return new Random(SystemClock.currentThreadTimeMillis() + ((long) INDEX));
    }

    public static float getRangeX() {
        return mRangeX;
    }

    public static float getRangeY() {
        return mRangeY;
    }

    public static float getRangeZ() {
        return mRangeZ;
    }

    protected static void nextSeed() {
        Random random = new Random();
        INDEX = random.nextInt(10) + INDEX;
    }

    public static float[] nextXYZ() {
        Random random = getRandom();
        float randomRange = randomRange(random, 0.0f, mRangeZ);
        float f = mCenterZ;
        float f2 = mRangeZ / 2.0f;
        f = randomRange(random, mCenterX, ((f + f2) - randomRange) * mRangeX);
        float randomRange2 = randomRange(random, mCenterY, mRangeY);
        f2 = mRangeZ;
        nextSeed();
        return new float[]{(f * 2.5f) / 5.0f, ((randomRange2 * 2.0f) + (f2 - randomRange)) / 5.0f, randomRange};
    }

    public static float[] orginXYZ() {
        Random random = getRandom();
        float randomRange = ((mCenterZ + (mRangeZ / 2.0f)) - randomRange(random, 0.0f, mRangeZ)) * mRangeX;
        float nextInt = (((float) random.nextInt(10000)) * randomRange) / 10000.0f;
        randomRange /= 2.0f;
        float f = mCenterX;
        float randomRange2 = randomRange(random, mCenterY, mRangeY);
        nextSeed();
        return new float[]{(((nextInt - randomRange) + f) * 2.5f) / 5.0f, randomRange2 * 4.0f, randomRange};
    }

    private static float randomRange(Random random, float f, float f2) {
        return (((((float) random.nextInt(10000)) * f2) / 10000.0f) - (f2 / 2.0f)) + f;
    }

    public static void setCenterXYZ(float f, float f2, float f3) {
        mCenterX = f;
        mCenterY = f2;
        mCenterZ = f3;
    }

    public static void setRange(float f, float f2, float f3) {
        mRangeX = f;
        mRangeY = f2;
        mRangeZ = f3;
    }

    public abstract float[] next();
} 
 

