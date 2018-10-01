package cn.droidlover.xdroidmvp.mvp;


import com.google.gson.Gson;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by {冯中萌} on 2017/6/6.
 */

public abstract class XPresenter<T>{

    protected Gson mGson;

    public XPresenter(Gson mGson) {

        this.mGson = mGson;
    }

    protected Reference<T> mTReference;//view 接口类型弱引用

    public void attachView(T view) {
        mTReference = new WeakReference<T>(view);//建立关联
    }

    protected T getView() {
        if(mTReference == null){
            return null;
        }
        return mTReference.get();
    }

    public boolean isViewAttached() {
        return mTReference != null && mTReference.get() != null;
    }

    public void detachView() {
        if (mTReference != null) {
            mTReference.clear();
            mTReference = null;
        }
    }

    
}
