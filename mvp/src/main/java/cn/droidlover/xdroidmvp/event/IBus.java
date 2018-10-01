package cn.droidlover.xdroidmvp.event;


/**
 * Created by XU on 2018/5/7.
 */


public interface IBus {

    void register(Object object);
    void unregister(Object object);
    void post(IEvent event);
    void postSticky(IEvent event);


    interface IEvent {
        int getTag();
    }

}
