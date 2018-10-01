package cn.droidlover.xdroidmvp.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * Created by XU on 2018/5/7.
 */


public interface IView<P> {
    void bindUI(View rootView);

    void initData(Bundle savedInstanceState);

    int getOptionsMenuId();

    int getLayoutId();

    boolean useEventBus();

    P newP();

    void startRefresh(boolean isShowLoadingView);//开始刷新数据

//    void onMessageEvent(EventBusMessage message);//接受消息

    void showLoodingView(Object object);//显示等待圈

    void showLoodingDialog(Object object);//显示等待圈

    void showContentView(Object object);//显示主页面

    void showErrorView(Object object);//显示加载失败

    void showEmptyView(Object object);//显示空信息

    void showNoNetworkView(Object object);//显示没有网络

    void showTimeErrorView(Object object);//超时界面

    void ToToast(String string);//显示土司

    void hideDiaLogView();//隐藏土司

    interface IRegisterActivityView extends IView{
        void registerSuccess(Object string);//注册成功

        void registerError(Object string);//注册失败
    }

    public interface IMySendActivityView extends IView{
        void getSuccess(Object string);
        void getError(Object string);
    }
}
