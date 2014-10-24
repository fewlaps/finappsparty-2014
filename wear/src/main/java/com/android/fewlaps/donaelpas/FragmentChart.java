package com.android.fewlaps.donaelpas;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.fewlaps.android.donaelpas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by Esteve on 24/10/14.
 */
public class FragmentChart extends Fragment {

    LineChart chart;
    TextView todaySteps;

    Handler getStepsTask = null;

    private static final int DELAY_STEPS = 500;

    public FragmentChart() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        EventBus.getDefault().register(this);

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        final ViewPager vp = ((MainActivity) getActivity()).viewPager;
        todaySteps = (TextView) view.findViewById(R.id.todaySteps);
        todaySteps.setText(getString(R.string.steps, 18294));

        chart = (LineChart) view.findViewById(R.id.chart);

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
        return view;

    }

    public void onEventMainThread(StepEvent event) {
        Log.i("EVENT", "Today steps: " + event.getTodaySteps());
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getStepsTask = new Handler();
        getStepsTask.postDelayed(new Runnable() {
            public void run() {
                getActivity().startService(new Intent(getActivity(), GetStepsIntentService.class));
                if (getStepsTask != null) {
                    getStepsTask.postDelayed(this, DELAY_STEPS);
                }
            }
        }, DELAY_STEPS);
    }

    @Override
    public void onPause() {
        getStepsTask = null;
        super.onPause();
    }
}
