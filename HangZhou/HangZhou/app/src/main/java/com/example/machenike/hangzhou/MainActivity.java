package com.example.machenike.hangzhou;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;

import com.example.machenike.hangzhou.Fragment.FirstFragment;
import com.example.machenike.hangzhou.Fragment.MyDialogFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import in.myinnos.customimagetablayout.ChangeColorTab;

public class MainActivity extends AppCompatActivity {

    int[] image = {R.drawable.shuffling1,
            R.drawable.shuffling2,
            R.drawable.shuffling3,
            R.drawable.shuffling4,
            R.drawable.shuffling5};
    String[] title = {"走出焦虑，从这一步开始！", "心理学九大误区，你中招了吗？",
            "权威，有关抑郁症的十个误区！", "国际权威！抑郁症自助手册！", "心理学九大误区，你中招了吗？"};
    String[] mainTitlesArray={"Test1","Test2","Test3","Test4"};
    private List<Integer> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Banner banner;
    private String[] alltitle;
    private String[] allcontent;
    private List<PsychologyMessage> contentlist;
    private BaseAdapter adapter;
    private ListView article_list;
    private RelativeLayout holeLayout;
    private Toolbar toolbar;
    private DrawerLayout mdrawerLayout;
    private NavigationView navView;
    private ViewPager vp;
    private ChangeColorTab changeColorTab;
    private FragmentPagerAdapter adapterViewPager;
    private RelativeLayout second;
    private RelativeLayout third;
    private RelativeLayout fourth;
    private RelativeLayout fifth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.boy1);
        }
        navView.setCheckedItem(R.id.nav_camera);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_send:
                        new MyDialogFragment().show(getSupportFragmentManager(), "dialog_fragment");
                        break;
                    case R.id.password_edit:
                        Intent temp=new Intent(MainActivity.this,PasswordActivity.class);
                        startActivity(temp);
                        break;
                    case R.id.nav_share:
                        Intent temp1=new Intent(MainActivity.this,ShareActivity.class);
                        startActivity(temp1);
                        break;
                }
                return true;
            }
        });

        View head=navView.getHeaderView(0);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open=new Intent(MainActivity.this,EditInfomation.class);
                startActivity(open);
            }
        });

        second=(RelativeLayout)findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(MainActivity.this,HoleActivity.class);
                startActivity(turn);
            }
        });

        third=(RelativeLayout)findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(MainActivity.this,QuestionActivity.class);
                startActivity(turn);
            }
        });

        fourth=(RelativeLayout)findViewById(R.id.fourth);
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(MainActivity.this,ReservationActivity.class);
                startActivity(turn);
            }
        });

        fifth=(RelativeLayout)findViewById(R.id.fifth);
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(MainActivity.this,CheckActivity.class);
                startActivity(turn);
            }
        });


       /* holeLayout=(RelativeLayout)findViewById(R.id.second);
        holeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HoleActivity.class);
                startActivity(intent);
            }
        });

        for(int i=0;i<image.length;i++){
            images.add(image[i]);
        }
        for(int i=0;i<title.length;i++){
            titles.add(title[i]);
        }
        banner=(Banner)findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.setBannerAnimation(com.youth.banner.Transformer.DepthPage);
        banner.setBannerTitles(titles);
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();*/

        vp=(ViewPager)findViewById(R.id.viewPager);
        changeColorTab=(ChangeColorTab)findViewById(R.id.tabChangeColorTab);
        changeColorTab.setViewpager(vp);
        adapterViewPager=new MyPageAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterViewPager);
    }

  /*  class MyViewPageAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mainTitlesArray.length;
        }

        //判断是否是否为同一张图片，这里返回方法中的两个参数做比较就可以
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        //设置viewpage内部东西的方法，如果viewpage内没有子空间滑动产生不了动画效果
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(mainTitlesArray[position]);
            textView.setGravity(Gravity.CENTER);
            container.addView(textView);
            //最后要返回的是控件本身
            return textView;
        }
        //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            //         super.destroyItem(container, position, object);
        }
        //目的是展示title上的文字，
        @Override
        public CharSequence getPageTitle(int position) {
            return mainTitlesArray[position];
        }
    }
    */

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
                    fragment=new FirstFragment();
                    return fragment;
                case 1:
                    fragment=new FirstFragment();
                    return fragment;
                case 2:
                    fragment=new FirstFragment();
                    return fragment;
                case 3:
                    fragment=new FirstFragment();
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

