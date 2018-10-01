package com.czt.temprxb.framework.base.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czt.temprxb.framework.view.FootView;
import com.czt.temprxb.framework.view.HeadView;
import com.czt.temprxb.framework.view.dialog.LoadingDialog;
import com.fingdo.statelayout.StateLayout;
import com.luck.picture.lib.permissions.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yan.pullrefreshlayout.PullRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.mvp.IView;
import cn.droidlover.xdroidmvp.mvp.VDelegate;
import cn.droidlover.xdroidmvp.mvp.VDelegateBase;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by XU on 2016/12/29.
 */

public abstract class XFragment extends RxFragment implements IView {

    private VDelegate vDelegate;

    protected Activity context;
    private View rootView;
    protected LayoutInflater layoutInflater;
    private View mRootView;
    protected StateLayout mStateLayout;
    protected LoadingDialog mLoadingDialog;
    protected PtrFrameLayout mRefreshLayout;
    protected PullRefreshLayout mPullRefreshLayout;
    private RxPermissions rxPermissions;

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        layoutInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }


        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initData(savedInstanceState);
        initLayout();
        setlistener();
        startRefresh(true);
    }
    private void initLayout() {
        if (getStateLayoutID() != 0) {
            mStateLayout = optainView(getStateLayoutID());
            if (mStateLayout != null) {
                mStateLayout.setUseAnimation(true);
                mStateLayout.setRefreshListener(new StateLayout.OnViewRefreshListener() {
                    @Override
                    public void refreshClick() {
                        startRefresh(true);
                    }

                    @Override
                    public void loginClick() {
                        //                        LoginActivity.startLoginActivity(getContext(), true);
                    }
                });
            }
        }
        if (getPullRefreshLayoutID() != 0) {
            mRefreshLayout = optainView(getPullRefreshLayoutID());
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
                });
            }
        }

        mLoadingDialog = new LoadingDialog(getContext());
        mLoadingDialog.setListener(new LoadingDialog.LoadingListener() {
            @Override
            public void showListener() {

            }

            @Override
            public void dismissListener() {

            }
        });
        if (getRecyerViewID() != 0) {
            mPullRefreshLayout = optainView(getRecyerViewID());
            if (mPullRefreshLayout != null) {
                ititRecyerView();
            }
        }

    }
    /**
     * 初始化recyerView 刷新布局
     */
    protected void ititRecyerView() {
        mPullRefreshLayout.setHeaderView(new HeadView(this.getContext()));
        mPullRefreshLayout.setFooterView(new FootView(this.getContext()));
        mPullRefreshLayout.setRefreshEnable(true);
        mPullRefreshLayout.setLoadMoreEnable(true);
    }
    /**
     * 刷新recyerview
     *
     * @return
     */
    protected int getRecyerViewID() {
        return 0;
    }
    protected <T extends View> T optainView(int resID) {
        return (T) mRootView.findViewById(resID);
    }

    protected abstract void setlistener();

    protected abstract int getStateLayoutID();

    protected abstract int getPullRefreshLayoutID();
    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }

        getvDelegate().destory();


        vDelegate = null;

    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(getActivity());
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

}
