package com.android.fewlaps.donaelpas;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.WatchViewStub;

import com.fewlaps.android.donaelpas.R;

public class MainActivity extends FragmentActivity {

    private static final int NUM_COLUMNS = 2;

    public ViewPager viewPager;


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
                    Fragment giveStepsFragment = new FragmentDonateSteps();
                    return giveStepsFragment;
                default:
                    return null;
            }

        }
        @Override
        public int getCount() {
            return NUM_COLUMNS;
        }

    }

}
