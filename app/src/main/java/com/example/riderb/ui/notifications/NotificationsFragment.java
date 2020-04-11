package com.example.riderb.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.Navigation;


import com.example.riderb.MainActivity;
import com.example.riderb.R;
import com.example.riderb.activity.Login;
import com.example.riderb.activity.UpdatePsd;
import com.example.riderb.bean.User;

import cn.bmob.v3.BmobUser;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private TextView name,logout;
    Button button1,button2,button3,button4,updatapsd,confirm;
    User user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
    public void refresh() {

        onCreate(null);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name=getActivity().findViewById(R.id.textView4);
        logout=getActivity().findViewById(R.id.logout);
        updatapsd=getActivity().findViewById(R.id.updatepsd);
        button1=getActivity().findViewById(R.id.button_publish);
        button2=getActivity().findViewById(R.id.button_finished);
        button3=getActivity().findViewById(R.id.button_unfiish);
        button4=getActivity().findViewById(R.id.button_score);
        confirm=getActivity().findViewById(R.id.button_confirm);
        user= BmobUser.getCurrentUser(User.class);
        Refresh();
       if(logout.getText().toString().trim().equals("退出登录")){
           logout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   BmobUser.logOut();
                   Toast.makeText(getActivity(),"退出登录成功",Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(getActivity(), MainActivity.class));
               }
           });
       }
        if (user!=null){
            button1.setOnClickListener(Navigation.createNavigateOnClickListener(
                    R.id.action_notificationsFragment_to_publishFragment
            ));
        }else{
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (user!=null){
            button2.setOnClickListener(Navigation.createNavigateOnClickListener(
                    R.id.action_notificationsFragment_to_finishFragment
            ));
        }else{
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (user!=null){
            confirm.setOnClickListener(Navigation.createNavigateOnClickListener(
                    R.id.action_navigation_notifications_to_unconfirmFragment
            ));
        }else{
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (user!=null){
            button3.setOnClickListener(Navigation.createNavigateOnClickListener(
                    R.id.action_notificationsFragment_to_unfinishFragment
            ));
        }else{
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (user!=null){
            button4.setOnClickListener(Navigation.createNavigateOnClickListener(
                    R.id.action_notificationsFragment_to_scoreFragment
            ));
        }else{
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (user!=null){
            updatapsd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), UpdatePsd.class));
                }
            });
        }
    }

    private void Refresh() {
        if (user==null){
            name.setText("未登录");
            logout.setVisibility(View.GONE);
            updatapsd.setVisibility(View.GONE);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), Login.class));
                }
            });
        }else{
            logout.setText("退出登录");
            name.setText(user.getName());
        }
    }
}
