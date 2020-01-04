import 'dart:core';

import 'dart:ui';
import 'dart:math' as math;

import 'package:flutter/material.dart';

class Cloud extends CustomPainter {
  double MAX_SPEED = 1.0;

  int MAX_TIME = 30;

  double OFFSET_RATIO;

  double mAcceleration;

  bool mAnim;

  bool mDay;

  double mDensity;

  int mHeight;

  bool mInit;

  int mLevel;

  double mMaxHRadio;

  double mMaxRadiusScale;

  double mMaxY;

  double mMoveSpeedX = 1.0;

  double mMoveSpeedY = 1.0;

  Paint mNightPaint;

  int mOldTime;

  Paint mPaint;

  double mRXRotate;

  double mRYRotate;

  double mRadius;

  double mRadiusNoise;

  double mRadiusNoiseRate;

  double mRadiusScale;

  int mRadiusStep;

  double mScaleZ;

  int mWidth;

  double mX;

  double mXRotate;

  double mY;

  double mYRotate;

  double mZ;

  Cloud(double x, double y, double z, double radius) {
    double f;
    this.mMaxRadiusScale = 1.1;
    this.OFFSET_RATIO = 0.5;
    this.mX = 0.0;
    this.mY = 0.0;
    this.mZ = 0;
    this.mRadiusScale = 1.0;
    this.mRadiusStep = 120;
    this.mRadiusNoise = 0.003;
    this.mRadiusNoiseRate = 1.0;
    this.mXRotate = 0.0;
    this.mYRotate = 0.0;
    this.mRadius = 0.0;
    this.mAnim = true;
    this.mMaxHRadio = 0.4;
    this.mOldTime = 0;
    this.mAcceleration = 0.001;
    this.mInit = false;
    this.mDay = true;
    this.mDensity = 1.0;
    this.mPaint = new Paint();
    this.mNightPaint = new Paint();
    this.mMaxY = 0.0;
    this.mX = x;
    this.mY = y;
    this.mRadius = radius;
    this.mZ = z;
    this.mPaint..isAntiAlias = true;
    this.mPaint..color = Colors.red;
    this.mNightPaint..isAntiAlias = true;
    if (z.toInt() == 0) {
      f = 1.0;
    } else {
      f = (2500 - z) / 2500.0;
    }
    this.mScaleZ = f;
    this.mRadiusNoise = 1.0;
    this.mRadiusScale = 1.0;
  }

////  bool draw(Canvas paramCanvas) {
////
////  }
//
  double getRadius() {
    return this.mRadius * this.mRadiusScale;
  }

//
  bool isDay() {
    return this.mDay;
  }

//
////  Cloud setAlpha(int paramInt) {
////    this.mPaint.setAlpha(paramInt);
////    return this;
////  }
//
//  Cloud setAnimation(bool paramBool) {
//    this.mAnim = paramBool;
//    return this;
//  }
//
//  void setDay(bool paramBool) {
//    this.mDay = paramBool;
//  }
//
//  Cloud setDensity(double paramdouble) {
//    this.mDensity = paramdouble;
//    return this;
//  }
//
//  Cloud setHeight(int paramInt) {
//    this.mHeight = paramInt;
//    return this;
//  }
//
//  Cloud setHeightRadio(double paramdouble) {
//    this.mMaxHRadio = paramdouble;
//    return this;
//  }
//
//  Cloud setLevel(int paramInt) {
//    this.mLevel = paramInt;
//    return this;
//  }
//
//  Cloud setMaxY(double paramdouble) {
//    this.mMaxY = paramdouble;
//    return this;
//  }
//
////  Cloud setNightAlpha(int paramInt) {
////    this.mNightPaint.setAlpha(paramInt);
////    return this;
////  }
//
////  Cloud setNightColor(int paramInt) {
////    this.mNightPaint..color = paramInt;
////    return this;
////  }
//
////  Cloud setRadiusScale(double paramdouble) {
////    this.mMaxRadiusScale = paramdouble;
////    return this;
////  }
////
////  Cloud setStep(int paramInt) {
////    this.mRadiusStep = paramInt;
////    return this;
////  }
////
////  void setWidth(int paramInt) {
////    this.mWidth = paramInt;
////  }

//  void updateRoatationInfo(
//      double paramdouble1, double paramdouble2, double paramdouble3) {
//    this.mYRotate = 800.0 * paramdouble2 / 90.0 +
//        ((paramdouble3 / 90.0 * ((this.mWidth / 2) - this.mX)) * 0.5);
//    this.mYRotate *= this.mDensity * 0.5;
//    this.mXRotate = ((500.0 * paramdouble3 / 90.0) * 0.5 * this.mDensity) * 0.5;
//    this.mYRotate = (this.mYRotate * this.mLevel * 0.1);
//    this.mYRotate += ((10 - this.mLevel) * 4) * this.mDensity;
//    this.mXRotate += ((10 - this.mLevel) * 4) * this.mDensity;
//    paramdouble1 = getRadius();
//    if (this.mMaxY > 0.0 && this.mY + this.mYRotate + paramdouble1 > this.mMaxY)
//      this.mYRotate = this.mMaxY - this.mY - paramdouble1;
//  }

  @override
  void paint(Canvas canvas, Size size) {
    if (!this.mInit) {
      this.mRXRotate = this.mXRotate;
      this.mRYRotate = this.mYRotate;
      this.mInit = true;
    }
    if (this.mAnim) {
      int i;
      int l = new DateTime.now().millisecondsSinceEpoch;
      if (this.mOldTime.toInt() == 0) {
        i = 0;
      } else {
        i = (l - this.mOldTime);
      }
      this.mOldTime = l;
      int j = i;
      if (i > 30) j = 30;
      double f1 = (this.mRXRotate - this.mXRotate).abs();
      double f2 = (this.mRYRotate - this.mYRotate).abs();
      if (this.mMoveSpeedX < 1.0) {
        this.mMoveSpeedX += this.mAcceleration * j;
      } else {
        this.mMoveSpeedX -= this.mAcceleration * j;
      }
      if (this.mMoveSpeedX < 0.0) this.mMoveSpeedX = this.mAcceleration;
      if (f1 < 150.0 * this.mDensity)
        this.mMoveSpeedX =
            math.min(this.mMoveSpeedX, 1.0 * f1 / 150.0 * this.mDensity);
      double f3 = this.mMoveSpeedX * j * this.mDensity;
      if (f1 > f3) if (this.mRXRotate > this.mXRotate) {
        this.mRXRotate -= f3;
      } else {
        this.mRXRotate += f3;
      }
      if (this.mMoveSpeedY < 1.0) {
        this.mMoveSpeedY += this.mAcceleration * j;
      } else {
        this.mMoveSpeedY -= this.mAcceleration * j;
      }
      if (this.mMoveSpeedY < 0.0) this.mMoveSpeedY = this.mAcceleration;
      if (f2 < 150.0 * this.mDensity)
        this.mMoveSpeedY =
            math.min(this.mMoveSpeedY, 1.0 * f2 / 150.0 * this.mDensity);
      f1 = this.mMoveSpeedY * j * this.mDensity;
      if (f2 > f1) if (this.mRYRotate > this.mYRotate) {
        this.mRYRotate -= f1;
      } else {
        this.mRYRotate += f1;
      }
      if (this.mRadiusScale >= this.mMaxRadiusScale) {
        this.mRadiusNoise = -(this.mMaxRadiusScale - 1.0) / this.mRadiusStep;
        this.mRadiusNoiseRate = 0.01;
      } else if (this.mRadiusScale <= 1.0) {
        this.mRadiusNoise = (this.mMaxRadiusScale - 1.0) / this.mRadiusStep;
        this.mRadiusNoiseRate = 0.01;
      }
      if (this.mRadiusNoiseRate >= 1.0) {
        this.mRadiusNoiseRate = 1.0;
      } else {
        this.mRadiusNoiseRate += 0.05;
      }
      this.mRadiusScale += this.mRadiusNoise * this.mRadiusNoiseRate;
    }
    Paint paint = this.mPaint;
    if (!isDay()) paint = this.mNightPaint;
    canvas.drawCircle(
      new Offset(this.mX + this.mRXRotate, this.mY + this.mRYRotate),
      getRadius(),
      paint,
    );
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return true;
  }
}
