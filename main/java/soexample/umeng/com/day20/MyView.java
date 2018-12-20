package soexample.umeng.com.day20;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyView extends LinearLayout implements View.OnClickListener {
    private ImageView add_Btn;
    private TextView get_num;
    private ImageView delete_Btn;
    private int count = 0;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_view, this);
        initView();
    }

    private void initView() {
        add_Btn = findViewById(R.id.add_Btn);
        delete_Btn = findViewById(R.id.delete_Btn);
        add_Btn.setOnClickListener(this);
        delete_Btn.setOnClickListener(this);
        get_num = findViewById(R.id.get_num);
    }
 public void setCount(int count){
        this.count=count;
        if (count==0){
            delete_Btn.setVisibility(GONE);
            get_num.setVisibility(GONE);
        }else {
            delete_Btn.setVisibility(VISIBLE);
            get_num.setVisibility(VISIBLE);
        }
        get_num.setText(count+"");
 }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_Btn:
                if (count > 0) {
                    count--;
                    get_num.setText(count + "");
                    if (countCallback!=null){
                        countCallback.setNum(count);
                    }
                }
                break;
            case R.id.add_Btn:
                count++;
                get_num.setText(count+"");
                if (countCallback!=null){
                    countCallback.setNum(count);
                }
                break;
        }

    }

    private CountCallback countCallback;
    public interface CountCallback{
        void setNum(int num);
    }
    public void setCountCallback(CountCallback countCallback){
        this.countCallback=countCallback;
    }
}

