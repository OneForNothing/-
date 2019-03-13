package com.example.machenike.hangzhou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
    private LinearLayout back;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_edit);

        back=findViewById(R.id.back);
        finish=findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText new_pass=findViewById(R.id.new_pass);
                EditText confirm_pass=findViewById(R.id.confirm_pass);
                String temp1=new_pass.getText().toString();
                String temp2=confirm_pass.getText().toString();
                if(temp1.equals("")){
                    Toast.makeText(getApplicationContext(),"请设置登陆密码",Toast.LENGTH_LONG).show();
                }else if((!temp1.equals(""))&(temp2.equals(""))){
                    Toast.makeText(getApplicationContext(),"请再次输入密码",Toast.LENGTH_LONG).show();
                }else if((!temp1.equals(""))&(!temp2.equals(""))&(!temp1.equals(temp2))){
                    Toast.makeText(getApplicationContext(),"两次密码不一致",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"修改密码成功啦~",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
