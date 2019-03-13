package com.example.machenike.hangzhou.Fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machenike.hangzhou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {
    private Button cancel;
    private Button submmit;


    public MyDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View contentView=inflater.inflate(R.layout.common_dialog,null);
        cancel=contentView.findViewById(R.id.cancel);
        submmit=contentView.findViewById(R.id.submmit);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText comment=contentView.findViewById(R.id.comment_to);
                String temp=comment.getText().toString();
                if(temp.equals("")){
                    Toast.makeText(getContext(),"请填写意见反馈~",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),"您的意见已提交~",Toast.LENGTH_LONG).show();
                    dismiss();
                }

            }
        });

        return contentView;
    }

}
