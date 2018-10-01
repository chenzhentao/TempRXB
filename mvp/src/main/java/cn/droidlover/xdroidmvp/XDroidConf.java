package cn.droidlover.xdroidmvp;

import cn.droidlover.xdroidmvp.imageloader.ILoader;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;


public class XDroidConf {

    public static boolean LOG = true;
    public static String LOG_TAG = "RXB";

    public static String CACHE_SP_NAME = "config";
    public static String CACHE_DISK_DIR = "cache";

    public static int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static int ROUTER_ANIM_EXIT = Router.RES_NONE;

    public static int IL_LOADING_RES = ILoader.Options.RES_NONE;
    public static int IL_ERROR_RES = ILoader.Options.RES_NONE;

    public static boolean DEV = true;

    public static void configLog(boolean log, String logTag) {
        LOG = log;
        if (!Kits.Empty.check(logTag)) {
            LOG_TAG = logTag;
        }
    }

    public static void configCache(String spName, String diskDir) {
        if (!Kits.Empty.check(spName)) {
            CACHE_SP_NAME = spName;
        }
        if (!Kits.Empty.check(diskDir)) {
            CACHE_DISK_DIR = diskDir;
        }
    }

    public static void devMode(boolean isDev) {
        DEV = isDev;
    }

}
