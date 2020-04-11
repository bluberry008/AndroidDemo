package com.example.riderb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.riderb.MainActivity;
import com.example.riderb.R;
import com.example.riderb.bean.Goods;
import com.example.riderb.bean.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class Publish extends AppCompatActivity {
    private TextView poster,recipient,address,detail,count,code,contact;
    RadioButton small,medium,big;
    Button publish;
    User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        Bmob.initialize(this,"1fbbd80d7495b993c16f97010a9f190a");
        user= BmobUser.getCurrentUser(User.class);
        BmobQuery<User> query = new BmobQuery<User>();
        query.getObject(user.getObjectId(), new QueryListener<User>() {
            @Override
            public void done(User user1, BmobException e) {
                if (e==null){
                    user.setScore(user1.getScore());
                }
            }
        });
        initView();
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((user.getScore() - Integer.parseInt(count.getText().toString().trim())) >= 0) {
                    Goods goods = new Goods();
                    goods.setPoster(poster.getText().toString().trim());
                    goods.setAddress(address.getText().toString().trim());
                    goods.setRecipient(recipient.getText().toString().trim());
                    goods.setCode(code.getText().toString().trim());
                    goods.setDetail(detail.getText().toString().trim());
                    goods.setCount(Integer.parseInt(count.getText().toString().trim()));
                    goods.setContact(contact.getText().toString().trim());
                    goods.setEmailuser(user.getUsername());
                    goods.setState("0");
                    System.out.println(user.getUsername());
                    if (small.isChecked()) {
                        goods.setSize(0);
                    } else if (big.isChecked()) {
                        goods.setSize(2);
                    } else {
                        goods.setSize(1);
                    }
                    goods.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (poster.getText() == null || address.getText() == null || recipient.getText() == null ||
                                    code.getText() == null || count.getText() == null || contact.getText() == null) {
                                Toast.makeText(Publish.this, "除要求栏，不允许为空", Toast.LENGTH_SHORT).show();
                            } else if (e == null) {


                                user.setScore(user.getScore() - Integer.parseInt(count.getText().toString().trim()));
                                user.update(user.getObjectId(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(Publish.this, "积分扣除成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                Toast.makeText(Publish.this, "发布成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Publish.this, MainActivity.class));
                                finish();
                            }
                        }
                    });

                } else {
                    Toast.makeText(Publish.this, "积分不足，只能接单", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Publish.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    public void initView(){
        contact=findViewById(R.id.contact);
        poster =findViewById(R.id.poster1);
        recipient=findViewById(R.id.recipient1);
        address=findViewById(R.id.address1);
        detail=findViewById(R.id.detail1);
        count=findViewById(R.id.count1);
        code=findViewById(R.id.code1);
        small=findViewById(R.id.small1);
        medium=findViewById(R.id.sb);
        big=findViewById(R.id.big1);
        publish=findViewById(R.id.publish1);
    }
}
