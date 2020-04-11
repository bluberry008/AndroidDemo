package com.example.riderb.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


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

public class OrderFragment extends Fragment {
   private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
   private Button button;
    Goods goods1;
    Orders orders1;
    private User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order1, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
        Button button;
         goods1=new Goods();
        orders1=new Orders();
        user= BmobUser.getCurrentUser(User.class);
        textView1=getActivity().findViewById(R.id.textView5);
        textView2=getActivity().findViewById(R.id.textView6);
        textView3=getActivity().findViewById(R.id.textView7);
        textView4=getActivity().findViewById(R.id.textView8);
        textView5=getActivity().findViewById(R.id.textView9);
        textView6=getActivity().findViewById(R.id.textView10);
        textView7=getActivity().findViewById(R.id.textView11);
        textView8=getActivity().findViewById(R.id.textView12);
        textView9=getActivity().findViewById(R.id.textView13);
        button=getActivity().findViewById(R.id.button3);

        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.getObject("ffb93dc2cf", new QueryListener<Goods>() {
            @Override
            public void done(Goods goods, BmobException e) {
                if (e==null){
                    goods1=goods;
                    textView1.setText(goods.getObjectId());
                    textView2.setText(goods.getPoster());
                    textView3.setText(goods.getRecipient());
                    textView4.setText(goods.getAddress());
                    textView5.setText(goods.getContact());
                    textView6.setText(goods.getCode());
                    textView7.setText(goods.getDetail());
                    textView8.setText("件数："+goods.getCount());
                    if(goods.getSize()==0) {
                        textView9.setText("小件");
                    }else if (goods.getSize()==1){
                        textView9.setText("有大有小");
                    }else{
                        textView9.setText("大件");
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
                orders1.setEmailuser(user.getUsername());
                orders1.setState(1);

                Orders p2 = new Orders();
                p2.setObjectId(orders1.getObjectId());
                p2.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                    }
                });


                orders1.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                           // Log.e(TAG, "done: ", );
                        }
                    }
                });



            }
            });
        button.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_notificationsFragment_to_unfinishFragment
        ));
    }
}
