package com.example.machenike.hangzhou;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machenike.hangzhou.Fragment.CheckFragment;
import com.example.machenike.hangzhou.Fragment.OnDialogListener;
import com.example.machenike.hangzhou.Fragment.ShuoShuoFragment;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;


public class CheckActivity extends AppCompatActivity implements OnDateSelectedListener,OnDialogListener {

    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线
//  private MyMarkerView markerView;    //标记视图 即点击xy轴交点时弹出展示信息的View 需自定义
    public final static String[] xValues = new String[]{"语文", "数学", "英语", "音乐", "科学", "体育"};
    private PieChartView mpieChartView;
    private PieChartData mpieChartData;
    private boolean hasCenterText1 = false;             //是否有中心的文字
    private boolean hasCenterText2 = false;             //是否有中心的文字2
    private boolean isExploded = false;                 //是否是炸开的图像
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private LinearLayout mlinearLayout;
    private RelativeLayout first;
    private RelativeLayout second;
    private RelativeLayout third;
    private RelativeLayout fourth;
    private ImageView share;
    private IncomeBean data1,data2,data3,data4,data5;
    final List<IncomeBean> list = new ArrayList<>();
    private int punchDay;
    private TextView times;
    private MaterialCalendarView calendarView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        initpChart();
        initCalendar();

        lineChart = findViewById(R.id.lineChart);
        initLChart(lineChart);
        data1=new IncomeBean();
        data1.setValue(0.0);
        data1.setTradeDate("20180502");
        data2=new IncomeBean();
        data2.setValue(0.5);
        data2.setTradeDate("20180503");
        data3=new IncomeBean();
        data3.setValue(1.0);
        data3.setTradeDate("20180504");
        data4=new IncomeBean();
        data4.setValue(0.5);
        data4.setTradeDate("20180505");
        data5=new IncomeBean();
        data5.setValue(1.0);
        data5.setTradeDate("20180506");
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        showLineChart(list, "每日心情愉悦指数", Color.CYAN);

        mlinearLayout=findViewById(R.id.daka);
        mlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CheckFragment().show(getSupportFragmentManager(),"dialog_fragment");
            }
        });

        punchDay=20;
        times=(TextView)findViewById(R.id.times);
        times.setText(String.valueOf(punchDay));

        second=(RelativeLayout)findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(CheckActivity.this,HoleActivity.class);
                startActivity(turn);
            }
        });

        third=(RelativeLayout)findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(CheckActivity.this,QuestionActivity.class);
                startActivity(turn);
            }
        });

        fourth=(RelativeLayout)findViewById(R.id.fourth);
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(CheckActivity.this,ReservationActivity.class);
                startActivity(turn);
            }
        });

        first=(RelativeLayout)findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turn=new Intent(CheckActivity.this,MainActivity.class);
                startActivity(turn);
            }
        });

        share=findViewById(R.id.share_check);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent=new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"This is my text to send");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Share to..."));
            }
        });
    }

    @Override
    public void onDialogClick(String person) {
        refreshChart();
    }

    private void initCalendar(){
        calendarView=findViewById(R.id.calendar);
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        calendarView.setCurrentDate(calendar);
        calendarView.state()
                .edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(year,month-1,1))
                .setMaximumDate(CalendarDay.from(year,month-1,31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendarView.setWeekDayLabels(new String[]{"日","一","二","三","四","五","六"});
        calendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                StringBuffer buffer=new StringBuffer();
                int yearOne=day.getYear();
                int monthOne=day.getMonth()+1;
                buffer.append(yearOne).append("年").append(monthOne).append("月");
                return buffer;
            }
        });
        calendarView.setOnDateChangedListener(this);

    }

    private void initpChart(){
        mpieChartView=findViewById(R.id.pChart);
        mpieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                switch (i){
                    case 0:
                        Toast.makeText(CheckActivity.this, "开心占比"+sliceValue, Toast.LENGTH_SHORT).show();
                    case 1:
                        Toast.makeText(CheckActivity.this, "平淡占比"+sliceValue, Toast.LENGTH_SHORT).show();
                    case 2:
                        Toast.makeText(CheckActivity.this, "难过占比"+sliceValue, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onValueDeselected() {

            }
        });

        int numValues = 6;
        //初始化数据
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            values.add(sliceValue);
        }

        mpieChartData = new PieChartData(values);
        mpieChartData.setHasLabels(true);
        mpieChartData.setHasLabelsOnlyForSelected(false);
        mpieChartData.setHasLabelsOutside(true);
        mpieChartData.setHasCenterCircle(true);

        if (isExploded) {
            mpieChartData.setSlicesSpacing(24);
        }

        if (hasCenterText1) {
            mpieChartData.setCenterText1("心情");//设置中心文字1
        }

        if (hasCenterText2) {
            mpieChartData.setCenterText2("Charts (Roboto Italic)");//设置中心文字2
        }

        mpieChartView.setPieChartData(mpieChartData);


    }

    /**
     * 初始化图表
     */
    private void initLChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        lineChart.setBackgroundColor(Color.WHITE);
//是否显示边界
        lineChart.setDrawBorders(false);
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        Description description = new Description();
//        description.setText("需要展示的内容");
        description.setEnabled(false);
        lineChart.setDescription(description);




        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5,false);
        leftYAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return ((int) (value * 100)) + "%";
            }
        });
        leftYAxis.setLabelCount(5);



        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);
        xAxis.setDrawGridLines(false);
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(true);
        leftYAxis.enableGridDashedLine(10f, 10f, 0f);
        rightYaxis.setEnabled(false);


        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat df = new DecimalFormat(".00");
                return df.format(value * 100) + "%";
            }
        });

        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(final List<IncomeBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            IncomeBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, (float) data.getValue());
            entries.add(entry);
        }

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.get((int) value % dataList.size()).getTradeDate();
                return DateUtil.formatDate(tradeDate);
            }
        });

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }

    /**
     * 设置线条填充背景颜色
     *
     * @param drawable
     */
    public void setChartFillDrawable(Drawable drawable) {
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            //避免在 initLineDataSet()方法中 设置了 lineDataSet.setDrawFilled(false); 而无法实现效果
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            lineChart.invalidate();
        }
    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

    }



    public void refreshChart(){
        punchDay++;
        times.setText(String.valueOf(punchDay));
        lineChart = findViewById(R.id.lineChart);
        initLChart(lineChart);
        showLineChart(list, "每日心情愉悦指数", Color.CYAN);
        initpChart();
    }
}
