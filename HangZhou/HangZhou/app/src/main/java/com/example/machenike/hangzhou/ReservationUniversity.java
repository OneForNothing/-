package com.example.machenike.hangzhou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ReservationUniversity extends AppCompatActivity  {

    private LinearLayout back;
    private TextView cardText1;
    private TextView cardText2;
    private String phoneNumber="预约电话                15651975685  预约网站                  http://www.renkang555.com/?bdjjpc-xlza-zd-043528";
    private String address="地址                        江苏省南京市广州路264南京脑科医院门诊楼3楼";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_university);
        System.out.println("111111111111111");

        cardText1=(TextView) findViewById(R.id.card_text1);
        cardText1.setText(phoneNumber);

        cardText2=(TextView) findViewById(R.id.card_text2);
        cardText2.setText(address);

        back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn = new Intent(ReservationUniversity.this, ReservationActivity.class);
                startActivity(turn);
            }
        });

    }
}