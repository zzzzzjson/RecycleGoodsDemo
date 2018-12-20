package soexample.umeng.com.day20.callback;

public interface MyCallBack<T> {
    void success(T user);
    void error(T error);
}
