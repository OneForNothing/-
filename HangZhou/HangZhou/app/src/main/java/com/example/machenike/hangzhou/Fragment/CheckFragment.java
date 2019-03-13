package com.example.machenike.hangzhou.Fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machenike.hangzhou.CheckActivity;
import com.example.machenike.hangzhou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckFragment extends DialogFragment {



    private TextView cancel;
    private TextView confirm;
    public OnDialogListener mlistener;
    private RadioButton smile;
    private RadioButton normal;
    private RadioButton bad;
    public CheckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.check_dialog,null);
        cancel=contentView.findViewById(R.id.cancel_check);
        confirm=contentView.findViewById(R.id.confirm_check);
        smile=contentView.findViewById(R.id.smile);
        normal=contentView.findViewById(R.id.normal);
        bad=contentView.findViewById(R.id.sad);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"打卡成功，祝您心情愉快！",Toast.LENGTH_LONG).show();
                mlistener.onDialogClick("confirm");
                dismiss();
            }
        });

        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smile.setButtonDrawable(getResources().getDrawable(R.drawable.smile2));
                normal.setButtonDrawable(getResources().getDrawable(R.drawable.normal));
                bad.setButtonDrawable(getResources().getDrawable(R.drawable.sad));
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smile.setButtonDrawable(getResources().getDrawable(R.drawable.smile));
                normal.setButtonDrawable(getResources().getDrawable(R.drawable.normal2));
                bad.setButtonDrawable(getResources().getDrawable(R.drawable.sad));
            }
        });

        bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smile.setButtonDrawable(getResources().getDrawable(R.drawable.smile));
                normal.setButtonDrawable(getResources().getDrawable(R.drawable.normal));
                bad.setButtonDrawable(getResources().getDrawable(R.drawable.sad2));
            }
        });

        return contentView;
    }


    public void setOnDialogListener(OnDialogListener dialogListener){
        mlistener = dialogListener;
    }

}
