package com.fewlaps.android.donaelpas;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    public static List getDatabase(){
        List campaigns = new ArrayList();

        campaigns.add(new CampaignBean("Fewlaps","The Fewlaps' campaign"));
        campaigns.add(new CampaignBean("Lorem ipsum","Some random text"));
        campaigns.add(new CampaignBean("The answer","42 times 42"));

        return campaigns;
    }
}
