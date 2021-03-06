package com.example.machenike.hangzhou;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private String usernameStr;
    private String passwordStr;
    private final int LOGINSUCCESS=0;
    private final int LOGINNOTFOUND=1;
    private final int LOGINEXCEPT=2;
    private final int REGISTERSUCCESS=3;
    private final int REGISTERNOTFOUND=4;
    private final int REGISTEREXCEPT=5;
    private Button register;
    private final String judgeLogin="登录成功";

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //找到我们需要的控件
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        register = (Button) findViewById(R.id.bt_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(turn);
            }
        });

        // 使通知栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

    }
    //登录按钮的点击事件，也可以用set监听器的方法，不过这种方法简单
    public void login(View v){
        //获取编辑框内的内容
        usernameStr = username.getText().toString().trim();
        passwordStr = password.getText().toString().trim();

        //判断是否输入为空（在这里就不再进行正则表达式判断了）
        if(usernameStr.equals("") || passwordStr.equals("")){
            Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        }//进行登录操作(联网操作要添加权限)
        else {
            //联网操作要开子线程，在主线程不能更新UI
            new Thread(){

                private HttpURLConnection connection;


                @Override
                public void run() {

                    try {
                        //封装成传输数据的键值对,无论get还是post,传输中文时都要进行url编码（RULEncoder）
                        // 如果是在浏览器端的话，它会自动进行帮我们转码，不用我们进行手动设置
                        String data2= "username="+ URLEncoder.encode(usernameStr,"utf-8")+"&password="+ URLEncoder.encode(passwordStr,"utf-8")+"&sign="+URLEncoder.encode("login","utf-8");
                        connection=HttpConnectionUtils.getConnection(data2);
                        int code = connection.getResponseCode();
                        if(code==200){
                            InputStream inputStream = connection.getInputStream();
                            String str = StreamChangeStrUtils.toChange(inputStream);//写个工具类流转换成字符串
                            Message message = Message.obtain();//更新UI就要向消息机制发送消息
                            message.what=LOGINSUCCESS;//用来标志是哪个消息
                            message.obj=str;//消息主体
                            handler.sendMessage(message);
                            if(str.equals(judgeLogin)) {
                                Intent turn = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(turn);
                            }
                        }
                        else {
                            Message message = Message.obtain();
                            message.what=LOGINNOTFOUND;
                            message.obj="登录异常...请稍后再试";
                            handler.sendMessage(message);
                        }
                    } catch (Exception e) {//会抛出很多个异常，这里抓一个大的异常
                        Log.i("debug",Log.getStackTraceString(e));
                        //e.printStackTrace();
                        Message message = Message.obtain();
                        message.what=LOGINEXCEPT;
                        message.obj="服务器异常...请稍后再试";
                        handler.sendMessage(message);
                    }
                }
            }.start();//不要忘记开线程
        }
    }
}
