package com.example.riderb.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.riderb.R;
import com.example.riderb.activity.DetailPage;
import com.example.riderb.activity.Login;
import com.example.riderb.activity.Publish;
import com.example.riderb.activity.ReceiveOrder;
import com.example.riderb.adapter.MyAdapter;
import com.example.riderb.bean.Goods;
import com.example.riderb.bean.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HomeFragment extends Fragment {

     FloatingActionButton add,de;
     User user;



    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rv;
    List<Goods> data;
    private MyAdapter myAdapter;;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        Bmob.initialize(getActivity(),"1fbbd80d7495b993c16f97010a9f190a");

        Refresh();


         swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 Refresh();
             }
         });



        add=getActivity().findViewById(R.id.floatingActionButton2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user= BmobUser.getCurrentUser(User.class);
                if(user==null){
                    startActivity(new Intent(getActivity(), Login.class));
                }else {
                    startActivity(new Intent(getActivity(), Publish.class));

                   // startActivity(new Intent(getActivity(), DetailPage.class));
                }
            }
        });

       /* de=getActivity().findViewById(R.id.floatingActionButton);
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReceiveOrder.class));
            }
        });*/



    }

    private void Refresh() {
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.addWhereEqualTo("state","0");
        query.order("-createdAt");
        query.setLimit(100);
        query.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> object, BmobException e) {
                swipeRefreshLayout.setRefreshing(false);
                if(e==null){
                    data=object;
                    myAdapter = new MyAdapter(getActivity(),data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(myAdapter);//设置适配器
                }
                else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initView(){
        rv = (RecyclerView)getActivity(). findViewById(R.id.recyclerView);   //获取控件

        swipeRefreshLayout=getActivity().findViewById(R.id.swip);
    }
}
