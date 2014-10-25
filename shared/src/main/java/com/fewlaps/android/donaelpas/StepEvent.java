package com.fewlaps.android.donaelpas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roc on 25/10/2014.
 */
public class StepEvent {
    public List<Long> lastWeekSteps;

    public StepEvent(long steps) {
        lastWeekSteps = new ArrayList(7);
        lastWeekSteps.add(steps); //today
        lastWeekSteps.add(8472l); //yesterday
        lastWeekSteps.add(9574l); //... guess it! :P
        lastWeekSteps.add(7378l);
        lastWeekSteps.add(8178l);
        lastWeekSteps.add(9261l);
        lastWeekSteps.add(10242l);
    }

    public long getTodaySteps() {
        return lastWeekSteps.get(0);
    }
}
