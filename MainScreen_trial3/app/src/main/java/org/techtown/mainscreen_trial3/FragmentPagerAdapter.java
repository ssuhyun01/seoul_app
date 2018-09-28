package org.techtown.mainscreen_trial3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    public FragmentPagerAdapter(FragmentManager fm){ super(fm);}

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return HealthTime.newInstance();
            case 1:
                return Location.newInstance();
            case 2:
                return Schedule.newInstance();
            case 3:
                return RecommendList.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
