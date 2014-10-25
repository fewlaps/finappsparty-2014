package com.android.fewlaps.donaelpas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.WatchViewStub;
import android.view.View;

import com.fewlaps.android.donaelpas.R;

/**
 * Created by Esteve on 25/10/14.
 */
public class ThanksActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                View.OnClickListener sharedListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ThanksActivity.this, ConfirmationActivity.class);
                        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);
                        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, getString(R.string.fet));
                        startActivity(intent);
                    }
                };

                stub.findViewById(R.id.shareFacebook).setOnClickListener(sharedListener);
                stub.findViewById(R.id.shareTwitter).setOnClickListener(sharedListener);
            }
        });
    }
}
