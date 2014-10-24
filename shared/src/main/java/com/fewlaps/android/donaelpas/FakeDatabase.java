package com.fewlaps.android.donaelpas;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    public static List<CampaignBean> getDatabase() {
        List<CampaignBean> campaigns = new ArrayList<CampaignBean>();

        campaigns.add(new CampaignBean(1l, "Fewlaps", "The Fewlaps' campaign"));
        campaigns.add(new CampaignBean(2l, "Lorem ipsum", "Some random text"));
        campaigns.add(new CampaignBean(3l, "The answer", "42 times 42"));

        return campaigns;
    }
}
