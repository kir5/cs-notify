package com.csnotify.k5.csnotify;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.csnotify.k5.csnotify.Adapters.NotificationAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActionBarDrawerToggle myToggle;
    String JSON_data;
    String date,message,tag;
    JSONObject JSON_object;
    JSONArray JSON_array;
    NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            JSON_data = extras.getString("json_data");
        }else {
            Toast.makeText(this,"Unable to get Extras",Toast.LENGTH_LONG).show();
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.navigation_actionID);
        setSupportActionBar(myToolbar);

        // Introduce and Prepare components to use them later

        DrawerLayout myDrawer = (DrawerLayout) findViewById(R.id.HomeDrawerLayoutID);
        myToggle = new ActionBarDrawerToggle(this, myDrawer, R.string.Open_name, R.string.Close_name);

        // Add event to myDrawer (it will be active while myToggle is touched)

        myDrawer.addDrawerListener(myToggle);
        myToggle.syncState();

        // Control Icon on ActionBar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SectionsPageAdaptor mSectionsPageAdaptor = new SectionsPageAdaptor(getSupportFragmentManager());

        // setup the viewPager with the sectionsAdaptor

        ViewPager mviewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mviewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

        NavigationView mnavigationView = (NavigationView) findViewById(R.id.homeNavigationViewID);
        mnavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(myToggle.onOptionsItemSelected(item))
        {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdaptor adaptor = new SectionsPageAdaptor(getSupportFragmentManager());
        adaptor.addFragment(new Tab1_fragment(), "Chatrooms");
        adaptor.addFragment(new Tab2_fragment(), "Notification Board");
        viewPager.setAdapter(adaptor);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.Nav_MyAccount:
                Intent showInfo = new Intent(getApplicationContext(), MyAccountActivity.class);
                startActivity(showInfo);
                return true;
            case R.id.Nav_Settings:
                Intent changePass = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(changePass);
                return true;
            case R.id.Nav_Share:
                //
                return true;
            case R.id.Nav_logOut:
                finish();
                return true;
        }

        return false;
    }
}

