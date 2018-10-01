package com.yan.pullrefreshlayout.view.clickBtn;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by ${王俊强} on 2017/4/11.
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
                }
            }
        }, time);
        return super.performClick();
    }


}
