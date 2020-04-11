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
import com.example.riderb.adapter.AdapterPublish;
import com.example.riderb.bean.Goods;
import com.example.riderb.bean.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PublishFragment extends Fragment {
    private RecyclerView mrecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_publish, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user= BmobUser.getCurrentUser(User.class);
        mrecyclerView = (RecyclerView)getActivity(). findViewById(R.id.recyclerview2);   //获取控件
        mrecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(layoutManager);
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.addWhereEqualTo("emailuser",user.getUsername());
        query.order("-createdAt");
        query.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> object, BmobException e) {
                if(e==null){
                    List<Goods> list = new ArrayList<>();
                    list.addAll(object);          //添加数据到notificationList中
                    AdapterPublish myAdapter = new  AdapterPublish(list);
                    mrecyclerView.setAdapter(myAdapter);      //设置适配器
                }
                else {
                    Log.i("bmob获取通知","获取通知失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
