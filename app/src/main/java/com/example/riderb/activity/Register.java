package com.example.riderb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.riderb.R;
import com.example.riderb.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {
    private TextView email,psd,name,input_code;
    Button sendCode,register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.input_email);
        psd=findViewById(R.id.input_psd);
        name=findViewById(R.id.input_name);
        sendCode=findViewById(R.id.code);
        input_code=findViewById(R.id.input_code);
        register=findViewById(R.id.registerbutton);

       /*sendCode.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendCode.setEnabled(false);
               BmobSMS.requestSMSCode("13612108287", "测试", new QueryListener<Integer>() {
                   @Override
                   public void done(Integer integer, BmobException e) {
if (e==null){
    Toast.makeText(Register.this,"发送成功",Toast.LENGTH_SHORT).show();
}else{
    Toast.makeText(Register.this,"发送失败",Toast.LENGTH_SHORT).show();
}
                   }


               });
           }
       });*/

        /*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=input_code.getText().toString().trim();
                if(!TextUtils.isEmpty(number)){
                    BmobSMS.verifySmsCode("13612108287", number, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Toast.makeText(Register.this,"验证成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }*/




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(email.getText().toString().trim());
                user.setPassword(psd.getText().toString().trim());
                user.setName(name.getText().toString().trim());
                user.setScore(3);

                if (email.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();

                } else if (psd.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if (name.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    user.signUp(new SaveListener<User>() {

                        @Override
                        public void done(User user, BmobException e) {
                            if(e==null){
                                Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,Login.class));
                                finish();
                            }else{
                                Toast.makeText(Register.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
