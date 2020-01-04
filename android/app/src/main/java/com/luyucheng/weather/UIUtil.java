package com.luyucheng.weather;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.Build.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.WindowManager.*;
import android.widget.*;

import androidx.annotation.RequiresApi;

public class UIUtil
{ 

    public static class DragOnTouchListener implements OnTouchListener
	{ 
        private int currentSlop = 0; 
        private boolean dispatchTouchEvent; 
        private boolean enable = false; 
        private PointF mLastPoint = null; 
        private boolean mScrolling = false; 
        private int mTouchSlop = 5; 
        private OnDragListener onDragListener; 
        private View view; 

        public DragOnTouchListener(View view, OnDragListener onDragListener, boolean z)
		{ 
            this.onDragListener = onDragListener; 
            this.view = view; 
            this.dispatchTouchEvent = z; 
        } 

        public boolean onTouch(View view, final MotionEvent motionEvent)
		{ 
            if (!this.enable)
			{ 
                return false; 
            } 
            float y = motionEvent.getY(); 
            if (motionEvent.getAction() == 0)
			{ 
                this.mLastPoint = new PointF(motionEvent.getX(), motionEvent.getY()); 
            } 
            if (motionEvent.getAction() == 2)
			{ 
                if (this.mLastPoint == null)
				{ 
                    this.mLastPoint = new PointF(motionEvent.getX(), motionEvent.getY()); 
                } 
                if (this.mScrolling)
				{ 
                    this.view.post(new Runnable() { 
							public void run()
							{ 
								DragOnTouchListener.this.onDragListener.onDrag(DragOnTouchListener.this.view, motionEvent, OnDragListener. DragEvent.ACTION_DRAG, DragOnTouchListener.this.currentSlop);
							} 
						}); 
                }
				else
				{ 
                    y -= this.mLastPoint.y; 
                    this.currentSlop = (int) y; 
                    if (Math.abs(y) >= ((float) this.mTouchSlop) && Math.abs((int) (this.mLastPoint.x - motionEvent.getX())) < Math.abs((int) (this.mLastPoint.y - motionEvent.getY())))
					{ 
                        this.mScrolling = true; 
                        this.onDragListener.onDrag(this.view, motionEvent, OnDragListener. DragEvent.ACTION_START, this.currentSlop);
                    } 
                } 
            }
			else if (motionEvent.getAction() == 3 || motionEvent.getAction() == 1)
			{ 
                if (this.mScrolling)
				{ 
                    this.mScrolling = false; 
                    this.onDragListener.onDrag(this.view, motionEvent, OnDragListener. DragEvent.ACTION_END, this.currentSlop);
                    return true; 
                } 
                this.mLastPoint = null; 
            } 
            return !this.dispatchTouchEvent; 
        } 

        public void setDispatchTouchEvent(boolean z)
		{ 
            this.dispatchTouchEvent = z; 
        } 

        public void setEnable(boolean z)
		{ 
            this.enable = z; 
        } 
    } 

    public static class DragOnTouchListenerHolder
	{ 
        public DragOnTouchListener dragOnTouchListener; 
    } 

    public interface OnDragListener
	{ 

        public enum DragEvent
		{ 
            ACTION_START, 
            ACTION_DRAG, 
            ACTION_END 
			} 

        void onDrag(View view, MotionEvent motionEvent, DragEvent dragEvent, int i); 
    } 

    public static int dip2px(Context context, float f)
	{ 
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f); 
    } 

    public static Point getPos(View view)
	{ 
        int[] iArr = new int[2]; 
        view.getLocationOnScreen(iArr); 
        return new Point(iArr[0], iArr[1]); 
    } 

    @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenHeight(Context context)
	{ 
        WindowManager windowManager = (WindowManager) context.getSystemService("window"); 
        DisplayMetrics displayMetrics = new DisplayMetrics(); 
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics); 
        return displayMetrics.heightPixels; 
    } 

    @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenWidth(Context context)
	{ 
        WindowManager windowManager = (WindowManager) context.getSystemService("window"); 
        DisplayMetrics displayMetrics = new DisplayMetrics(); 
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics); 
        return displayMetrics.widthPixels; 
    } 

    public static View getViewByPosition(int i, ListView listView)
	{ 
        int firstVisiblePosition = listView.getFirstVisiblePosition(); 
        return (i < firstVisiblePosition || i > (listView.getChildCount() + firstVisiblePosition) - 1) ? listView.getAdapter().getView(i, listView.getChildAt(i), listView) : listView.getChildAt(i - firstVisiblePosition); 
    } 

    public static int getWindowHeight(Context context)
	{ 
        WindowManager windowManager = (WindowManager) context.getSystemService("window"); 
        DisplayMetrics displayMetrics = new DisplayMetrics(); 
        windowManager.getDefaultDisplay().getMetrics(displayMetrics); 
        return displayMetrics.heightPixels; 
    } 

    @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR1)
    public static int getWindowWidth(Context context)
	{ 
        WindowManager windowManager = (WindowManager) context.getSystemService("window"); 
        DisplayMetrics displayMetrics = new DisplayMetrics(); 
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics); 
        return displayMetrics.widthPixels; 
    } 

    public static boolean isLandscape(Context context)
	{ 
        return context.getResources().getConfiguration().orientation == 2; 
    } 

    public static int px2dip(Context context, float f)
	{ 
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f); 
    } 

    public static DragOnTouchListenerHolder setOnDragListener(View view, OnDragListener onDragListener, boolean z, boolean z2)
	{ 
        DragOnTouchListenerHolder dragOnTouchListenerHolder = new DragOnTouchListenerHolder(); 
        DragOnTouchListener dragOnTouchListener = new DragOnTouchListener(view, onDragListener, z); 
        dragOnTouchListener.setEnable(z2); 
        view.setOnTouchListener(dragOnTouchListener); 
        dragOnTouchListenerHolder.dragOnTouchListener = dragOnTouchListener; 
        if (!(view instanceof ListView))
		{ 
            view.setOnClickListener(null); 
        } 
        return dragOnTouchListenerHolder; 
    } 

//    public static void setSystemBar(Activity activity, int i)
//	{
//        if (VERSION.SDK_INT >= 19)
//		{
//            Window window = activity.getWindow();
//            LayoutParams attributes = window.getAttributes();
//            attributes.flags |= 67108864;
//            window.setAttributes(attributes);
//            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(activity);
//            systemBarTintManager.setStatusBarTintEnabled(true);
//            systemBarTintManager.setStatusBarTintResource(i);
//        }
//    }

    public static void setWindowStyle(Activity activity)
	{ 
        Window window = activity.getWindow(); 
        if (VERSION.SDK_INT >= 21)
		{ 
            window.clearFlags(201326592); 
            window.getDecorView().setSystemUiVisibility(1280); 
            window.addFlags(Integer.MIN_VALUE); 
            window.setStatusBarColor(0); 
            return; 
        } 
        activity.requestWindowFeature(1); 
        window.setFlags(1024, 1024); 
    } 
} 
 

