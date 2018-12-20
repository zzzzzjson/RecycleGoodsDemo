package soexample.umeng.com.day20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.day20.MyView;
import soexample.umeng.com.day20.R;
import soexample.umeng.com.day20.bean.MyBean;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private List<MyBean.DataBean.SpusBean> data;
    private Context context;

    public RightAdapter(List<MyBean.DataBean.SpusBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.getPrice.setText(data.get(i).getSkus().get(0).getPrice()+"");
        viewHolder.getTitle.setText(data.get(i).getName());
        Glide.with(context).load(data.get(i).getPic_url()).into(viewHolder.rightImg);
        viewHolder.myView.setCount(data.get(i).getPraise_num());
        viewHolder.myView.setCountCallback(new MyView.CountCallback() {
            @Override
            public void setNum(int num) {
                data.get(i).setPraise_num(num);
                notifyDataSetChanged();
                adapterCallback.flush();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView rightImg;
        private TextView getTitle;
        private TextView getPrice;
        private MyView  myView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rightImg = itemView.findViewById(R.id.right_img);
            getTitle = itemView.findViewById(R.id.get_Title);
            getPrice = itemView.findViewById(R.id.get_Price);
            myView = itemView.findViewById(R.id.my_view);
        }
    }
    public float getGoodsPrice(){
        float price=0;
        for (int i = 0; i < data.size(); i++) {
            price+=data.get(i).getPraise_num()*Float.valueOf(data.get(i).getSkus().get(0).getPrice());
        }
        return price;
    }
    private AdapterCallback adapterCallback;
    public interface AdapterCallback{
        void flush();
    }
    public void setCallback(AdapterCallback adapterCallback){
        this.adapterCallback=adapterCallback;
    }
}
