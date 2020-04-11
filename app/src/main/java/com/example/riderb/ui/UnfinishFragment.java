package com.example.riderb.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.riderb.R;
import com.example.riderb.adapter.AdapterUnfinish;
import com.example.riderb.bean.Orders;
import com.example.riderb.bean.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class UnfinishFragment extends Fragment {
    private RecyclerView mrecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_unfinish, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user= BmobUser.getCurrentUser(User.class);
        mrecyclerView = (RecyclerView)getActivity(). findViewById(R.id.recyclerview5);   //获取控件
        mrecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(layoutManager);
        BmobQuery<Orders> query = new BmobQuery<Orders>();
        query.addWhereEqualTo("emailuser",user.getUsername());
        query.addWhereEqualTo("state",1);
        query.order("-createdAt");
        query.findObjects(new FindListener<Orders>() {
            @Override
            public void done(List<Orders> object, BmobException e) {
                if(e==null){
                    List<Orders> list = new ArrayList<>();
                    list.addAll(object);          //添加数据到notificationList中
                    AdapterUnfinish myAdapter = new AdapterUnfinish(list);
                    mrecyclerView.setAdapter(myAdapter);      //设置适配器
                }
                else {
                    Log.i("bmob获取通知","获取通知失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
