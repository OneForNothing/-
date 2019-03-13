package com.example.machenike.hangzhou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;


public class ReservationActivity extends AppCompatActivity  {

    private RelativeLayout first;
    private RelativeLayout second;
    private RelativeLayout third;
    private RelativeLayout fifth;
    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        first = (RelativeLayout) findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn = new Intent(ReservationActivity.this, MainActivity.class);
                startActivity(turn);
            }
        });

        second = (RelativeLayout) findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn = new Intent(ReservationActivity.this, HoleActivity.class);
                startActivity(turn);
            }
        });

        third = (RelativeLayout) findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn = new Intent(ReservationActivity.this, QuestionActivity.class);
                startActivity(turn);
            }
        });

        fifth=(RelativeLayout)findViewById(R.id.fifth);
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(ReservationActivity.this,CheckActivity.class);
                startActivity(turn);
            }
        });

        cardView1=(CardView) findViewById(R.id.card1_conslut);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(ReservationActivity.this,ReservationUniversity.class);
                startActivity(turn);
            }
        });

        cardView2=(CardView) findViewById(R.id.card2_conslut);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(ReservationActivity.this,ReservationHospital.class);
                startActivity(turn);
            }
        });

        cardView3=(CardView) findViewById(R.id.card3_conslut);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(ReservationActivity.this,ReservationDoctor.class);
                startActivity(turn);
            }
        });

    }
}