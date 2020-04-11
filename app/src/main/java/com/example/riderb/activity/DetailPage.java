package com.example.riderb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class DetailPage extends AppCompatActivity {
    ImageView back;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
    Button button;
    User user;
    String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datailpage);

        initView();
        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailPage.this, MainActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getUsername().equals(name)){
                    Toast.makeText(DetailPage.this,"不能接自己的单",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailPage.this,MainActivity.class));
                    finish();
                }else {
                    receiveOrder();
                }
            }
        });

 /*
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);




        final Observer<Goods> nameObserver = new Observer<Goods>() {
            @Override
            public void onChanged(@Nullable final Goods goods) {
                // Update the UI, in this case, a TextView.

                BmobQuery<Goods> query = new BmobQuery<Goods>();
                query.getObject(goods.getObjectId(), new QueryListener<Goods>() {
                    @Override
                    public void done(Goods goods, BmobException e) {
                        textView1.setText(goods.getRecipient());
                        textView2.setText(goods.getAddress());
                    }
                });

            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myViewModel.getCurrentName().observe(this, nameObserver);
*/
    }

    private void receiveOrder() {
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.getObject(textView1.getText().toString().trim(), new QueryListener<Goods>() {
            @Override
            public void done(Goods goods1, BmobException e) {
                if (e == null) {
                    Orders orders1=new Orders();
                    orders1.setAddress(goods1.getAddress());
                    orders1.setCode(goods1.getCode());
                    orders1.setContact(goods1.getContact());
                    orders1.setCount(goods1.getCount());
                    orders1.setDetail(goods1.getDetail());
                    orders1.setPoster(goods1.getPoster());
                    orders1.setRecipient(goods1.getRecipient());
                    orders1.setSize(goods1.getSize());
                    orders1.setState(1);
                    orders1.setEmailuser(user.getUsername());//接单人
                    orders1.setName(goods1.getEmailuser());//谁的单
                    user.setScore(orders1.getCount() + user.getScore());
                    Goods p2 = new Goods();
                    p2.setState("1");
                    p2.setObjectId(goods1.getObjectId());
                    p2.update(goods1.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                            }
                        }
                    });

                    orders1.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(DetailPage.this,"接单成功,从-已接单-查认",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DetailPage.this,MainActivity.class));
                                finish();
                            }
                        }
                    });
                }else{
                    Toast.makeText(DetailPage.this,"接单失败",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void initData() {
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
       // final Goods g=new Goods();
        BmobQuery<Goods> query=new BmobQuery<>();
        query.getObject(id, new QueryListener<Goods>() {
            @Override
            public void done(Goods goods, BmobException e) {
                if (e==null){
                    name=goods.getEmailuser();
                    textView1.setText(goods.getObjectId());
                    textView2.setText("快递点："+goods.getPoster());
                    textView3.setText("收货人："+goods.getRecipient());
                    textView4.setText("收货地址："+goods.getAddress());
                    textView5.setText("数量："+goods.getCount()+"件");
                    textView7.setText("详细要求："+goods.getDetail());
                    textView8.setText("联系方式："+goods.getContact());
                    if (goods.getSize()==0){
                        textView6.setText("全为小件");
                    }else if(goods.getSize()==1){
                        textView6.setText("有大件有小件");
                    }else {
                        textView6.setText("全为大件");
                    }

                }
            }
        });
    }

    private void initView() {
        textView1=findViewById(R.id.editText5);
        textView2=findViewById(R.id.editText6);
        textView3=findViewById(R.id.editText7);
        textView4=findViewById(R.id.editText10);
        textView5=findViewById(R.id.editText11);
        textView6=findViewById(R.id.editText12);
        textView7=findViewById(R.id.editText13);
        textView8=findViewById(R.id.editText14);
        back=findViewById(R.id.back);
        button=findViewById(R.id.button_re);
        user= BmobUser.getCurrentUser(User.class);
    }
}
