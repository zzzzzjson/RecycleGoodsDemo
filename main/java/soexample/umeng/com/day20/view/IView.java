package soexample.umeng.com.day20.view;

public interface IView<T> {
    void success(T user);
    void error(T error);
}
