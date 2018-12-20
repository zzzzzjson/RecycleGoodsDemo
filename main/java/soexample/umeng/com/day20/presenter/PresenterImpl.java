package soexample.umeng.com.day20.presenter;

import soexample.umeng.com.day20.callback.MyCallBack;
import soexample.umeng.com.day20.model.ModelImpl;
import soexample.umeng.com.day20.view.IView;

public class PresenterImpl implements Presenter{
    private IView iView;
    private ModelImpl model;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model=new ModelImpl();
    }

    @Override
    public void startRequest(String url) {
        model.getData(url, new MyCallBack() {
            @Override
            public void success(Object user) {
                iView.success(user);
            }

            @Override
            public void error(Object error) {
               iView.error(error);
            }
        });
    }
    public void onDetach(){
        if (iView!=null){
            iView=null;
        }
        if (model!=null){
            model=null;
        }
    }
}
