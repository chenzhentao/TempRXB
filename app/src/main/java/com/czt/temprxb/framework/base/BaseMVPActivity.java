package com.czt.temprxb.framework.base;


import com.czt.temprxb.framework.base.layout.BaseActivity;
import cn.droidlover.xdroidmvp.mvp.XPresenter;


/**
 * Created by {冯中萌} on 2017/4/19.AutoLayoutActivity
 */

public abstract class BaseMVPActivity<V, A extends XPresenter<V>> extends BaseActivity {

    private A mPresenter;

    @Override
    protected void setPresenter() {
        super.setPresenter();
        mPresenter = mPresenterCreate();


        if (mPresenter == null) {
            throw new NullPointerException("请把管理者实现类new出来呦");
        }
        if (!mPresenter.isViewAttached()) {
            mPresenter.attachView((V) this);
        }
    }

    protected abstract A mPresenterCreate();

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
