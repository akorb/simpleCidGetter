package com.andy.simplecidgetter;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainTabActivity extends AppCompatActivity {

    /**
     * The {@link ViewPager2} that will host the section contents.
     */
    private ViewPager2 mViewPager;

    private Cid cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0); // disable shadow for appbar

        cid = new Cid();

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager(), this.getLifecycle());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, mViewPager,
                (tab, position) -> tab.setText(getPageTitle(position))
        ).attach();
    }

    private static String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CID";
            case 1:
                return "All";
        }

        throw new IllegalArgumentException();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void share() {
        int curItem = mViewPager.getCurrentItem();

        if (cid.getState() == Cid.State.NoHtc && curItem == 0) {
            Toast.makeText(this.getApplicationContext(), "No info to share.", Toast.LENGTH_LONG).show();
            return;
        }

        String summary = cid.getText(curItem == 1);
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, summary);
        startActivity(sendIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Handle item selection
        switch (id) {
            case R.id.share:
                share();
                return true;
            case R.id.info:
                // Start info activity
                InfoActivity act = new InfoActivity();
                Intent intent = new Intent(this, act.getClass());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A {@link FragmentStateAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter extends FragmentStateAdapter {

        SectionsPagerAdapter(FragmentManager fragment, Lifecycle lifecycle) {
            super(fragment, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new CidFragment(cid);
            }
            if (position == 1) {
                return new AllFragment();
            }

            throw new IllegalArgumentException("Given value for \"position\" is not valid.)");
        }

        @Override
        public int getItemCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
