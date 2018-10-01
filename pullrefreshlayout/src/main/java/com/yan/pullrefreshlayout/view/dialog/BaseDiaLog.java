package com.yan.pullrefreshlayout.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.MotionEvent;
import android.view.View;

import com.yan.pullrefreshlayout.R;


/**
 * Created by ${王俊强} on 2017/3/10.
 */

public abstract class BaseDiaLog extends Dialog {
    private @NonNull View view;

    public BaseDiaLog(@NonNull Context context) {
        super(context, R.style.dialogWindowAnim);
    }

    @Override
    public void show() {
        view = setView();
        super.show();
    }

    public BaseDiaLog(@NonNull Context context, @StyleRes int themeResId) {
        this(context);
    }

    protected BaseDiaLog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        this(context);
    }

    float startY;
    float moveY = 0;

    public abstract View setView();

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = ev.getY() - startY;
                view.scrollBy(0, -(int) moveY);
                startY = ev.getY();
                if (view.getScrollY() > 0) {
                    view.scrollTo(0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (view.getScrollY() < -this.getWindow().getAttributes().height / 4 && moveY > 0) {
                    this.dismiss();

                }
                view.scrollTo(0, 0);
                break;
        }
        return super.onTouchEvent(ev);
    }
}
