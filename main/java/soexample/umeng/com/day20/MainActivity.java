package soexample.umeng.com.day20;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20.adapter.LeftAdapter;
import soexample.umeng.com.day20.adapter.RightAdapter;
import soexample.umeng.com.day20.base.BaseActivity;
import soexample.umeng.com.day20.bean.MyBean;
import soexample.umeng.com.day20.presenter.PresenterImpl;
import soexample.umeng.com.day20.view.IView;

public class MainActivity<T> extends BaseActivity implements IView<T> {

    private ArrayList<MyBean.DataBean> list = new ArrayList<>();

    private String mUrl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private PresenterImpl presenter;
    private RecyclerView Left_Recycler;
    private RecyclerView Right_Recycler;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private TextView rightTitle;
    private TextView goodsPrice;
    List<MyBean.DataBean.SpusBean> spus=new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        Left_Recycler = (RecyclerView) findViewById(R.id.Left_Recycler);
        Right_Recycler = (RecyclerView) findViewById(R.id.Right_Recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Left_Recycler.setLayoutManager(linearLayoutManager);
        Right_Recycler.setLayoutManager(layoutManager);
        rightTitle = findViewById(R.id.right_Title);
        goodsPrice = findViewById(R.id.goods_price);
    }

    @Override
    protected void setOnClick() {

    }

    @Override
    protected void processLogin() {
        presenter = new PresenterImpl(this);
        leftAdapter = new LeftAdapter(list, this);
        rightAdapter = new RightAdapter(spus, MainActivity.this);
        Left_Recycler.setAdapter(leftAdapter);
        Right_Recycler.setAdapter(rightAdapter);

        presenter.startRequest(mUrl);
        leftAdapter.setItemOnClick(new LeftAdapter.ItemOnClick() {
            @Override
            public void onItemClick(int position) {
                spus.clear();
                spus.addAll(list.get(position).getSpus());

                /*for (int i = 0; i < MainActivity.this.spus.size(); i++) {
                    MainActivity.this.spus.get(i).setPraise_num(0);
                }*/
                rightAdapter.notifyDataSetChanged();
                rightTitle.setText(list.get(position).getName());
            }
        });
        rightAdapter.setCallback(new RightAdapter.AdapterCallback() {
            @Override
            public void flush() {
                goodsPrice.setText("结算"+rightAdapter.getGoodsPrice());
            }
        });

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(T user) {
        MyBean data = (MyBean) user;
        list.addAll(data.getData());
        leftAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(T error) {
        Toast.makeText(this, error + "", Toast.LENGTH_SHORT).show();
    }


}
