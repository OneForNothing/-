package com.example.machenike.hangzhou.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.machenike.hangzhou.ArticleAdapter;
import com.example.machenike.hangzhou.R;
import com.example.machenike.hangzhou.RefreshableView;
import com.example.machenike.hangzhou.ShareAdapter;
import com.example.machenike.hangzhou.ShareMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoleFragment extends Fragment {

    private String[] allName;
    private String[] allTime;
    private String[] allContent;
    private int[] allLike;
    private int[] allComment;
    private RefreshableView refreshableView;



    public HoleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hole,container,false);

        refreshableView=(RefreshableView) contentView.findViewById(R.id.refreshable_view);
        ShareAdapter myAdapter=new ShareAdapter(getActivity(),getData());
        ListView listView=(ListView)contentView.findViewById(R.id.shuoshuo);
        listView.setAdapter(myAdapter);

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        },0);
        return contentView;
    }

    private List<ShareMessage> getData(){
        List<ShareMessage> msgList=new ArrayList<ShareMessage>();
        ShareMessage msg;
        allName=getResources().getStringArray(R.array.name);
        allTime=getResources().getStringArray(R.array.time);
        allContent=getResources().getStringArray(R.array.share);
        allLike=getResources().getIntArray(R.array.like);
        allComment=getResources().getIntArray(R.array.comment);

        msg=new ShareMessage();
        msg.setType(ShareAdapter.WITH_PIC);
        msg.setName(allName[0]);
        msg.setTime(allTime[0]);
        msg.setContent(allContent[0]);
        msg.setLike(allLike[0]);
        msg.setComment(allComment[0]);
        msg.setHead_image(R.drawable.boy1);
        msg.setShare_image(R.drawable.sharepicture1);
        msgList.add(msg);

        msg=new ShareMessage();
        msg.setType(ShareAdapter.WITH_PIC);
        msg.setName(allName[1]);
        msg.setTime(allTime[1]);
        msg.setContent(allContent[1]);
        msg.setLike(allLike[1]);
        msg.setComment(allComment[1]);
        msg.setHead_image(R.drawable.boy1);
        msg.setShare_image(R.drawable.sharepicture1);
        msgList.add(msg);

        msg=new ShareMessage();
        msg.setType(ShareAdapter.WITH_PIC);
        msg.setName(allName[2]);
        msg.setTime(allTime[2]);
        msg.setContent(allContent[2]);
        msg.setLike(allLike[2]);
        msg.setComment(allComment[2]);
        msg.setHead_image(R.drawable.boy1);
        msg.setShare_image(R.drawable.sharepicture1);
        msgList.add(msg);

        msg=new ShareMessage();
        msg.setType(ShareAdapter.WITHOUT_PIC);
        msg.setName(allName[3]);
        msg.setTime(allTime[3]);
        msg.setContent(allContent[3]);
        msg.setLike(allLike[3]);
        msg.setComment(allComment[3]);
        msg.setHead_image(R.drawable.boy1);
        msgList.add(msg);

        msg=new ShareMessage();
        msg.setType(ShareAdapter.WITH_PIC);
        msg.setName(allName[4]);
        msg.setTime(allTime[4]);
        msg.setContent(allContent[4]);
        msg.setLike(allLike[4]);
        msg.setComment(allComment[4]);
        msg.setHead_image(R.drawable.boy1);
        msg.setShare_image(R.drawable.sharepicture1);
        msgList.add(msg);

        msg=new ShareMessage();
        msg.setType(ShareAdapter.WITH_PIC);
        msg.setName(allName[5]);
        msg.setTime(allTime[5]);
        msg.setContent(allContent[5]);
        msg.setLike(allLike[5]);
        msg.setComment(allComment[5]);
        msg.setHead_image(R.drawable.boy1);
        msg.setShare_image(R.drawable.sharepicture1);
        msgList.add(msg);

        msg=new ShareMessage();
        msg.setType(ShareAdapter.WITHOUT_PIC);
        msg.setName(allName[6]);
        msg.setTime(allTime[6]);
        msg.setContent(allContent[6]);
        msg.setLike(allLike[6]);
        msg.setComment(allComment[6]);
        msg.setHead_image(R.drawable.boy1);
        msgList.add(msg);

        return msgList;

    }

}
