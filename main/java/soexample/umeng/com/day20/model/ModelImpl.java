package soexample.umeng.com.day20.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.day20.bean.MyBean;
import soexample.umeng.com.day20.callback.MyCallBack;
import soexample.umeng.com.day20.utils.OkHttpUtils;

public class ModelImpl implements Model{
    private MyCallBack myCallBack;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= (String) msg.obj;
            Gson gson = new Gson();
            MyBean myBean = gson.fromJson(str, MyBean.class);
            myCallBack.success(myBean);
        }
    };
    @Override
    public void getData(String url, final MyCallBack callBack) {
        this.myCallBack=callBack;
        OkHttpUtils.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                callBack.error(e.getMessage());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               mHandler.sendMessage(mHandler.obtainMessage(0,response.body().string()));
            }
        });
    }
}
