package com.example.riderb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.riderb.MainActivity;
import com.example.riderb.R;
import com.example.riderb.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login extends AppCompatActivity {

    private TextView username,psd,register,te;
    Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.email);
        psd=findViewById(R.id.psd);
        login=findViewById(R.id.login);
        register=findViewById(R.id.textView3);
         te=findViewById(R.id.textView2);
        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         User user = new User();
                                         user.setUsername(username.getText().toString().trim());
                                         user.setPassword(psd.getText().toString().trim());
                                         /*BmobUser.loginByAccount(username.getText().toString().trim(), psd.getText().toString().trim(), new LogInListener<User>() {

                                             @Override
                                             public void done(User user, BmobException e) {
                                                 if (e == null) {
                                                     Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                                     startActivity(new Intent(Login.this, MainActivity.class));
                                                     finish();
                                                 } else {
                                                     Toast.makeText(Login.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                                 }
                                             }
                                         });*/
                                        user.login(new SaveListener<User>() {
                                             @Override
                                             public void done(User user, BmobException e) {
                                                 if (e == null) {
                                                     Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                                     startActivity(new Intent(Login.this, MainActivity.class));
                                                     finish();
                                                 } else {
                                                     Toast.makeText(Login.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                                 }
                                             }
                                         });
                                     }
                                 });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
        te.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgetPsd.class));
            }
        });

    }
}
