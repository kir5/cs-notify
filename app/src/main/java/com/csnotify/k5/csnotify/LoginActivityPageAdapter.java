package com.csnotify.k5.csnotify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k5 on 5/27/17.
 */
class LoginActivityPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> LoginActivityfragmentList = new ArrayList<>(); // to keep track of login page fragments
    private final List<String> LoginActivityfragmentTitleList = new ArrayList<>(); // to keep track of login page fragment titles

    public void addFragmentsandTitles(Fragment fragment, String title)
    {
        LoginActivityfragmentList.add(fragment);
        LoginActivityfragmentTitleList.add(title);
    }

    public LoginActivityPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return LoginActivityfragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return LoginActivityfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return LoginActivityfragmentList.size();
    }
}
