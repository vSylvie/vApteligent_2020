package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AdvancedActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    AdvancedPageAdapter pageAdapter;
    TabItem tabCrash;
    TabItem tabNetwork;
    TabItem tabFlows;
    TabItem tabUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced);


/**Initialize Crittercism
 */
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("vApteligent Demo Advanced");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager2);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        tabCrash = findViewById(R.id.tabCrash);
        tabNetwork = findViewById(R.id.tabNetwork);
        tabFlows = findViewById(R.id.tabFlows);
        tabUsage = findViewById(R.id.tabUsage);

        pageAdapter = new AdvancedPageAdapter(getSupportFragmentManager());
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
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.advanced_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_return:
                back();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void back() {
        Intent MainIntent = new Intent(AdvancedActivity.this, MainActivity.class);
        startActivity(MainIntent);
        Toast.makeText(this, R.string.action_return, Toast.LENGTH_SHORT).show();
    }
}