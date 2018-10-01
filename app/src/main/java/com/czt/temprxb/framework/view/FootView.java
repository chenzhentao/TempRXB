package com.czt.temprxb.framework.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.czt.temprxb.R;
import com.yan.pullrefreshlayout.PullRefreshLayout;
import com.yan.pullrefreshlayout.view.LoadView;

/**
 * Created by ${冯中萌} on 2016/6/6.
 */

public class FootView extends FrameLayout implements PullRefreshLayout.OnPullListener {
    public FootView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FootView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FootView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FootView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private TextView mTextView;
    private LoadView mLoadView;

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        addView(LayoutInflater.from(context).inflate(R.layout.pull_foodview, this, false));
        mTextView = (TextView) findViewById(R.id.pull_tvload);
        mLoadView = (LoadView) findViewById(R.id.pull_loodingview);
    }


    @Override
    public void onPullChange(float percent) {

    }

    @Override
    public void onPullReset() {
        mTextView.setText(R.string.shanglajiazaigengduo);
    }

    @Override
    public void onPullHoldTrigger() {
        mTextView.setText(R.string.shifangshuaxin);
    }

    @Override
    public void onPullHoldUnTrigger() {
        mTextView.setText(R.string.shanglajiazaigengduo);
    }

    @Override
    public void onPullHolding() {
        mLoadView.setVisibility(VISIBLE);
        mLoadView.startLoad();
    }

    @Override
    public void onPullFinish() {
        mLoadView.stopLoad();
        mLoadView.setVisibility(INVISIBLE);
        mTextView.setText(R.string.shuaxinwancheng);
    }
}
