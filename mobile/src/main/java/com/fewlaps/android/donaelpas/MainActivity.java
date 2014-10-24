package com.fewlaps.android.donaelpas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;


public class MainActivity extends Activity {
    LineChart chart;
    TextView todaySteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todaySteps = (TextView) findViewById(R.id.todaySteps);
        todaySteps.setText(getString(R.string.steps, 18294));

        chart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        valsComp1.add(new Entry(12047, 0));
        valsComp1.add(new Entry(9214, 1));
        valsComp1.add(new Entry(15242, 2));
        valsComp1.add(new Entry(18294, 3));

        LineDataSet setComp1 = new LineDataSet(valsComp1, null);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q");
        xVals.add("2.Q");
        xVals.add("3.Q");
        xVals.add("4.Q");

        LineData data = new LineData(xVals, dataSets);

        chart.setData(data);

        findViewById(R.id.donateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CampaignActivity.class));
            }
        });
    }
}
