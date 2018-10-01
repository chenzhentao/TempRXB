package cn.droidlover.xdroidmvp.cache;


/**
 * Created by XU on 2018/5/7.
 */

public interface ICache {
    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();

}
