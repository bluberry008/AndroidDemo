package com.example.riderb.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.riderb.R;

public class ForgetPsd extends AppCompatActivity {
    Button button;
    EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpsd);
        button=findViewById(R.id.button2);
        editText=findViewById(R.id.editText8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editText.getText().toString();
                /*BmobUser.resetPasswordByEmail(email, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                   if(e==null){
                       Toast.makeText(ForgetPsd.this,"发送成功,注意查收",Toast.LENGTH_SHORT).show();
                   }
                    }

                });*/
                /*BmobUser.requestEmailVerify(email, new  Email EmailVerifyListener(){

                    @Override
                    public void onSuccess() {
                        toast("please active your account in your email box");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        toast("email sending failed, please retry"+s);
                    }

                });*/

            }
        });
    }

}
