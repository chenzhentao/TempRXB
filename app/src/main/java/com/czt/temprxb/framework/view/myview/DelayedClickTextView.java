package com.czt.temprxb.framework.view.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import cn.droidlover.xdroidmvp.log.XLog;


/**
 *  Created by {冯中萌} on 2017/4/19
 */

public class DelayedClickTextView extends android.support.v7.widget.AppCompatTextView {

    public void setTime(long time) {
        this.time = time;
    }

    private long time;


    public DelayedClickTextView(Context context) {
        super(context);
        setTime(600L);
    }

    public DelayedClickTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTime(600L);
    }

    public DelayedClickTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
                    XLog.e("连续点击按钮触发异常");
                }
            }
        }, time);
        return super.performClick();
    }


}
