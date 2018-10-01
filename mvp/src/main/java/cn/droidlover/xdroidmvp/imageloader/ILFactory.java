package cn.droidlover.xdroidmvp.imageloader;


/**
 * Created by XU on 2018/5/7.
 */

public class ILFactory {

    private static ILoader loader;


    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (ILFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }


}
