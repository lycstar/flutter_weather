package com.luyucheng.weather;


import android.graphics.*; 

public class Cloud
{ 
    private static final float MAX_SPEED = 1.0f; 
    private static final int MAX_TIME = 30; 
    private final float OFFSET_RATIO = 0.5f; 
    private float mAcceleration = 0.001f; 
    private boolean mAnim = true; 
    private boolean mDay = true; 
    private float mDensity = 1.0f; 
    private int mHeight; 
    private boolean mInit = false; 
    private int mLevel; 
    private float mMaxHRadio = 0.4f; 
    private float mMaxRadiusScale = 1.1f; 
    private float mMaxY = 0.0f; 
    private float mMoveSpeedX; 
    private float mMoveSpeedY; 
    private Paint mNightPaint = new Paint(); 
    private long mOldTime = 0; 
    private Paint mPaint = new Paint(); 
    private float mRXRotate; 
    private float mRYRotate; 
    private float mRadius = 0.0f; 
    private float mRadiusNoise = 0.003f; 
    private float mRadiusNoiseRate = 1.0f; 
    private float mRadiusScale = 1.0f; 
    private int mRadiusStep = 120; 
    private float mScaleZ; 
    private int mWidth; 
    private float mX = 0.0f; 
    private float mXRotate = 0.0f; 
    private float mY = 0.0f; 
    private float mYRotate = 0.0f; 
    private int mZ = 0; 

    public Cloud(int i, int i2, int i3, int i4, int i5)
	{ 
        this.mX = (float) i; 
        this.mY = (float) i2; 
        this.mRadius = (float) i4; 
        this.mZ = i3; 
        this.mPaint.setAntiAlias(true); 
        this.mPaint.setColor(i5); 
        this.mNightPaint.setAntiAlias(true); 
        this.mScaleZ = i3 == 0 ? 1.0f : ((float) (2500 - i3)) / 2500.0f; 
        this.mRadiusNoise = 1.0f; 
        this.mRadiusScale = 1.0f; 
    } 

    public boolean draw(Canvas canvas)
	{ 
        if (!this.mInit)
		{ 
            this.mRXRotate = this.mXRotate; 
            this.mRYRotate = this.mYRotate; 
            this.mInit = true; 
        } 
        if (this.mAnim)
		{ 
            long currentTimeMillis = System.currentTimeMillis(); 
            int i = this.mOldTime == 0 ? 0 : (int) (currentTimeMillis - this.mOldTime); 
            this.mOldTime = currentTimeMillis; 
            if (i > 30)
			{ 
                i = 30; 
            } 
            float abs = Math.abs(this.mRXRotate - this.mXRotate); 
            float abs2 = Math.abs(this.mRYRotate - this.mYRotate); 
            if (this.mMoveSpeedX < 1.0f)
			{ 
                this.mMoveSpeedX += this.mAcceleration * ((float) i); 
            }
			else
			{ 
                this.mMoveSpeedX -= this.mAcceleration * ((float) i); 
            } 
            if (this.mMoveSpeedX < 0.0f)
			{ 
                this.mMoveSpeedX = this.mAcceleration; 
            } 
            if (abs < this.mDensity * 150.0f)
			{ 
                this.mMoveSpeedX = Math.min(this.mMoveSpeedX, (1.0f * abs) / (this.mDensity * 150.0f)); 
            } 
            float f = (this.mMoveSpeedX * ((float) i)) * this.mDensity; 
            if (abs > f)
			{ 
                if (this.mRXRotate > this.mXRotate)
				{ 
                    this.mRXRotate -= f; 
                }
				else
				{ 
                    this.mRXRotate += f; 
                } 
            } 
            if (this.mMoveSpeedY < 1.0f)
			{ 
                this.mMoveSpeedY += this.mAcceleration * ((float) i); 
            }
			else
			{ 
                this.mMoveSpeedY -= this.mAcceleration * ((float) i); 
            } 
            if (this.mMoveSpeedY < 0.0f)
			{ 
                this.mMoveSpeedY = this.mAcceleration; 
            } 
            if (abs2 < this.mDensity * 150.0f)
			{ 
                this.mMoveSpeedY = Math.min(this.mMoveSpeedY, (1.0f * abs2) / (this.mDensity * 150.0f)); 
            } 
            float f2 = (((float) i) * this.mMoveSpeedY) * this.mDensity; 
            if (abs2 > f2)
			{ 
                if (this.mRYRotate > this.mYRotate)
				{ 
                    this.mRYRotate -= f2; 
                }
				else
				{ 
                    this.mRYRotate = f2 + this.mRYRotate; 
                } 
            } 
            if (this.mRadiusScale >= this.mMaxRadiusScale)
			{ 
                this.mRadiusNoise = (-(this.mMaxRadiusScale - 1.0f)) / ((float) this.mRadiusStep); 
                this.mRadiusNoiseRate = 0.01f; 
            }
			else if (this.mRadiusScale <= 1.0f)
			{ 
                this.mRadiusNoise = (this.mMaxRadiusScale - 1.0f) / ((float) this.mRadiusStep); 
                this.mRadiusNoiseRate = 0.01f; 
            } 
            if (this.mRadiusNoiseRate >= 1.0f)
			{ 
                this.mRadiusNoiseRate = 1.0f; 
            }
			else
			{ 
                this.mRadiusNoiseRate += 0.05f; 
            } 
            this.mRadiusScale += this.mRadiusNoise * this.mRadiusNoiseRate; 
        } 
        Paint paint = this.mPaint; 
        if (!isDay())
		{ 
            paint = this.mNightPaint; 
        } 
        canvas.drawCircle(this.mX + this.mRXRotate, this.mY + this.mRYRotate, getRadius(), paint); 
        return true; 
    } 

    public float getRadius()
	{ 
        return this.mRadius * this.mRadiusScale; 
    } 

    public boolean isDay()
	{ 
        return this.mDay; 
    } 

    public Cloud setAlpha(int i)
	{ 
        this.mPaint.setAlpha(i); 
        return this; 
    } 

    public Cloud setAnimation(boolean z)
	{ 
        this.mAnim = z; 
        return this; 
    } 

    public void setDay(boolean z)
	{ 
        this.mDay = z; 
    } 

    public Cloud setDensity(float f)
	{ 
        this.mDensity = f; 
        return this; 
    } 

    public Cloud setHeight(int i)
	{ 
        this.mHeight = i; 
        return this; 
    } 

    public Cloud setHeightRadio(float f)
	{ 
        this.mMaxHRadio = f; 
        return this; 
    } 

    public Cloud setLevel(int i)
	{ 
        this.mLevel = i; 
        return this; 
    } 

    public Cloud setMaxY(float f)
	{ 
        this.mMaxY = f; 
        return this; 
    } 

    public Cloud setNightAlpha(int i)
	{ 
        this.mNightPaint.setAlpha(i); 
        return this; 
    } 

    public Cloud setNightColor(int i)
	{ 
        this.mNightPaint.setColor(i); 
        return this; 
    } 

    public Cloud setRadiusScale(float f)
	{ 
        this.mMaxRadiusScale = f; 
        return this; 
    } 

    public Cloud setStep(int i)
	{ 
        this.mRadiusStep = i; 
        return this; 
    } 

    public void setWidth(int i)
	{ 
        this.mWidth = i; 
    } 

    public void updateRoatationInfo(float f, float f2, float f3)
	{ 
        this.mYRotate = (800.0f * (f2 / 90.0f)) + ((float) (((double) ((f3 / 90.0f) * (((float) (this.mWidth / 2)) - this.mX))) * 0.5d)); 
        this.mYRotate *= this.mDensity * 0.5f; 
        this.mXRotate = ((float) ((((double) (500.0f * (f3 / 90.0f))) * 0.5d) * ((double) this.mDensity))) * 0.5f; 
        this.mYRotate = (float) (((double) this.mYRotate) * (((double) this.mLevel) * 0.1d)); 
        this.mYRotate += ((float) ((10 - this.mLevel) * 4)) * this.mDensity; 
        this.mXRotate += ((float) ((10 - this.mLevel) * 4)) * this.mDensity; 
        float radius = getRadius(); 
        if (this.mMaxY > 0.0f && (this.mY + this.mYRotate) + radius > this.mMaxY)
		{ 
            this.mYRotate = (this.mMaxY - this.mY) - radius; 
        } 
    } 
} 
 

