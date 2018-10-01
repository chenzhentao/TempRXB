package com.yan.pullrefreshlayout.view.listView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class MyListview_E extends ExpandableListView {
    public MyListview_E(Context context) {
        super(context);
    }
    public MyListview_E(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyListview_E(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
