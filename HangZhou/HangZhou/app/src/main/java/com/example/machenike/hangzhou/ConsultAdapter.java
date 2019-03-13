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

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsultAdapter extends BaseAdapter {
    private static final int TEMP=0;
    private LayoutInflater mInflater;

    private List<ConsultMessage> mList;

    public ConsultAdapter(Context context,List<ConsultMessage> mList){
        this.mList=mList;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        ConsultMessage msg=mList.get(position);
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
        private CircleImageView doctor_image;
        private TextView name;
        private TextView tel;
        private TextView time;
        private TextView place;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ConsultMessage msg=mList.get(i);
        int type=getItemViewType(i);
        Temp temp=null;

        if(view==null){
            switch (type){
                case TEMP:
                    temp=new Temp();
                    view=mInflater.inflate(R.layout.consult_info,null);
                    temp.doctor_image=view.findViewById(R.id.doctor);
                    temp.name=view.findViewById(R.id.name_content);
                    temp.tel=view.findViewById(R.id.contact_content);
                    temp.time=view.findViewById(R.id.time);
                    temp.place=view.findViewById(R.id.place);
                    temp.doctor_image.setImageResource(msg.getDoctor_image());
                    temp.name.setText(msg.getName());
                    temp.tel.setText(msg.getTel());
                    temp.time.setText(msg.getTime());
                    temp.place.setText(msg.getPlace());
                    break;
                    default:
                        break;
            }
        }
        return view;
    }
}
