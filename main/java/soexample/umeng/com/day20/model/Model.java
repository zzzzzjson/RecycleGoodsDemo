package soexample.umeng.com.day20.model;

import soexample.umeng.com.day20.callback.MyCallBack;

public interface Model {
    void getData(String url, MyCallBack callBack);
}
