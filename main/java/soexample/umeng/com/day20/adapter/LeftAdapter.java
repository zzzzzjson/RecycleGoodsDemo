package soexample.umeng.com.day20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import soexample.umeng.com.day20.R;
import soexample.umeng.com.day20.bean.MyBean;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private ArrayList<MyBean.DataBean> list;
    private Context context;

    public LeftAdapter(ArrayList<MyBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.leftTitle.setText(list.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnClick != null) {
                    itemOnClick.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView leftTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leftTitle = itemView.findViewById(R.id.left_Title);
        }
    }

    private ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void onItemClick(int position);
    }

    ;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }
}
