package com.android.fewlaps.donaelpas;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fewlaps.android.donaelpas.R;
import com.github.mikephil.charting.charts.LineChart;

/**
 * Created by Esteve on 24/10/14.
 */
public class DonateStepsFragment extends Fragment{
    LineChart chart;
    TextView todaySteps;

    public DonateStepsFragment() {

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

        View view = inflater.inflate(R.layout.fragment_donate_steps, container, false);
        final ViewPager vp = ((MainActivity) getActivity()).viewPager;

        view.findViewById(R.id.donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CampaignActivity.class));
            }
        });
        return view;

    }
}
