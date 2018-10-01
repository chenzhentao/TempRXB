package com.yan.pullrefreshlayout.view.clickBtn;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * Created by ${王俊强} on 2017/6/2.
 */

public class DelayedClickLinearLayout extends LinearLayout {
    private long time;

    public void setTime(long time) {
        this.time = time;
    }

    public DelayedClickLinearLayout(Context context) {
        super(context);
        setTime(600L);
    }

    public DelayedClickLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTime(600L);
    }

    public DelayedClickLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTime(600L);
    }

    public DelayedClickLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTime(600L);
    }

    @Override
    public boolean performClick() {
        setClickable(false);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    setClickable(true);
                } catch (Exception e) {
                }
            }
        }, time);
        return super.performClick();
    }
}
