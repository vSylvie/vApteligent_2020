package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.crittercism.app.Crittercism;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/**
 * Main application object.
 *
 * @author Sylvie Ngo
 */


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter pageAdapter;
    TabItem tabCrash;
    TabItem tabFlows;
    TabItem tabUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /**
     * Initialization of Crittercism
     */
        //WSO Intelligence App ID WSO test account
        //Crittercism.initialize(getApplicationContext(), "61b7fd650b2f4cf58fc72478b16f38f700555300");

        //Apteligent App ID Sylvie Account
        Crittercism.initialize(getApplicationContext(), "b6e22ca4b5304f3187d29cac3fc83b5e00555300");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("vApteligent Demo");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        tabCrash = findViewById(R.id.tabCrash);
        tabFlows = findViewById(R.id.tabFlows);
        tabUsage = findViewById(R.id.tabUsage);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    /**
     * @param menu concerns the icon on the toolbar. It explains what action it will do.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu:
                menu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void menu() {
        Intent MainIntent = new Intent(MainActivity.this, AdvancedActivity.class);
        startActivity(MainIntent);
        Toast.makeText(this, R.string.action_menu, Toast.LENGTH_SHORT).show();
    }

}

