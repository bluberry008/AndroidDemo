package com.example.riderb.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riderb.R;
import com.example.riderb.adapter.AdapterHome;
import com.example.riderb.bean.Goods;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Home extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterHome adapterHome;
    List<Goods> list =new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bmob.initialize(this,"1fbbd80d7495b993c16f97010a9f190a");
        mrecyclerView=findViewById(R.id.recyclerview_home);
        layoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutManager);

        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> object, BmobException e) {
                if(e==null){
                    //List<Goods> list =new ArrayList<>();
                   // list.addAll(object);
                    // 添加数据到notificationList中
                    list=object;
                    adapterHome = new AdapterHome(Home.this,list);
                    mrecyclerView.setAdapter(adapterHome);//设置适配器
                }
                else {
                    Log.i("bmob获取通知","获取通知失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }


}
