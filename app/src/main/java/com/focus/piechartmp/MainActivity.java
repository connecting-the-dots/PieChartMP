package com.focus.piechartmp;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
//import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.github.mikephil.charting.components.Legend.LegendPosition;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private RelativeLayout mainLayout;
    private PieChart mChart;
    private float[] yData = {5, 10, 15, 30 ,40};
    private String[] xData = {"Sony", "Apple", "ASUS", "HTC", "Chrome"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        mChart = new PieChart(this);
        mainLayout.addView(mChart);
        mainLayout.setBackgroundColor(Color.LTGRAY);

        mChart.setUsePercentValues(true);
        mChart.setDescription("Smartphones Market Share");
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

//        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//
//            @Override
//            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//                if (e == null)
//                    return;
//
//                Toast.makeText(MainActivity.this,
//                        xData[e.getXIndex()] + " = " + e.getVal() + "%",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });

        addData();

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }

    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for(int i = 0; i < yData.length; i++) {
            yVals1.add( new Entry(yData[i], i));
        }

        ArrayList<String> xVals = new ArrayList<String>();
        for(int i = 0; i < yData.length; i++) {
            xVals.add( xData[i] );
        }

        PieDataSet dataSet = new PieDataSet( yVals1, "Market Share");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        mChart.highlightValues(null);
        mChart.invalidate();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
