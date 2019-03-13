package com.example.machenike.hangzhou;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;




public class QuestionActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer=new MediaPlayer();
    private LinearLayout back;
    private ImageView mImageView;
    private TextView mTextView;
    private TextView answer1Text;
    private TextView answer2Text;
    private TextView answer3Text;
    private int blueMy=0xFFEB8E55;
    private int answerIndex=0;
    private String[] answer1={"1.根据声音选取自己的感受","温暖的篝火","定时炸弹","在珠峰上许愿","着急下班的员工","请保证答题完整以获得准确测试报告"};
    private String[] answer2={"2.滑动卡片回答下一问题","危险的电花","香喷喷的烤肉","睡不着的夜晚","等待一个人","若未打完可滑动卡片继续答题"};
    private String[] answer3={"3.准备好开始答题","海上迎接风暴","迷失在荒芜沙漠","窃取机密的黑客","游戏中请勿打扰","提交"};
    private String[] question={"最美妙的音乐，可能来自生活百态，请凭直觉选出你对这些音乐的感知(请打开声音)","这段音乐给你什么样的感觉？","这段音乐给你什么样的感觉？",
            "这段音乐给你什么样的感觉？","这段音乐给你什么样的感觉？","是否确定提交获取测试报告？"};
    private String[] result={"关键词：感性","你笑起来像个孩子，把你带在身边有青春永驻的效果","只要对你倾注感情，你就会成为最可靠的伙伴","你听到喜欢的音乐，就能触发运气开挂的效果"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        mImageView = (ImageView)findViewById(R.id.user_imageview);
        mTextView = (TextView) findViewById(R.id.user_text);
        answer1Text=(TextView) findViewById(R.id.answer1_text);
        answer2Text=(TextView) findViewById(R.id.answer2_text);
        answer3Text=(TextView) findViewById(R.id.answer3_text);
        try {
            initCard() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        back = (LinearLayout)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionActivity.this.finish();
            }
        });
    }


    public void initCard() {
        mTextView.setTextSize(22);
        mTextView.setTextColor(Color.WHITE);
        mTextView.getBackground().setAlpha(190);
        answer1Text.setTextSize(15);
        answer1Text.setTextColor(Color.WHITE);
        answer1Text.setBackgroundColor(blueMy);
        answer2Text.setTextSize(15);
        answer2Text.setTextColor(Color.WHITE);
        answer2Text.setBackgroundColor(blueMy);
        answer3Text.setTextSize(15);
        answer3Text.setTextColor(Color.WHITE);
        answer3Text.setBackgroundColor(blueMy);
        if (answer1[answerIndex] != null&&answer2[answerIndex]!=null&&answer3[answerIndex]!=null&&question[answerIndex]!=null) {
            if(answerIndex>5)
                answerIndex=0;
            mTextView.setText(question[answerIndex]);
            System.out.println(answerIndex);
            answer1Text.setText(answer1[answerIndex]);
            answer2Text.setText(answer2[answerIndex]);
            answer3Text.setText(answer3[answerIndex]);
            System.out.println(answerIndex);
            switch (answerIndex){
                case 0:mImageView.setImageResource(R.drawable.question1);
                break;
                case 1:mImageView.setImageResource(R.drawable.question2);
                break;
                case 2:mImageView.setImageResource(R.drawable.question3);
                break;
                case 3:mImageView.setImageResource(R.drawable.question4);
                break;
                case 4:mImageView.setImageResource(R.drawable.question5);
                break;
                case 5:mImageView.setImageResource(R.drawable.question6);
                break;
            }
            System.out.println(answerIndex);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //the first card index must be 0
                    mediaPlayer=MediaPlayer.create(QuestionActivity.this,R.raw.fengling);
                    mediaPlayer.start();
                    System.out.println(answerIndex);
                    Toast.makeText(QuestionActivity.this,question[answerIndex],Toast
                            .LENGTH_SHORT).show();
                }
            });
            answer1Text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //the first card index must be 0
                    if((answerIndex)!=0&&(answerIndex)!=5) {
                        answer2[answerIndex] = " ";
                        answer3[answerIndex] = " ";
                        answer2Text.setText(answer2[answerIndex]);
                        answer3Text.setText(answer3[answerIndex]);
                        answerIndex++;
                        initCard();
                    }
                    else if(answerIndex==5){
                        mImageView.setImageResource(R.drawable.question3);
                        mTextView.getBackground().setAlpha(0);
                        mTextView.setText(result[0]);
                        mTextView.setTextSize(30);
                        mTextView.setTextColor(Color.BLACK);
                        answer1Text.getBackground().setAlpha(0);
                        answer1Text.setText(result[1]);
                        answer1Text.setTextSize(20);
                        answer1Text.setTextColor(Color.BLACK);
                        answer2Text.getBackground().setAlpha(0);
                        answer2Text.setText(result[2]);
                        answer2Text.setTextSize(20);
                        answer2Text.setTextColor(Color.BLACK);
                        answer3Text.getBackground().setAlpha(0);
                        answer3Text.setText(result[3]);
                        answer3Text.setTextSize(20);
                        answer3Text.setTextColor(Color.BLACK);
                    }
                }
            });
            answer2Text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //the first card index must be 0
                    if((answerIndex)!=0&&(answerIndex)!=5) {
                        answer1[answerIndex] = " ";
                        answer3[answerIndex] = " ";
                        answer1Text.setText(answer1[answerIndex]);
                        answer3Text.setText(answer3[answerIndex]);
                        answerIndex++;
                        initCard();
                    }
                    else if(answerIndex==5) {
                        mImageView.setImageResource(R.drawable.question6);
                        mTextView.getBackground().setAlpha(0);
                        mTextView.setText(result[0]);
                        mTextView.setTextSize(30);
                        mTextView.setTextColor(Color.BLACK);
                        answer1Text.getBackground().setAlpha(0);
                        answer1Text.setText(result[1]);
                        answer1Text.setTextSize(20);
                        answer1Text.setTextColor(Color.BLACK);
                        answer2Text.getBackground().setAlpha(0);
                        answer2Text.setText(result[2]);
                        answer2Text.setTextSize(20);
                        answer2Text.setTextColor(Color.BLACK);
                        answer3Text.getBackground().setAlpha(0);
                        answer3Text.setText(result[3]);
                        answer3Text.setTextSize(20);
                        answer3Text.setTextColor(Color.BLACK);
                    }
                }
            });
            answer3Text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //the first card index must be 0
                    if(answerIndex!=5) {
                        answer1[answerIndex] = " ";
                        answer2[answerIndex] = " ";
                        answer1Text.setText(answer1[answerIndex]);
                        answer2Text.setText(answer2[answerIndex]);
                        answerIndex++;
                        initCard();
                    }
                    else{
                        mImageView.setImageResource(R.drawable.login);
                        mTextView.getBackground().setAlpha(0);
                        mTextView.setText(result[0]);
                        mTextView.setTextSize(30);
                        mTextView.setTextColor(Color.BLACK);
                        answer1Text.getBackground().setAlpha(0);
                        answer1Text.setText(result[1]);
                        answer1Text.setTextSize(20);
                        answer1Text.setTextColor(Color.BLACK);
                        answer2Text.getBackground().setAlpha(0);
                        answer2Text.setText(result[2]);
                        answer2Text.setTextSize(20);
                        answer2Text.setTextColor(Color.BLACK);
                        answer3Text.getBackground().setAlpha(0);
                        answer3Text.setText(result[3]);
                        answer3Text.setTextSize(20);
                        answer3Text.setTextColor(Color.BLACK);
                    }
                }
            });
        }
    }
}