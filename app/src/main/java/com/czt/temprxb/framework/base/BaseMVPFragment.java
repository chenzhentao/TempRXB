package com.czt.temprxb.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czt.temprxb.framework.base.layout.XFragment;
import cn.droidlover.xdroidmvp.mvp.XPresenter;


/**
 *  Created by {冯中萌} on 2017/4/19
 */

public abstract class BaseMVPFragment<V, A extends XPresenter<V>> extends XFragment {
    private A mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = mPresenterCreate();
        if (!mPresenter.isViewAttached()) {
            mPresenter.attachView((V) this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract A mPresenterCreate();

    @Override
    public void onDetach() {
        mPresenter.detachView();
        super.onDetach();
    }

}
