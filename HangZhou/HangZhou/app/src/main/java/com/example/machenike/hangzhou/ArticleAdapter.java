package com.example.machenike.hangzhou;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {
    public static final int NO_PIC=0;
    public static final int LITTLE_PIC=1;
    public static final int BIG_PIC=2;
    private LayoutInflater mInflater;

    private List<PsychologyMessage> mList;

    public ArticleAdapter(Context context, List<PsychologyMessage> mList){
        this.mList=mList;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewBigPicture {
        private ImageView article_pic;
        private TextView title;
        private TextView content;
    }

    public class ViewLittlePicture {
        private ImageView article_pic;
        private TextView title;
        private TextView content;
    }

    public class ViewNoPicture {
        private TextView title;
        private TextView content;
    }

    @Override
    public int getItemViewType(int position) {
        PsychologyMessage msg=mList.get(position);
        int type=msg.getType();
        Log.e("TYPE+",""+type);
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PsychologyMessage msg=mList.get(i);
        int type=getItemViewType(i);
        ViewNoPicture holderNoPicture=null;
        ViewLittlePicture holderLittlePicture=null;
        ViewBigPicture holderBigPicture=null;

        if(view==null){
           switch (type){
               case NO_PIC:
                   holderNoPicture=new ViewNoPicture();
                   view=mInflater.inflate(R.layout.no_picture,null);
                   holderNoPicture.title=(TextView)view.findViewById(R.id.no_title);
                   holderNoPicture.content=(TextView)view.findViewById(R.id.no_content);
                   holderNoPicture.title.setText(msg.getTitle());
                   holderNoPicture.content.setText(msg.getContent());
                   break;

               case BIG_PIC:
                   holderBigPicture=new ViewBigPicture();
                   view=mInflater.inflate(R.layout.big_picture,null);
                   holderBigPicture.article_pic=(ImageView)view.findViewById(R.id.big_image);
                   holderBigPicture.content=(TextView)view.findViewById(R.id.big_content);
                   holderBigPicture.title=(TextView)view.findViewById(R.id.big_title);
                   holderBigPicture.title.setText(msg.getTitle());
                   holderBigPicture.content.setText(msg.getContent());
                   holderBigPicture.article_pic.setImageResource(msg.getPic());
                   break;

               case LITTLE_PIC:
                   holderLittlePicture=new ViewLittlePicture();
                   view=mInflater.inflate(R.layout.little_picture,null);
                   holderLittlePicture.article_pic=(ImageView)view.findViewById(R.id.little_picture);
                   holderLittlePicture.content=(TextView)view.findViewById(R.id.little_content);
                   holderLittlePicture.title=(TextView)view.findViewById(R.id.little_title);
                   holderLittlePicture.title.setText(msg.getTitle());
                   holderLittlePicture.content.setText(msg.getContent());
                   holderLittlePicture.article_pic.setImageResource(msg.getPic());
                   break;
                   default:
                       break;
           }


        }
        return view;
    }


}
