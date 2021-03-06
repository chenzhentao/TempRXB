package cn.droidlover.xdroidmvp.event;


/**
 * Created by XU on 2018/5/7.
 */


public class BusProvider {

    private static RxBusImpl bus;

    public static RxBusImpl getBus() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = RxBusImpl.get();
                }
            }
        }
        return bus;
    }

}
