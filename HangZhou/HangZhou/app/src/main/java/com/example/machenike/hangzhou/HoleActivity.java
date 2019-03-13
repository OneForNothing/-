package com.example.machenike.hangzhou;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.machenike.hangzhou.Fragment.FirstFragment;
import com.example.machenike.hangzhou.Fragment.HoleFragment;
import com.example.machenike.hangzhou.Fragment.ShuoShuoFragment;

import java.util.ArrayList;
import java.util.List;

import in.myinnos.customimagetablayout.ChangeColorTab;

public class HoleActivity extends AppCompatActivity {


    private List<ShareMessage> contentList;
    private BaseAdapter adapter;
    private ListView share_list;
    String[] mainTitlesArray={"Test1","Test2","Test3","Test4"};
    private ViewPager vp;
    private ChangeColorTab changeColorTab;
    private FragmentPagerAdapter adapterViewPager;
    private ImageView submmit;
    private RelativeLayout first;
    private RelativeLayout third;
    private RelativeLayout fourth;
    private RelativeLayout fifth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole1);
        vp=(ViewPager)findViewById(R.id.viewPager);
        changeColorTab=(ChangeColorTab)findViewById(R.id.tabChangeColorTab);
        changeColorTab.setViewpager(vp);
        adapterViewPager=new HoleActivity.MyPageAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterViewPager);

        submmit=findViewById(R.id.edit_shupshuo);
        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShuoShuoFragment().show(getSupportFragmentManager(),"dialog_fragment");
            }
        });


        first=(RelativeLayout)findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(HoleActivity.this,MainActivity.class);
                startActivity(turn);
            }
        });

        third=(RelativeLayout)findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(HoleActivity.this,QuestionActivity.class);
                startActivity(turn);
            }
        });

        fourth=(RelativeLayout)findViewById(R.id.fourth);
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(HoleActivity.this,ReservationActivity.class);
                startActivity(turn);
            }
        });

        fifth=(RelativeLayout)findViewById(R.id.fifth);
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(HoleActivity.this,CheckActivity.class);
                startActivity(turn);
            }
        });
    }



    class MyPageAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS=4;

        public MyPageAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        Fragment fragment;

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    fragment=new HoleFragment();
                    return fragment;
                case 1:
                    fragment=new HoleFragment();
                    return fragment;
                case 2:
                    fragment=new HoleFragment();
                    return fragment;
                case 3:
                    fragment=new HoleFragment();
                    return fragment;
                default:
                    return null;
            }

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mainTitlesArray[position];
        }
    }



}
