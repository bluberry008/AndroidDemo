package com.example.riderb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.riderb.MainActivity;
import com.example.riderb.R;
import com.example.riderb.bean.Orders;
import com.example.riderb.bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class ConfirmOrder extends AppCompatActivity {
   private TextView textView1,textView2,textView3,textView4;
   Button button;
User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cunconfirm);
        initView();
        user= BmobUser.getCurrentUser(User.class);
        Intent intent=getIntent();;
        final String id=intent.getStringExtra("id");
        BmobQuery<Orders> query=new BmobQuery<>();
        query.addWhereEqualTo("state",1);
        query.addWhereEqualTo("name",user.getName());
        query.getObject(id, new QueryListener<Orders>() {
            @Override
            public void done(Orders orders, BmobException e) {
                 textView1.setText("送货人"+orders.getEmailuser());
                 textView2.setText(orders.getCount()+"件");
                 textView3.setText("收货地址"+orders.getAddress());
                 textView4.setText("创建时间"+orders.getCreatedAt());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(ConfirmOrder.this,"确认收货成功",Toast.LENGTH_SHORT).show();
               Orders orders=new Orders();
                orders.setState(2);
                orders.update(id, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                      if (e==null){
                          Toast.makeText(ConfirmOrder.this,"确认收货成功",Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(ConfirmOrder.this, MainActivity.class));
                      }
                    }
                });
            }
        });

    }

    public void initView(){
        textView1=findViewById(R.id.textView16);
        textView2=findViewById(R.id.textView17);
        textView3=findViewById(R.id.textView18);
        textView4=findViewById(R.id.textView19);
        button=findViewById(R.id.button16);
    }
}
