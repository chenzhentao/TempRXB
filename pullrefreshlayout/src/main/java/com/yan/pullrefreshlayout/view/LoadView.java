package com.yan.pullrefreshlayout.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.AttributeSet;

import com.yan.pullrefreshlayout.R;

import java.lang.ref.SoftReference;


/**
 * Created by ${冯中萌} on 2016/6/6.
 */
public class LoadView extends android.support.v7.widget.AppCompatImageView {
    private MyRunable runnable;
    private int width;
    private int height;

    public LoadView(Context context) {
        super(context);
        init();
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        runnable = null;
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.loading);
        setImageBitmap(bitmap);
        width = bitmap.getWidth() / 2;
        height = bitmap.getHeight() / 2;
        runnable = new MyRunable(this);
    }

    public void startLoad() {
        if (runnable != null) {
            runnable.startload();
        }
    }

    public void stopLoad() {
        if (runnable != null) {
            runnable.stopload();
        }
    }

    static class MyRunable implements Runnable {
        private boolean flag;
        private SoftReference<LoadView> loadingViewSoftReference;
        private float degrees = 0f;
        private Matrix max;

        public MyRunable(LoadView loadingView) {
            loadingViewSoftReference = new SoftReference<LoadView>(loadingView);
            max = new Matrix();
        }

        @Override
        public void run() {
            if (loadingViewSoftReference.get().runnable != null && max != null) {
                degrees += 30f;
                max.setRotate(degrees, loadingViewSoftReference.get().width, loadingViewSoftReference.get().height);
                loadingViewSoftReference.get().setImageMatrix(max);
                if (degrees == 360) {
                    degrees = 0;
                }
                if (flag) {
                    loadingViewSoftReference.get().postDelayed(loadingViewSoftReference.get().runnable, 80);
                }
            }
        }

        public void stopload() {
            flag = false;
        }

        public void startload() {
            if (flag) {
                return;
            }
            flag = true;
            if (loadingViewSoftReference.get().runnable != null && max != null) {
                loadingViewSoftReference.get().postDelayed(loadingViewSoftReference.get().runnable, 80);
            }
        }
    }
}