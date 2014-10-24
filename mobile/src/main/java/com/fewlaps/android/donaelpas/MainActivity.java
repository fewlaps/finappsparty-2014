package com.fewlaps.android.donaelpas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class MainActivity extends Activity {
    LineChart chart;
    TextView todaySteps;

    Handler getStepsTask = null;

    private static final int DELAY_STEPS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todaySteps = (TextView) findViewById(R.id.todaySteps);
        chart = (LineChart) findViewById(R.id.chart);

        findViewById(R.id.donateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CampaignActivity.class));
            }
        });

        EventBus.getDefault().register(this);
    }

    public void updateScreen(StepEvent event) {
        todaySteps.setText(getString(R.string.steps, event.getTodaySteps()));
        if (todaySteps.getVisibility() == View.INVISIBLE) {
            todaySteps.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            todaySteps.setVisibility(View.VISIBLE);
        }

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        valsComp1.add(new Entry(event.lastWeekSteps.get(6), 0));
        valsComp1.add(new Entry(event.lastWeekSteps.get(5), 1));
        valsComp1.add(new Entry(event.lastWeekSteps.get(4), 2));
        valsComp1.add(new Entry(event.lastWeekSteps.get(3), 3));
        valsComp1.add(new Entry(event.lastWeekSteps.get(2), 4));
        valsComp1.add(new Entry(event.lastWeekSteps.get(1), 5));
        valsComp1.add(new Entry(event.lastWeekSteps.get(0), 6));
        LineDataSet setComp1 = new LineDataSet(valsComp1, null);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q");
        xVals.add("2.Q");
        xVals.add("3.Q");
        xVals.add("4.Q");
        xVals.add("4.Q");
        xVals.add("4.Q");
        xVals.add("4.Q");

        LineData data = new LineData(xVals, dataSets);

        chart.setData(data);
        chart.invalidate();

        if (chart.getVisibility() == View.INVISIBLE) {
            chart.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            chart.setVisibility(View.VISIBLE);
        }
    }

    public void onEventMainThread(StepEvent event) {
        updateScreen(event);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStepsTask = new Handler();
        getStepsTask.postDelayed(new Runnable() {
            public void run() {
                startService(new Intent(MainActivity.this, GetStepsIntentService.class));
                if (getStepsTask != null) {
                    getStepsTask.postDelayed(this, DELAY_STEPS);
                }
            }
        }, DELAY_STEPS);
    }

    @Override
    protected void onPause() {
        getStepsTask = null;
        super.onPause();
    }
}
