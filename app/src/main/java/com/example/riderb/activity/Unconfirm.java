package com.example.riderb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.riderb.R;
import com.example.riderb.bean.Goods;
import com.example.riderb.bean.Orders;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class Unconfirm extends AppCompatActivity {
   TextView textView1,textView2,textView3,textView4;
   Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cunconfirm);
        initView();

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        // final Goods g=new Goods();
        BmobQuery<Orders> query=new BmobQuery<>();
        query.getObject("id",new QueryListener<Orders>() {
            @Override
            public void done(Orders orders, BmobException e) {
                if (e==null){
                    textView1.setText(orders.getPoster());
                    textView2.setText("送货人"+orders.getEmailuser());
                    textView3.setText("收货地址"+orders.getAddress());
                    textView4.setText("创建时间"+orders.getCreatedAt());
                }

            }
        });
    }

    private void initView() {
        textView1=findViewById(R.id.textView16);
        textView2=findViewById(R.id.textView17);
        textView3=findViewById(R.id.textView18);
        textView4=findViewById(R.id.textView19);
        button.findViewById(R.id.button16);
    }
}
