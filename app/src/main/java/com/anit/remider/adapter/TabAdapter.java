package com.anit.remider.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.anit.remider.fragment.CurrentTaskFragment;
import com.anit.remider.fragment.DoneTaskFragment;

/**
 * Created by 79900 on 04.07.2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }



    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new CurrentTaskFragment();
            case 1:
                return new DoneTaskFragment();
            default:
                return null;
        }
    }



    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
