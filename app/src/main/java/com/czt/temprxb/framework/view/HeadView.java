package com.czt.temprxb.framework.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.czt.temprxb.R;
import com.yan.pullrefreshlayout.PullRefreshLayout;
import com.yan.pullrefreshlayout.view.LoadView;

/**
 *  Created by {冯中萌} on 2017/4/19
 */

public class HeadView extends FrameLayout implements PullRefreshLayout.OnPullListener {


    private TextView mTextView;
    private TextView mTextViewTime;
    private ImageView mImageViewJIanTou;
    private ImageView mImageViewSuccess;
    private LoadView mLoadView;
    private final int ROTATE_ANIM_DURATION = 180;

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    public HeadView(Context context) {
        super(context);
        init();
    }

    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        addView(LayoutInflater.from(getContext()).inflate(R.layout.refresh_view, this, false));
        mTextView = (TextView) findViewById(R.id.pullre_tvtitle);
        mTextViewTime = (TextView) findViewById(R.id.pull_re_tvtime);
        mImageViewJIanTou = (ImageView) findViewById(R.id.pullre_jiantou);
        mImageViewSuccess = (ImageView) findViewById(R.id.pullre_success);
        mLoadView = (LoadView) findViewById(R.id.pullre_loadingview);


        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);

        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    @Override
    public void onPullChange(float percent) {

    }

    @Override
    public void onPullReset() {
        mTextView.setText("下拉刷新");
        mImageViewSuccess.setVisibility(INVISIBLE);
        mImageViewJIanTou.setVisibility(VISIBLE);
        refreshUpdatedAtValue(mTextViewTime);
    }

    @Override
    public void onPullHoldTrigger() {
        mTextView.setText("释放刷新");
        mImageViewJIanTou.clearAnimation();
        mImageViewJIanTou.startAnimation(mRotateUpAnim);
    }

    @Override
    public void onPullHoldUnTrigger() {
        mTextView.setText("下拉刷新");
        mImageViewJIanTou.clearAnimation();
        mImageViewJIanTou.startAnimation(mRotateDownAnim);
    }

    @Override
    public void onPullHolding() {
        mTextView.setText("正在刷新");
        mLoadView.startLoad();
        mLoadView.setVisibility(VISIBLE);
        mImageViewJIanTou.clearAnimation();
        mImageViewJIanTou.setVisibility(INVISIBLE);
    }

    @Override
    public void onPullFinish() {
        mTextView.setText("刷新完成");
        mLoadView.stopLoad();
        mLoadView.setVisibility(INVISIBLE);
        mImageViewSuccess.setVisibility(VISIBLE);
        SharedPreferences.Editor editor = preferences.edit();//获取编辑器
        editor.putLong(UPDATED_AT + mId, System.currentTimeMillis());
        editor.commit();//提交修改
    }


    /**
     * 刷新下拉头中上次更新时间的文字描述。
     */
    public void refreshUpdatedAtValue(TextView textView) {

        lastUpdateTime = preferences.getLong(UPDATED_AT + mId, -1);
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdateTime;
        long timeIntoFormat;
        String updateAtValue;
        if (lastUpdateTime == -1) {
            updateAtValue = getResources().getString(R.string.not_updated_yet);
        } else if (timePassed < 0) {
            updateAtValue = getResources().getString(R.string.time_error);
        } else if (timePassed < ONE_MINUTE) {
            updateAtValue = getResources().getString(R.string.updated_just_now);
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updateAtValue = String.format(getResources().getString(R.string.updated_at),

                    value);
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时";
            updateAtValue = String.format(getResources().getString(R.string.updated_at),

                    value);
        } else if (timePassed < ONE_MONTH) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "天";
            updateAtValue = String.format(getResources().getString(R.string.updated_at),

                    value);
        } else if (timePassed < ONE_YEAR) {
            timeIntoFormat = timePassed / ONE_MONTH;
            String value = timeIntoFormat + "个月";
            updateAtValue = String.format(getResources().getString(R.string.updated_at),

                    value);
        } else {
            timeIntoFormat = timePassed / ONE_YEAR;
            String value = timeIntoFormat + "年";
            updateAtValue = String.format(getResources().getString(R.string.updated_at),

                    value);
        }
        textView.setText(updateAtValue);
    }


    /**
     * 一分钟的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    private SharedPreferences preferences;

    /**
     * 上次更新时间的毫秒值
     */
    private long lastUpdateTime;
    /**
     * 为了防止不同界面的下拉刷新在上次更新时间上互相有冲突，使用id来做区分
     */
    private int mId = -1;

    /**
     * 上次更新时间的字符串常量，用于作为SharedPreferences的键值
     */
    private static final String UPDATED_AT = "updated_at";
}
