package com.czt.temprxb.framework.view.myview;

import android.content.Context;
import android.util.AttributeSet;

import cn.droidlover.xdroidmvp.log.XLog;


/**
 * Created by {冯中萌} on 2017/4/19
 */

public class DelayedClickButton extends android.support.v7.widget.AppCompatButton {
   private int time ;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public DelayedClickButton(Context context) {
        super(context);
        time = 500;
    }

    public DelayedClickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        time = 500;
    }

    public DelayedClickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        time = 500;
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
