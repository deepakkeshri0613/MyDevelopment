package com.deepak.mydevelopment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.deepak.mydevelopment.intent.IntentFragment;

import adapters.NavigationDrawerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationDrawerAdapter.ClickListener{

    Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    ActivityFetch activityFetch;
    NavigationDrawerAdapter navigationDrawerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] activities=getResources().getStringArray(R.array.menu_name);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showFragment(MainFragment.createFragment());
        activityFetch=new ActivityFetch(activities);
        navigationDrawerAdapter=new NavigationDrawerAdapter(this,activityFetch.getActivityList());
        navigationDrawerAdapter.setOnClickListener(this);
        drawerFragment=(NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout),toolbar,navigationDrawerAdapter);
    }

    @Override
    public void onMenuItemClick(int position) {
        drawerFragment.getmDrawerLayout().closeDrawer(Gravity.START);

        switch (position)
        {
            case 0:showFragment(IntentFragment.createFragment());
                   break;
        }
    }


    void showFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
    }
    {

    }
}
