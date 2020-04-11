package com.example.riderb.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.riderb.MainActivity;
import com.example.riderb.R;
import com.example.riderb.bean.User;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Timer timer=new Timer();
        timer.schedule(timerTask,2000);

        Bmob.initialize(this,"1fbbd80d7495b993c16f97010a9f190a");


    }
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
           // startActivity(new Intent(Welcome.this, MainActivity.class));
          //  BmobUser bmobUser=BmobUser.getCurrentUser(BmobUser.class);
            User bmobUser=BmobUser.getCurrentUser(User.class);
            startActivity(new Intent(Welcome.this, MainActivity.class));
            /*if(bmobUser!=null){
                startActivity(new Intent(Welcome.this, MainActivity.class));
            }else{
                startActivity(new Intent(Welcome.this, Login.class));
            }*/
        }
    };
    }
