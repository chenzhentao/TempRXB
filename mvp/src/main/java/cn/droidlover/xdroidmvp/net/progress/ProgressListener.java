package cn.droidlover.xdroidmvp.net.progress;


/**
 * Created by XU on 2018/5/7.
 */


public interface ProgressListener {
    void onProgress(long soFarBytes, long totalBytes);

    void onError(Throwable throwable);
}
