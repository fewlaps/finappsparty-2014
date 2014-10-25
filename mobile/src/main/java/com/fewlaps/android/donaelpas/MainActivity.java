package com.fewlaps.android.donaelpas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.common.images.ImageManager;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class MainActivity extends Activity {
    BarChart chart;
    TextView totalSteps;
    TextView steps;

    Handler getStepsTask = null;

    private static final int DELAY_STEPS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isFirstRun()) {
            hideViews(false);
        }

        findViewById(R.id.shadow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViews(true);
            }
        });
        totalSteps = (TextView) findViewById(R.id.totalSteps);
        steps = (TextView) findViewById(R.id.steps);
        chart = (BarChart) findViewById(R.id.chart);

        findViewById(R.id.donateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CampaignActivity.class));
            }
        });

        EventBus.getDefault().register(this);
    }

    public void updateScreen(StepEvent event) {
        long acumulatedSteps = 0;
        for (int i = 0; i < event.lastWeekSteps.size(); i++) {
            acumulatedSteps = acumulatedSteps + event.lastWeekSteps.get(i);
        }

        totalSteps.setText(String.valueOf(acumulatedSteps));

        if (totalSteps.getVisibility() == View.INVISIBLE) {
            totalSteps.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            totalSteps.setVisibility(View.VISIBLE);
        }

        if (steps.getVisibility() == View.INVISIBLE) {
            steps.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            steps.setVisibility(View.VISIBLE);
        }

        ArrayList<BarEntry> valsComp1 = new ArrayList<BarEntry>();
        valsComp1.add(new BarEntry(event.lastWeekSteps.get(6), 0));
        valsComp1.add(new BarEntry(event.lastWeekSteps.get(5), 1));
        valsComp1.add(new BarEntry(event.lastWeekSteps.get(4), 2));
        valsComp1.add(new BarEntry(event.lastWeekSteps.get(3), 3));
        valsComp1.add(new BarEntry(event.lastWeekSteps.get(2), 4));
        valsComp1.add(new BarEntry(event.lastWeekSteps.get(1), 5));
        valsComp1.add(new BarEntry(event.lastWeekSteps.get(0), 6));

        BarDataSet setComp1 = new BarDataSet(valsComp1, null);
        setComp1.setBarSpacePercent(35f);
        setComp1.setColor(getResources().getColor(R.color.chart_bar));
        setComp1.setBarShadowColor(getResources().getColor(android.R.color.transparent));

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q");
        xVals.add("2.Q");
        xVals.add("3.Q");
        xVals.add("4.Q");
        xVals.add("4.Q");
        xVals.add("4.Q");
        xVals.add("4.Q");


        BarData data = new BarData(xVals, dataSets);

        chart.setData(data);
        chart.setDrawYValues(true);
        chart.setDrawValueAboveBar(true);
        chart.setDrawGridBackground(false);
        chart.setDrawHorizontalGrid(false);
        chart.setDrawVerticalGrid(false);
        chart.setDrawYLabels(false);
        chart.setDrawXLabels(false);
        chart.setDescription("");
        chart.setDrawLegend(false);
        chart.setValueTextColor(getResources().getColor(R.color.chart_gray));
        chart.invalidate();

        if (chart.getVisibility() == View.INVISIBLE) {
            chart.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            chart.setVisibility(View.VISIBLE);
            chart.animateY(300);
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

    private void hideViews(boolean animated) {

        RelativeLayout butFake = (RelativeLayout) findViewById(R.id.fakeDonateButton);
        ImageView arrow = (ImageView) findViewById(R.id.arrow);
        View shadow = (View) findViewById(R.id.shadow);
        TextView textInstructions1 = (TextView) findViewById(R.id.textInstructions1);
        TextView textInstructions2 = (TextView) findViewById(R.id.textInstructions2);
        TextView fakeText = (TextView) findViewById(R.id.fakeText);

        if (animated && butFake.getVisibility() == View.VISIBLE) {
            Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out);
            butFake.startAnimation(fadeOut);
            arrow.startAnimation(fadeOut);
            shadow.startAnimation(fadeOut);
            textInstructions1.startAnimation(fadeOut);
            textInstructions2.startAnimation(fadeOut);
            fakeText.startAnimation(fadeOut);
        }

        butFake.setVisibility(View.INVISIBLE);
        arrow.setVisibility(View.INVISIBLE);
        shadow.setVisibility(View.INVISIBLE);
        textInstructions1.setVisibility(View.INVISIBLE);
        textInstructions2.setVisibility(View.INVISIBLE);
        fakeText.setVisibility(View.INVISIBLE);
    }

    public static final String KEY_PREFS_FIRST_RUN = "first_run";

    private boolean isFirstRun() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(KEY_PREFS_FIRST_RUN, true)) {
            prefs.edit().putBoolean(KEY_PREFS_FIRST_RUN, false).apply();
            return true;
        }
        return false;
    }

}
