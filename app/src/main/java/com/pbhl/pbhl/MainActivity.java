package com.pbhl.pbhl;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.Twitter;
import io.fabric.sdk.android.Fabric;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.List;

import io.fabric.sdk.android.services.concurrency.AsyncTask;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Gurbz on 2017-04-13.
 */

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener, ThirdFragment.OnFragmentInteractionListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_CONSUMER_KEY = "j1qW8lZhpTqxnfO7HSq9eUHKF";
    private static final String TWITTER_CONSUMER_SECRET = "Ah1AKbpxbdGy0O5aAjfTuxoTq8G8QoSJqNaYeWfopcA8xTZviK";

    private List<twitter4j.Status> statuses = null;
    TwitterAuthClient authClient;

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AsyncTask For Getting ID's
        new AsyncTask<Void, Void, List<Status>>(){
            @Override
            protected List<twitter4j.Status> doInBackground(Void... voids) {
//                List<twitter4j.Status> statuses = null;

                TwitterHandles handles = TwitterHandles.getInstance();

                ConfigurationBuilder builder=new ConfigurationBuilder();
                builder.setApplicationOnlyAuthEnabled(true);
                // setup
                twitter4j.Twitter twitter = new TwitterFactory(builder.build()).getInstance();
                // exercise & verify
                twitter.setOAuthConsumer(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);

                try {
                    twitter.getOAuth2Token();
                    statuses = twitter.getUserTimeline("PBHL_EDM");
//                    long[] temp = new long[statuses.size()];
//                    for(int i=0;i<statuses.size(); i++){
//                        temp[i] = statuses.get(i);
//                    }
                    handles.setPBHLOfficial(statuses);

                    statuses = twitter.getUserTimeline("PBHLBobMcKenzie");
//                    long[] temp1 = new long[statuses.size()];
//                    for(int i=0;i<statuses.size(); i++){
//                        temp1[i] = statuses.get(i);
//                    }
                    handles.setBobMackenzie(statuses);

                    statuses = twitter.getUserTimeline("FriedmanPBHL");
//                    long[] temp2 = new long[statuses.size()];
//                    for(int i=0;i<statuses.size(); i++){
//                        temp2[i] = statuses.get(i);
//                    }
                    handles.setElliotFriedman(statuses);



                } catch (TwitterException e) {
                    e.printStackTrace();
                }

                Log.v("HERE WE DID IT OMG..",Long.toString(statuses.get(0).getId()));
                Log.v("HERE WE DID IT OMG..",statuses.get(0).getText());
                Log.v("HERE WE DID IT OMG..",statuses.get(0).getUser().getName());
                Log.v("HERE WE DID IT OMG..",statuses.get(0).getHashtagEntities().getClass().getName());
//                Log.v("HERE WE DID IT OMG..",statuses.get(0).getMediaEntities());
                return statuses;
            }
        }.execute(null, null, null);


        //---------------------------------------------------------------
        // Replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Drawer Set Up
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the auth client.
        authClient.onActivityResult(requestCode, resultCode, data);
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}