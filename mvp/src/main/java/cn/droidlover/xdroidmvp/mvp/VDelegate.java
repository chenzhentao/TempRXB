package cn.droidlover.xdroidmvp.mvp;

import android.view.View;


/**
 * Created by XU on 2018/5/7.
 */


public interface VDelegate {
    void resume();

    void pause();

    void destory();

    void visible(boolean flag, View view);
    void gone(boolean flag, View view);
    void inVisible(View view);

    void toastShort(String msg);
    void toastLong(String msg);
}
