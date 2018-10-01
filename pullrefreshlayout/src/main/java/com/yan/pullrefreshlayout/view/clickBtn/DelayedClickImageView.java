package com.yan.pullrefreshlayout.view.clickBtn;

import android.content.Context;
import android.util.AttributeSet;


/**
 * Created by ${王俊强} on 2017/4/11.
 */

public class DelayedClickImageView extends android.support.v7.widget.AppCompatImageView {
    private long time;

    public void setTime(long time) {
        this.time = time;
    }

    public DelayedClickImageView(Context context) {
        super(context);
        setTime(600L);
    }

    public DelayedClickImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTime(600L);
    }

    public DelayedClickImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
                }
            }
        }, time);
        return super.performClick();
    }
}
