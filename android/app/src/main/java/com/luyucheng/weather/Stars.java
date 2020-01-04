package com.luyucheng.weather;

import android.content.*;
import android.graphics.*; 

public class Stars implements IViewShap { 
    public static final int CIRCLE_COUNT = 50; 
    private Circle[] mCircles; 
    private int mWHeight = 1920; 
    private int mWWidth = 1080; 

    class Circle { 
        public int CURRNET; 
        public Paint P; 
        public float R; 
        public int STEP = 120; 
        public float X; 
        public float Y; 

        Circle() { 
        } 

        public void next() { 
            this.CURRNET = (this.CURRNET + 1) % this.STEP; 
            if (this.CURRNET <= 60) { 
                this.P.setAlpha((int) ((((float) this.CURRNET) / 60.0f) * 100.0f)); 
            } else { 
                this.P.setAlpha((int) ((((float) (120 - this.CURRNET)) / 60.0f) * 100.0f)); 
            } 
        } 
    } 

    public void draw(Canvas canvas) { 
        for (int i = 0; i < this.mCircles.length; i++) { 
            canvas.drawCircle(this.mCircles[i].X, this.mCircles[i].Y, this.mCircles[i].R, this.mCircles[i].P); 
        } 
    } 

    public void init(Context context, int i, int i2) { 
        setWindowSize(i, i2); 
        BaseAnimation.setRange((float) this.mWWidth, (float) this.mWHeight, 0.0f); 
        this.mCircles = new Circle[50]; 
        for (int i3 = 0; i3 < this.mCircles.length; i3++) { 
            Paint paint = new Paint(); 
            paint.setColor(Color.rgb(255, 255, 255)); 
            this.mCircles[i3] = new Circle(); 
            float[] orginXY = StartAnimation.orginXY(); 
            this.mCircles[i3].X = orginXY[0]; 
            this.mCircles[i3].Y = orginXY[1]; 
            this.mCircles[i3].R = (float) UIUtil.dip2px(context, 1.0f); 
            this.mCircles[i3].P = paint; 
            this.mCircles[i3].CURRNET = (int) orginXY[2]; 
        } 
    } 

    public void next() { 
        for (Circle next : this.mCircles) { 
            next.next(); 
        } 
    } 

    public void setWindowSize(int i, int i2) { 
        this.mWWidth = i; 
        this.mWHeight = i2; 
    } 
} 
 

