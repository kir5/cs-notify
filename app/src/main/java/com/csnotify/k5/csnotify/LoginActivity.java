package com.csnotify.k5.csnotify;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    private ViewPager loginActivityViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginActivityViewPager = (ViewPager) findViewById(R.id.LoginActivityTabsViewcontainerID);
        setupViewPager(loginActivityViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.LoginActivity_tabsID);
        tabLayout.setupWithViewPager(loginActivityViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        LoginActivityPageAdapter mloginAdapter = new LoginActivityPageAdapter(getSupportFragmentManager());
        mloginAdapter.addFragmentsandTitles(new LoginTab1Fragment(), "Login");
        mloginAdapter.addFragmentsandTitles(new LoginTab2Fragment(), "Help");
        viewPager.setAdapter(mloginAdapter);
    }
}
