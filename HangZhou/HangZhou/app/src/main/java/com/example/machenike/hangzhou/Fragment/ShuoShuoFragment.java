package com.example.machenike.hangzhou.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machenike.hangzhou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShuoShuoFragment extends DialogFragment {

    private LinearLayout back;
    private Button submmit;
    private ImageView choose;
    private TextWatcher textWatcher;
    private TextView shuoshuo;
    private String content;


    public ShuoShuoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View contentView=inflater.inflate(R.layout.share_yourself,null);
        back=contentView.findViewById(R.id.back);
        submmit=contentView.findViewById(R.id.submmit);
        choose=contentView.findViewById(R.id.choose);
        shuoshuo=contentView.findViewById(R.id.shuoshuo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             if(!charSequence.toString().equals("")){
                 submmit.setBackgroundColor(Color.YELLOW);
                 submmit.setTextColor(Color.BLACK);
                 submmit.setClickable(true);
             }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                content=shuoshuo.getText().toString();
                if(content.equals("")){
                    submmit.setBackgroundColor(Color.GRAY);
                    submmit.setTextColor(Color.parseColor("#C7C7C7"));
                    submmit.setClickable(false);
                }

            }
        };

        shuoshuo.addTextChangedListener(textWatcher);

        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"您的心情已成功分享~",Toast.LENGTH_LONG).show();
                dismiss();
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.share_yourself,new BottomSelectFragment());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });







        return contentView;
    }

}
