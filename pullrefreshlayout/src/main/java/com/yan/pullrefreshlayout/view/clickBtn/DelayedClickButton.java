package com.yan.pullrefreshlayout.view.clickBtn;

import android.content.Context;
import android.util.AttributeSet;


/**
 * Created by ${王俊强} on 2017/4/11.
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
                }
            }
        }, time);
        return super.performClick();
    }
}
