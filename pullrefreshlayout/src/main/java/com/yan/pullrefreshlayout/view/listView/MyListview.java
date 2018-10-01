package com.yan.pullrefreshlayout.view.listView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by 王俊强 on 2016/8/19 0019.
 */
public class MyListview extends ListView {
    public MyListview(Context context) {
        super(context);
    }
    public MyListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyListview(Context context, AttributeSet attrs,
                      int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
