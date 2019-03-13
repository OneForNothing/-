package com.example.machenike.hangzhou;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;


public class RegisterActivity extends AppCompatActivity {

    private LinearLayout backLogin;
    private EditText username;
    private EditText password;
    private EditText confirmPsd;
    private EditText email;
    private EditText school;
    private String usernameStr;
    private String passwordStr;
    private String confirmPsdStr;
    private String emailStr;
    private String schoolStr;
    private final int LOGINSUCCESS=0;
    private final int LOGINNOTFOUND=1;
    private final int LOGINEXCEPT=2;
    private final int REGISTERSUCCESS=3;
    private final int REGISTERNOTFOUND=4;
    private final int REGISTEREXCEPT=5;
    private final String judgeRegister="注册成功";

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){//消息机制，用来在子线程中更新UI
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){//具体消息，具体显示
                case LOGINSUCCESS:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case LOGINNOTFOUND:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case LOGINEXCEPT:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case REGISTERSUCCESS:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case REGISTERNOTFOUND:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
                case REGISTEREXCEPT:
                    Toast.makeText(getApplicationContext(),(String)msg.obj,Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPsd=(EditText)findViewById(R.id.confirmPsd);
        email=(EditText)findViewById(R.id.email);
        school=(EditText)findViewById(R.id.school);

        backLogin = (LinearLayout)findViewById(R.id.backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(turn);
            }
        });

    }

    //注册按钮的点击事件
    public void register(View v){

        usernameStr = username.getText().toString().trim();
        passwordStr = password.getText().toString().trim();
        confirmPsdStr=confirmPsd.getText().toString().trim();
        emailStr=email.getText().toString().trim();
        schoolStr=school.getText().toString().trim();


        if(usernameStr.equals("") || passwordStr.equals("")||confirmPsdStr.equals("")||emailStr.equals("")){
            Toast.makeText(RegisterActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else if(usernameStr.length()!=11){
            Toast.makeText(RegisterActivity.this,"请使用正确手机号作为用户名",Toast.LENGTH_SHORT).show();
        }
        else if(passwordStr.equals(confirmPsdStr)==false){
            Toast.makeText(RegisterActivity.this,"两次密码输入不一致，请重新输入",Toast.LENGTH_SHORT).show();
        }
        else if(emailStr.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")==false){
            Toast.makeText(RegisterActivity.this,"请使用正确的邮箱格式",Toast.LENGTH_SHORT).show();
        }
        else{
            new Thread(){

                HttpURLConnection connection=null;
                @Override
                public void run() {
                    try {
                        String data= "username="+ URLEncoder.encode(usernameStr,"utf-8")+"&password="+ URLEncoder.encode(passwordStr,"utf-8")+"&email="+URLEncoder.encode(emailStr,"utf-8")+"&school="+URLEncoder.encode(schoolStr,"utf-8")+"&sign="+URLEncoder.encode("register","utf-8");
                        connection=HttpConnectionUtils.getConnection(data);
                        int code = connection.getResponseCode();
                        if(code==200){
                            InputStream inputStream = connection.getInputStream();
                            String str = StreamChangeStrUtils.toChange(inputStream);
                            Message message = Message.obtain();
                            message.obj=str;
                            message.what=REGISTERSUCCESS;
                            handler.sendMessage(message);

                            if(str.equals(judgeRegister)) {
                                Intent turn = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(turn);
                            }
                        }
                        else {
                            Message message = Message.obtain();
                            message.what=REGISTERNOTFOUND;
                            message.obj="注册异常...请稍后再试";
                            handler.sendMessage(message);
                        }
                    } catch (Exception e) {
                        Log.i("debug",Log.getStackTraceString(e));
                        //e.printStackTrace();
                        Message message = Message.obtain();
                        message.what=REGISTEREXCEPT;
                        message.obj="服务器异常...请稍后再试";
                        handler.sendMessage(message);
                    }

                }
            }.start();//不要忘记开线程

        }
    }
}

