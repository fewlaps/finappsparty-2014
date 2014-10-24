package com.android.fewlaps.donaelpas;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;

import com.fewlaps.android.donaelpas.R;

import de.greenrobot.event.EventBus;

public class MainActivity extends FragmentActivity {

    private static final int NUM_COLUMNS = 2;

    public ViewPager viewPager;

    Handler getStepsTask = null;

    private static final int DELAY_STEPS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                viewPager = (ViewPager) stub.findViewById(R.id.viewPager);
                viewPager.setAdapter(new SectionsPagerAdapter(getFragmentManager()));
            }
        });

        EventBus.getDefault().register(this);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    Fragment chartFragment = new FragmentChart();
                    return chartFragment;
                case 1:
                    Fragment donateStepsFragment = new FragmentDonateSteps();
                    return donateStepsFragment;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return NUM_COLUMNS;
        }

    }


    public void onEventMainThread(StepEvent event) {
        Log.i("EVENT", "Today steps: " + event.getTodaySteps());
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
