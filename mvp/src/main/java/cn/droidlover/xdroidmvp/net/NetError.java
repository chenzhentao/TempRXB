package cn.droidlover.xdroidmvp.net;

public class NetError extends Exception {
    private Throwable exception;
    private int type = NoConnectError;

    public static final int ParseError = 0;
    public static final int NoConnectError = 1;
    public static final int AuthError = 2;
    public static final int NoDataError = 3;
    public static final int BusinessError = 4;
    public static final int OtherError = 5;

    public NetError(Throwable exception, int type) {
        this.exception = exception;
        this.type = type;
    }

    public NetError(String detailMessage, int type) {
        super(detailMessage);
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (exception != null) return exception.getMessage();
        return super.getMessage();
    }

    public int getType() {
        return type;
    }
}
