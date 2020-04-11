package com.example.riderb.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.riderb.R;
import com.example.riderb.bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class ScoreFragment extends Fragment {
    User user;
    TextView textView1,textView2,textView3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_score, container, false);
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView1=getActivity().findViewById(R.id.ni);
        textView3=getActivity().findViewById(R.id.fen);
        user = BmobUser.getCurrentUser(User.class);
        BmobQuery<User> query = new BmobQuery<User>();
        query.getObject(user.getObjectId(), new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    textView3.setText(user.getScore()+"");
                }
            }
        });

        textView1.setText("昵称："+user.getName());

    }
}
