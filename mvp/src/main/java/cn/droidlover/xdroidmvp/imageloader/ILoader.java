package cn.droidlover.xdroidmvp.imageloader;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

import cn.droidlover.xdroidmvp.XDroidConf;


/**
 * Created by XU on 2018/5/7.
 */


public interface ILoader {

    void init(Context context);

    void loadNet(ImageView target, String url, Options options);

    void loadNet(Context context, String url, Options options, LoadCallback callback);

    void loadResource(ImageView target, int resId, Options options);

    void loadAssets(ImageView target, String assetName, Options options);

    void loadFile(ImageView target, File file, Options options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    void resume(Context context);

    void pause(Context context);


    class Options {

        public int loadingResId = RES_NONE;
        public int loadErrorResId = RES_NONE;
        public ImageView.ScaleType scaleType = null;

        public static final int RES_NONE = -1;

        public static Options defaultOptions() {
            return new Options(XDroidConf.IL_LOADING_RES, XDroidConf.IL_ERROR_RES);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }

        public Options scaleType(ImageView.ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }
    }

}
