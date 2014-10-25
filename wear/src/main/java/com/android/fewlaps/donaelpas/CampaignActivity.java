package com.android.fewlaps.donaelpas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fewlaps.android.donaelpas.CampaignBean;
import com.fewlaps.android.donaelpas.FakeDatabase;
import com.fewlaps.android.donaelpas.R;

import java.util.List;

/**
 * Created by Esteve on 24/10/14.
 */
public class CampaignActivity extends FragmentActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                list = (ListView) stub.findViewById(R.id.list);
                list.setAdapter(new CampaignAdapter(CampaignActivity.this, FakeDatabase.getDatabase()));
            }
        });
    }

    class CampaignAdapter extends BaseAdapter {

        private List<CampaignBean> campaigns;
        private FragmentActivity activity;

        public CampaignAdapter(FragmentActivity activity, List<CampaignBean> campaigns) {
            this.campaigns = campaigns;
            this.activity = activity;
        }


        @Override
        public int getCount() {
            return campaigns.size();
        }


        @Override
        public Object getItem(int position) {
            return campaigns.get(position);
        }


        @Override
        public long getItemId(int position) {
            return campaigns.get(position).id;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);
                convertView = inflater.inflate(R.layout.item_campaign, null);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.description = (TextView) convertView.findViewById(R.id.description);
                holder.button = convertView.findViewById(R.id.button);
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.title.setText(campaigns.get(position).title);
            holder.description.setText(campaigns.get(position).description);
            int id = activity.getResources().getIdentifier("pick".concat("" + (position % 7 +1)), "drawable", activity.getPackageName());
            holder.image.setImageBitmap(BitmapFactory.decodeResource(getResources(), id));

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CampaignActivity.this, ThanksActivity.class));
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView title;
            TextView description;
            View button;
            ImageView image;
        }
    }

}

