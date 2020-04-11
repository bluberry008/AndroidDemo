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

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class UpdatePsd extends AppCompatActivity {
   private TextView email,psd,newpsd,newpsdcon;
   private Button confirm;
   User user;
   boolean flag=true;
   Integer index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepsd);
        initView();
        if (user!=null){
            email.setText(user.getUsername());
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(psd.getText().toString().trim()==""||newpsd.getText().toString().trim()==""||newpsdcon.getText().toString().trim()==""){
                        Toast.makeText(UpdatePsd.this,"不允许存在空值",Toast.LENGTH_SHORT).show();
                    }else {


                        BmobQuery<User> query = new BmobQuery<>();
                        query.addWhereEqualTo("password", psd.getText().toString().trim());
                        query.findObjects(new FindListener < User > () {
                            @Override
                            public void done(List<User> object, BmobException e) {
                                for (int i=0;i<object.size();i++){
                                    if (object.get(i).getUsername().equals(email.getText().toString().trim())){
                                         index=i;
                                         break;
                                    }
                                }
                                if (index<object.size()){
                                    flag=false;
                                }

                                if (flag){
                                    Toast.makeText(UpdatePsd.this,"原密码不正确，请检验",Toast.LENGTH_SHORT).show();

                                }else if (!newpsd.getText().toString().trim().equals(newpsdcon.getText().toString().trim())){
                                    Toast.makeText(UpdatePsd.this,"新密码两次输入不一致",Toast.LENGTH_SHORT).show();
                                }else{
                                    user.setPassword(newpsd.getText().toString().trim());
                                    user.update(user.getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e==null){
                                                BmobUser.logOut();
                                                Toast.makeText(UpdatePsd.this,"修改成功，请重新登录",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(UpdatePsd.this,Login.class));
                                                finish();
                                            }else{
                                                Toast.makeText(UpdatePsd.this,"修改失败",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });


                    }

                }
            });


        }else{
            Toast.makeText(UpdatePsd.this,"请先登录",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UpdatePsd.this,Login.class));
            finish();
        }

    }

    private void initView() {
        email=findViewById(R.id.register_emaiil);
        psd=findViewById(R.id.register_psd);
        newpsd = findViewById(R.id.newpsd);
        newpsdcon=findViewById(R.id.newpsd_confirm);
        confirm=findViewById(R.id.cofirm_update);
        user= BmobUser.getCurrentUser(User.class);
    }
}
