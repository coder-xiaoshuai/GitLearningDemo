package com.example.zhangshuai.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
