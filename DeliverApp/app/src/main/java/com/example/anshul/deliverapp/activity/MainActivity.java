package com.example.anshul.deliverapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.fragment.DeliveryFragment;
import com.example.anshul.deliverapp.fragment.PickUpFragment;
import com.example.anshul.deliverapp.fragment.SuccessFragment;
import com.example.anshul.deliverapp.onservables.FragmentObserver;
import com.example.anshul.deliverapp.services.GpsService;
import com.example.anshul.deliverapp.sharedprefs.PreferenceManager;
import com.example.anshul.deliverapp.utility.AppConstants;
import com.example.anshul.deliverapp.utility.Utility;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    private boolean mIsServiceStarted = false;
    GpsService mLocalService;
    private boolean mBound;
    ImageView iv_logout;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private final int REQUEST_CHECK_SETTINGS=111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iv_logout=(ImageView)findViewById(R.id.iv_logout);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mSectionsPagerAdapter.updateFragments();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view," "+ getLocation().getLongitude()+" "+getLocation().getLatitude(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=20.5666,45.345"));
                startActivity(intent);

            }
        });


        Utility.startTrackScheduler(this);


        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutAlertDialog();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Utility.getLocationOnOff(this);

        Intent intent=new Intent(this, GpsService.class);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);

//        getLocation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound){
        unbindService(mServiceConnection);
        mBound=false;
        }
    }

    public void updateFragments() {
        mSectionsPagerAdapter.updateFragments();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public class SectionsPagerAdapter extends FragmentPagerAdapter {



        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            AppController.getOnserver().deleteObservers(); // Clear existing observers.
            switch (position){
                case 0:
                    Fragment fragment=PickUpFragment.newInstance(position);
//                    AppController.getOnserver().addObserver((Observer) fragment);
                    return fragment;

                case 1:
                    Fragment fragment1=DeliveryFragment.newInstance(position);
//                    AppController.getOnserver().addObserver((Observer) fragment1);
                    return fragment1;//PickUpFragment.newInstance(position);

                case 2:
                    Fragment fragment2=SuccessFragment.newInstance(position);
//                    AppController.getOnserver().addObserver((Observer) fragment2);
                    return fragment2;//PickUpFragment.newInstance(position);
            }
//            return PlaceholderFragment.newInstance(position + 1);
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PickUp";
                case 1:
                    return "Delivery";
                case 2:
                    return "Success";
            }
            return null;
        }

        public void updateFragments() {
            AppController.getOnserver().notifyObservers();
        }
    }


    ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            GpsService.LocalBinder localService=(GpsService.LocalBinder) iBinder;
            mLocalService=localService.getService();
            mBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound=false;

        }
    };

    public Location getLocation(){

        Location location=mLocalService.getmCurrentLocation();

        return location;
//        gpsService.getmCurrentLocation();
//
//        Log.i("location get",gpsService.getmCurrentLocation()+"");
//        tv_gpsLocation.setText(location.getProvider()+"Lat= "+location.getLatitude()
//                +"Long=  "+location.getLongitude());


    }


    /**
     * Show logout alert dialog
     */
    private void showLogoutAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Are You Sure?");
        builder.setMessage("Do you wnat to logout?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete database
                SharedPreferences.Editor editor = getSharedPreferences(AppConstants.M_SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);

                finish();
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
