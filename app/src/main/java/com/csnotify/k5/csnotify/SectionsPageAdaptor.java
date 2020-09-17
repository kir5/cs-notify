package com.csnotify.k5.csnotify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k5 on 5/18/17.
 */

class SectionsPageAdaptor extends FragmentPagerAdapter {

    private final List<Fragment> mfragmentList = new ArrayList<>(); // to keep track of fragments
    private final List<String> mfragmentTitleList = new ArrayList<>(); // to keep track of fragment titles


    public void addFragment(Fragment fragment, String title)
    {
        mfragmentList.add(fragment);
        mfragmentTitleList.add(title);
    }

    public SectionsPageAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
}
