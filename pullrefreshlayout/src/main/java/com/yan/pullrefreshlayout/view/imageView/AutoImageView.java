package com.yan.pullrefreshlayout.view.imageView;

import android.content.Context;
import android.util.AttributeSet;


/**
 * Created by ${王俊强} on 2017/1/9.
 */
public class AutoImageView extends android.support.v7.widget.AppCompatImageView {
    public AutoImageView(Context context) {
        super(context);
    }

    public AutoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
