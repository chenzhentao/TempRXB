package com.czt.temprxb.framework.base.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.czt.temprxb.R;
import com.czt.temprxb.framework.util.ActivityCollector;
import com.czt.temprxb.framework.util.NetworkUtil;
import com.czt.temprxb.framework.util.To;
import com.czt.temprxb.framework.view.FootView;
import com.czt.temprxb.framework.view.HeadView;
import com.czt.temprxb.framework.view.dialog.LoadingDialog;
import com.czt.temprxb.utils.StatusBarCompat;
import com.fingdo.statelayout.StateLayout;
import com.luck.picture.lib.permissions.RxPermissions;
import com.yan.pullrefreshlayout.PullRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.mvp.IView;
import cn.droidlover.xdroidmvp.mvp.VDelegate;
import cn.droidlover.xdroidmvp.mvp.VDelegateBase;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public abstract class BaseActivity extends AutoLayoutActivity implements IView {

    private VDelegate vDelegate;

    protected Activity context;
    private LoadingDialog mLoadingDialog;
    private StateLayout mStateLayout;
    private RxPermissions rxPermissions;
    private PtrFrameLayout mRefreshLayout;
    private Unbinder unbinder;
    protected PullRefreshLayout mPullRefreshLayout;
    private View statusBarView;
    protected int statusBarColor = 0;//状态栏颜色

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        boolean booleanss = beforeWindow(savedInstanceState);
        if (booleanss) {
            finish();
            return;
        }

        context = this;
        if (getLayoutId() > 0) {
            setContentView();
            bindUI(null);
        }
        setPresenter();
        initDialog();
        initData(savedInstanceState);
        setListener();
    }


    protected abstract int getStateLayoutID();

    protected abstract int getPullRefreshLayoutID();
    protected void setPresenter() {

    }
    /**
     * 初始化view
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T optionView(int id) {
        return (T) super.findViewById(id);
    }
    protected abstract void setListener();
    /**
     * 初始化 加载对话框
     */
    private void initDialog() {
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setListener(new LoadingDialog.LoadingListener() {
            @Override
            public void showListener() {
                showLoadingListener();
            }

            @Override
            public void dismissListener() {
                hideLoadingListener();
            }
        });
        if (getStateLayoutID() != 0) {
            mStateLayout = optionView(getStateLayoutID());
        }
        if (mStateLayout != null) {
            mStateLayout.setUseAnimation(true);
            mStateLayout.setRefreshListener(new StateLayout.OnViewRefreshListener() {
                @Override
                public void refreshClick() {
                    startRefresh(true);
                }

                @Override
                public void loginClick() {

                    //                    LoginActivity.startLoginActivity(BaseActivity.this, true);
                }
            });
        }
        if (getPullRefreshLayoutID() != 0) {
            mRefreshLayout = optionView(getPullRefreshLayoutID());
            if (mRefreshLayout != null) {
                mRefreshLayout.setResistance(1.7F);//阻力
                mRefreshLayout.setRatioOfHeaderHeightToRefresh(1.2F);//标头的高度与触发刷新的比例
                mRefreshLayout.setDurationToClose(300);//从您将视图相对位置移动到标题高度的持续时间为默认值
                mRefreshLayout.setDurationToCloseHeader(1000);//关闭标题的持续时间
                mRefreshLayout.setKeepHeaderWhenRefresh(true);//在刷新时保持标题
                mRefreshLayout.setPullToRefresh(false);//拉动就刷新还是释放后刷新
                mRefreshLayout.setLoadingMinTime(500);//最小等待时间|
                mRefreshLayout.setPtrHandler(new PtrDefaultHandler() {
                    @Override
                    public void onRefreshBegin(PtrFrameLayout frame) {
                        startRefresh(false);
                    }

                    @Override
                    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                        return super.checkCanDoRefresh(frame, content, header);
                    }
                });
            }
        }
        if (getRecyerViewLayoutID() != 0) {
            mPullRefreshLayout = optionView(getRecyerViewLayoutID());
            if (mPullRefreshLayout != null) {
                initRecyerViewLayout();
            }
        }

    }
    /**
     * 设置recyerview layout 刷新布局参数
     */
    protected void initRecyerViewLayout() {
        mPullRefreshLayout.setHeaderView(new HeadView(this));
        mPullRefreshLayout.setFooterView(new FootView(this));
        mPullRefreshLayout.setRefreshEnable(true);
        mPullRefreshLayout.setLoadMoreEnable(true);
    }

    /**
     * 是否使用列表刷新布局
     *
     * @return
     */
    protected int getRecyerViewLayoutID() {
        return 0;
    }
    /**
     * 对话框显示了
     */
    protected void showLoadingListener() {

    }

    /**
     * 对话框被用户消失了
     */
    protected void hideLoadingListener() {
        finish();
    }

    /**
     * 显示加载对话框
     */
    protected void showDialog(String msage) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            if (TextUtils.isEmpty(msage)) {
                mLoadingDialog.setText("Loading......");
            } else {
                mLoadingDialog.setText(msage);
            }
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideDiaLogView() {
        hideDialog();
    }

    /**
     * 显示加载对话框
     */
    protected void hideDialog() {
        if (mLoadingDialog == null) {
            return;
        }
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.refreshComplete();
        }
        if (mPullRefreshLayout != null) {
            mPullRefreshLayout.refreshComplete();
            mPullRefreshLayout.loadMoreComplete();
        }
    }


    /**
     * 隐藏软键盘
     */
    protected void hidekeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
    private void setContentView() {
        ActivityCollector.addActivity(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setContentView(getLayoutId());
        if (statusBarColor == 0) {
            statusBarView = StatusBarCompat.compat(this,
                    ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else if (statusBarColor != -1) {
            statusBarView = StatusBarCompat.compat(this, statusBarColor);
        }
    }


    protected boolean beforeWindow(Bundle savedInstanceState) {
        return false;
    }

    public static boolean isForeground = false;

    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }

        if (!NetworkUtil.isNetworkAvailable(this)) {
            showSetNetworkUI(this);
        }
    }

    /*
     * 打开设置网络界面
     */
    public void showSetNetworkUI(final Context context) {
        // 提示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示")
                .setMessage("网络连接不可用,是否进行设置?")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Intent intent = null;
                        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
                        if (android.os.Build.VERSION.SDK_INT > 10) {
                            intent = new Intent(
                                    android.provider.Settings.ACTION_WIFI_SETTINGS);
                        } else {
                            intent = new Intent();
                            ComponentName component = new ComponentName(
                                    "com.android.settings",
                                    "com.android.settings.WirelessSettings");
                            intent.setComponent(component);
                            intent.setAction("android.intent.action.VIEW");
                        }
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
        getvDelegate().pause();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }

        vDelegate = null;

    }

    public void showMessage(String message) {
        //        ToastUtil.showShort(context, message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }


    @Override
    public void showLoodingView(Object object) {
        hideDialog();
        if (mStateLayout != null) {
            if (object != null) {
                mStateLayout.showLoadingView((String) object);
            } else {
                mStateLayout.showLoadingView();
            }
        }
    }

    @Override
    public void showLoodingDialog(Object object) {
        showDialog((String) object);
    }

    @Override
    public void showContentView(Object object) {
        hideDialog();
        if (mStateLayout != null) {
            mStateLayout.showContentView();
        }

    }

    @Override
    public void showErrorView(Object object) {
        hideDialog();
        if (mStateLayout != null) {
            if (object != null) {
                mStateLayout.showErrorView((String) object);
            } else {
                mStateLayout.showErrorView();
            }
        } else {
            To.ee(object);
        }
    }

    @Override
    public void showEmptyView(Object object) {
        hideDialog();
        if (mStateLayout != null) {
            if (object != null) {
                mStateLayout.showEmptyView((String) object);
            } else {
                mStateLayout.showEmptyView();
            }
        } else {
            To.ee(object);
        }
    }



    /**
     * 显示登陆对话框
     */
    private void showLoginDiaLog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("您的登陆已过期，为了您的安全，请您重新登陆");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //                LoginActivity.startLoginActivity(getContext(), true);
            }
        });
        builder.show();
    }

    @Override
    public void showNoNetworkView(Object object) {
        hideDialog();
        if (mStateLayout != null) {
            if (object != null) {
                mStateLayout.showNoNetworkView((String) object);
            } else {
                mStateLayout.showNoNetworkView();
            }
        } else {
            To.ee(object);
        }
    }

    @Override
    public void showTimeErrorView(Object object) {
        hideDialog();
        if (mStateLayout != null) {
            if (object != null) {
                mStateLayout.showTimeoutView((String) object);
            } else {
                mStateLayout.showTimeoutView();
            }
        } else {
            To.ee(object);
        }
    }

    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }

    @Override
    public void ToToast(String string) {
        hideDialog();
        if (!TextUtils.isEmpty(string)) {
            To.oo(string);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String message) {

    }
}
