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


public class TestAdapter extends BaseAdapter {

    private static final int TEMP=0;
    private LayoutInflater mInflater;

    private List<TestMessage> mList;

    public TestAdapter(Context context, List<TestMessage> mList){
        this.mList=mList;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        TestMessage msg=mList.get(position);
        int type=msg.getType();
        Log.e("TYPE+",""+type);
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
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

    private class Temp{
        private ImageView image;
        private TextView title;
        private TextView time;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TestMessage msg=mList.get(i);
        int type=getItemViewType(i);
        Temp temp=null;

        if(view==null){
            switch (type){
                case TEMP:
                    temp=new Temp();
                    view=mInflater.inflate(R.layout.test_info,null);
                    temp.image=view.findViewById(R.id.image);
                    temp.title=view.findViewById(R.id.title);
                    temp.time=view.findViewById(R.id.time);
                    temp.image.setImageResource(msg.getTest_image());
                    temp.time.setText(msg.getTime());
                    temp.title.setText(msg.getTitle());
                    break;
                default:
                    break;
            }
        }
        return view;
    }


}
