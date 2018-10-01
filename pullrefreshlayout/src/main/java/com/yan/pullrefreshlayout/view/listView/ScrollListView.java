package com.yan.pullrefreshlayout.view.listView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;


/**
 * Created by ${王俊强} on 2017/2/24.
 */

public class ScrollListView extends ListView {
    private int mYmaxOverScrollY = 50;

    public ScrollListView(Context context) {
        this(context, null, 0);
    }

    public ScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    /**
     * 设置 mYmaxOverScrollY 根据屏幕的不同分辨率
     */
    private void init() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float density = metrics.density;
        mYmaxOverScrollY = (int) density * mYmaxOverScrollY;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                   int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, mYmaxOverScrollY, isTouchEvent);
    }
}
