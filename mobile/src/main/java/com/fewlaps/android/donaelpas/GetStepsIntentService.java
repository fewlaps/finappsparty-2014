package com.fewlaps.android.donaelpas;

import android.app.IntentService;
import android.content.Intent;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.greenrobot.event.EventBus;

public class GetStepsIntentService extends IntentService {

    public GetStepsIntentService() {
        super("GetStepsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            // To fake a little delay in the call to the Fitness API
            // ...that today is not accessible throug Developer Console! :(
            Thread.sleep(500);
            GregorianCalendar gc = new GregorianCalendar();
//            gc.set(Calendar.HOUR, 0);
            gc.set(Calendar.MINUTE, 0);
            gc.set(Calendar.SECOND, 0);
            EventBus.getDefault().post(new StepEvent((System.currentTimeMillis() - gc.getTimeInMillis()) / 1000));
        } catch (Exception e) {

        }
    }
}