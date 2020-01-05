package com.luyucheng.weather;

import java.util.*;

public class StartAnimation extends BaseAnimation {
    public static float[] orginXY() {
        Random random = BaseAnimation.getRandom();
        float rangeX = (BaseAnimation.getRangeX() * ((float) random.nextInt(10000))) / 10000.0f;
        float rangeY = (BaseAnimation.getRangeY() * ((float) random.nextInt(10000))) / 10000.0f;
        float nextInt = (float) random.nextInt(120);
        BaseAnimation.nextSeed();
        return new float[]{rangeX, rangeY, nextInt};
    }

    public float[] next() {
        return null;
    }
} 
 

