package com.example.machenike.hangzhou.Fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machenike.hangzhou.ArticleAdapter;
import com.example.machenike.hangzhou.GlideImageLoader;
import com.example.machenike.hangzhou.MainActivity;
import com.example.machenike.hangzhou.PsychologyMessage;
import com.example.machenike.hangzhou.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements OnBannerListener,AdapterView.OnItemClickListener {

   int[] image = {R.drawable.shuffling1,
            R.drawable.shuffling2,
            R.drawable.shuffling3,
            R.drawable.shuffling4,
            R.drawable.shuffling5};
    String[] title = {"走出焦虑，从这一步开始！", "心理学九大误区，你中招了吗？",
            "权威，有关抑郁症的十个误区！", "国际权威！抑郁症自助手册！", "心理学九大误区，你中招了吗？"};
    private List<Integer> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Banner banner;
    private List<PsychologyMessage> contentList;
    private String[] allTitle;
    private String[] allContent;
    private NestedScrollView scrollView;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contentView=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_blank,container,false);

        banner=(Banner) contentView.findViewById(R.id.banner);
        ArticleAdapter myAdapter=new ArticleAdapter(getActivity(),getData());
        ListView listView=(ListView)contentView.findViewById(R.id.article);
        listView.setAdapter(myAdapter);
        setListViewHeightBasedOnChildren(listView);
        scrollView=(NestedScrollView)contentView.findViewById(R.id.scroll);
        scrollView.smoothScrollTo(0,0);
        for(int i=0;i<image.length;i++){
            images.add(image[i]);
        }
        for(int i=0;i<title.length;i++){
            titles.add(title[i]);
        }
        banner=(Banner) contentView.findViewById(R.id.banner);
        //banner.bringToFront();
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.setBannerAnimation(com.youth.banner.Transformer.DepthPage);
        banner.setBannerTitles(titles);
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();



        return contentView;
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i <listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()-1));
        listView.setLayoutParams(params);
    }

    private List<PsychologyMessage> getData(){
       List<PsychologyMessage> msgList=new ArrayList<PsychologyMessage>();
       PsychologyMessage msg;
       allTitle=getResources().getStringArray(R.array.title);
       allContent=getResources().getStringArray(R.array.content);

       msg=new PsychologyMessage();
       msg.setType(ArticleAdapter.LITTLE_PIC);
       msg.setTitle(allTitle[0]);
       msg.setContent(allContent[0]);
       msg.setPic(R.drawable.home1);
       msgList.add(msg);

       msg=new PsychologyMessage();
       msg.setType(ArticleAdapter.NO_PIC);
       msg.setTitle(allTitle[1]);
       msg.setContent(allContent[1]);
       msgList.add(msg);

       msg=new PsychologyMessage();
       msg.setType(ArticleAdapter.BIG_PIC);
       msg.setTitle(allTitle[2]);
       msg.setContent(allContent[2]);
       msg.setPic(R.drawable.home2);
       msgList.add(msg);

       msg=new PsychologyMessage();
       msg.setType(ArticleAdapter.BIG_PIC);
       msg.setTitle(allTitle[2]);
       msg.setContent(allContent[2]);
       msg.setPic(R.drawable.share_back);
       msgList.add(msg);

       return msgList;
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(),"你点击了第"+position+"张轮播图",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(),"你点击了第"+i+"篇文章",Toast.LENGTH_LONG).show();
    }

}
