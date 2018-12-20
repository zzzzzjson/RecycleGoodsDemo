package soexample.umeng.com.day20.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {

    private final OkHttpClient okHttpClient;

    private OkHttpUtils(){
        okHttpClient = new OkHttpClient();
    }
    public static OkHttpUtils getInstance(){
        return OkHttpHolder.utils;
    }
    static class OkHttpHolder{
        private static final OkHttpUtils utils=new OkHttpUtils();
    }
    public void get(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
