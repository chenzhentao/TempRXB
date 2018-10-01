package cn.droidlover.xdroidmvp.net;


/**
 * Created by XU on 2018/5/7.
 */

public interface IModel {
    boolean isNull();

    boolean isAuthError();

    boolean isBizError();

    String getErrorMsg();
}
