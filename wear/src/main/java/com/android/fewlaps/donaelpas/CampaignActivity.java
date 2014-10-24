package com.android.fewlaps.donaelpas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fewlaps.android.donaelpas.CampaignBean;
import com.fewlaps.android.donaelpas.FakeDatabase;
import com.fewlaps.android.donaelpas.R;

import java.util.List;

/**
 * Created by Esteve on 24/10/14.
 */
public class CampaignActivity extends Activity {


    private WearableListView mListView;
    private MyListAdapter mAdapter;
    private List<CampaignBean> campaignBeanList;

    private float mDefaultCircleRadius;
    private float mSelectedCircleRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        mAdapter = new MyListAdapter();
        campaignBeanList = FakeDatabase.getDatabase();

        mDefaultCircleRadius = getResources().getDimension(R.dimen.gapXLarge);
        mSelectedCircleRadius = getResources().getDimension(R.dimen.gapXLarge);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.wearableListView);
                mListView.setAdapter(mAdapter);
            }
        });
    }

    public class MyListAdapter extends WearableListView.Adapter {

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new WearableListView.ViewHolder(new SingleCampaignView(CampaignActivity.this));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {
            SingleCampaignView itemView = (SingleCampaignView) viewHolder.itemView;

            TextView title = (TextView) itemView.findViewById(R.id.title);
            TextView description = (TextView) itemView.findViewById(R.id.description);
            TextView button = (TextView) itemView.findViewById(R.id.button);

            title.setText(campaignBeanList.get(i).title);
            description.setText(campaignBeanList.get(i).description);

        }

        @Override
        public int getItemCount() {
            return campaignBeanList.size();
        }
    }

    private final class SingleCampaignView extends FrameLayout implements WearableListView.Item {


        private float mScale;

        public SingleCampaignView(Context context) {
            super(context);
            View.inflate(context, R.layout.item_campaign, this);

        }

        @Override
        public float getProximityMinValue() {
            return mDefaultCircleRadius;
        }

        @Override
        public float getProximityMaxValue() {
            return mSelectedCircleRadius;
        }

        @Override
        public float getCurrentProximityValue() {
            return mScale;
        }

        @Override
        public void setScalingAnimatorValue(float value) {
            mScale = value;
//            imgView.setCircleRadius(mScale);
//            imgView.setCircleRadiusPressed(mScale);
        }

        @Override
        public void onScaleUpStart() {
//            imgView.setAlpha(1f);
//            txtView.setAlpha(1f);
//            imgView.setCircleColor(mChosenCircleColor);
        }

        @Override
        public void onScaleDownStart() {
//            imgView.setAlpha(0.5f);
//            txtView.setAlpha(0.5f);
//            imgView.setCircleColor(mFadedCircleColor);
        }
    }

}

