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

public class ShareAdapter extends BaseAdapter {
    public static final int WITHOUT_PIC=0;
    public static final int WITH_PIC=1;
    private LayoutInflater mInflater;

    private List<ShareMessage> mList;

    public ShareAdapter(Context context,List<ShareMessage> mList){
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

    public class WithoutPicture{
        private ImageView head_image;
        private TextView name;
        private TextView time;
        private TextView content;
        private TextView like;
        private TextView comment;
    }

    public class WithPicture{
        private ImageView head_image;
        private ImageView share_image;
        private TextView name;
        private TextView time;
        private TextView content;
        private TextView like;
        private TextView comment;
    }

    @Override
    public int getItemViewType(int position) {
        ShareMessage msg=mList.get(position);
        int type=msg.getType();
        Log.e("TYPE+",""+type);
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ShareMessage msg=mList.get(i);
        int type=getItemViewType(i);
        WithoutPicture holderWithoutPicture=null;
        WithPicture holderWithPicture=null;

        if(view==null) {
            switch (type) {
                case WITHOUT_PIC:
                    holderWithoutPicture = new WithoutPicture();
                    view=mInflater.inflate(R.layout.without_picture, null);
                    holderWithoutPicture.head_image = (ImageView) view.findViewById(R.id.head_portrait);
                    holderWithoutPicture.name = (TextView) view.findViewById(R.id.name);
                    holderWithoutPicture.time = (TextView) view.findViewById(R.id.time);
                    holderWithoutPicture.content = (TextView) view.findViewById(R.id.content1);
                    holderWithoutPicture.like = (TextView) view.findViewById(R.id.number_like);
                    holderWithoutPicture.comment = (TextView) view.findViewById(R.id.number_comment);
                    holderWithoutPicture.head_image.setImageResource(msg.getHead_image());
                    holderWithoutPicture.name.setText(msg.getName());
                    holderWithoutPicture.time.setText(msg.getTime());
                    holderWithoutPicture.content.setText(msg.getContent());
                    holderWithoutPicture.like.setText(""+msg.getLike());
                    holderWithoutPicture.comment.setText(""+msg.getComment());
                    break;

                case WITH_PIC:
                    holderWithPicture = new WithPicture();
                    view=mInflater.inflate(R.layout.with_picture, null);
                    holderWithPicture.head_image = (ImageView) view.findViewById(R.id.head_portrait_with);
                    holderWithPicture.share_image = (ImageView) view.findViewById(R.id.share_picture_with);
                    holderWithPicture.name = (TextView) view.findViewById(R.id.name_with);
                    holderWithPicture.time = (TextView) view.findViewById(R.id.time_with);
                    holderWithPicture.content = (TextView) view.findViewById(R.id.content1_with);
                    holderWithPicture.like = (TextView) view.findViewById(R.id.number_like_with);
                    holderWithPicture.comment = (TextView) view.findViewById(R.id.number_comment_with);
                    holderWithPicture.head_image.setImageResource(msg.getHead_image());
                    holderWithPicture.share_image.setImageResource(msg.getShare_image());
                    holderWithPicture.name.setText(msg.getName());
                    holderWithPicture.time.setText(msg.getTime());
                    holderWithPicture.content.setText(msg.getContent());
                    holderWithPicture.like.setText(""+msg.getLike());
                    holderWithPicture.comment.setText(""+msg.getComment());
                    break;

                default:
                    break;


            }
        }
        return view;
    }
}
