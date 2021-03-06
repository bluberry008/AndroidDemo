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
import com.example.riderb.bean.Goods;
import com.example.riderb.bean.Orders;
import com.example.riderb.bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ReceiveOrder extends AppCompatActivity {
    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    private Button button,button1;
    Goods goods1;
    Orders orders1;
    private User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiveorders);
        final TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;

        goods1=new Goods();
        orders1=new Orders();
        user= BmobUser.getCurrentUser(User.class);
        textView1=findViewById(R.id.textView5);
        textView2=findViewById(R.id.textView6);
        textView3=findViewById(R.id.textView7);
        textView4=findViewById(R.id.textView8);
        textView5=findViewById(R.id.textView9);
        textView6=findViewById(R.id.textView10);
        textView7=findViewById(R.id.textView11);
        textView8=findViewById(R.id.textView12);
        textView9=findViewById(R.id.textView13);
        button=findViewById(R.id.button3);
        button1=findViewById(R.id.button_return);
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.getObject("ffb93dc2cf", new QueryListener<Goods>() {
            @Override
            public void done(Goods goods, BmobException e) {
                if (e==null){
                    goods1=goods;
                    textView1.setText("ID："+goods.getObjectId());
                    textView2.setText("发送：方"+goods.getPoster());
                    textView3.setText("收货方："+goods.getRecipient());
                    textView4.setText("收货地址"+goods.getAddress());
                    textView5.setText("联系方式"+goods.getContact());
                    textView6.setText("请查看个人中心--未完成订单");
                    textView9.setText("详细要求："+goods.getDetail());
                    textView7.setText("件数："+goods.getCount());
                    if(goods.getSize()==0) {
                        textView8.setText("小件");
                    }else if (goods.getSize()==1){
                        textView8.setText("有大有小");
                    }else{
                        textView8.setText("大件");
                    }

                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orders1.setAddress(goods1.getAddress());
                orders1.setCode(goods1.getCode());
                orders1.setContact(goods1.getContact());
                orders1.setCount(goods1.getCount());
                orders1.setDetail(goods1.getDetail());
                orders1.setPoster(goods1.getPoster());
                orders1.setRecipient(goods1.getRecipient());
                orders1.setSize(goods1.getSize());
                orders1.setEmailuser(user.getUsername());//接单人
                orders1.setState(1);
                orders1.setEmailuser(user.getUsername());//接单人
                orders1.setName(goods1.getEmailuser());//谁的单
                user.setScore(orders1.getCount()+user.getScore());
                Goods p2 = new Goods();
                p2.setObjectId(goods1.getObjectId());
                p2.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                    }
                });


                orders1.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(ReceiveOrder.this,"接单成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
button1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(ReceiveOrder.this, MainActivity.class));
    }
});
    }
}
