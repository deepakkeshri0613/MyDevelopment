package com.deepak.mydevelopment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsk on 08-Apr-18.
 */

public class ActivityFetch {



    public List<String> getActivityList() {
        return activityList;
    }

    List<String> activityList=new ArrayList<>();

        public ActivityFetch(String[] activities)
        {
            for (int i=0;i<activities.length;++i)
                activityList.add(activities[i]);
        }
    }

