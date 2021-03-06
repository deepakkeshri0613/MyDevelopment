package com.deepak.mydevelopment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.deepak.mydevelopment.AlertDialog.AlertDialogFragment;
import com.deepak.mydevelopment.broadcastreciver.BroadCastFragment;
import com.deepak.mydevelopment.intent.IntentFragment;
import com.deepak.mydevelopment.permission.PermissionFragment;
import com.deepak.mydevelopment.recyclerview.RecyclerViewFragment;
import com.deepak.mydevelopment.service.ServiceFragment;
import com.deepak.mydevelopment.style.styleFragment;
import com.deepak.mydevelopment.viewpager.ViewPagerFragment;

import adapters.NavigationDrawerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationDrawerAdapter.ClickListener{

    private int position=0,previousFragmentPosition=0;

    Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    ActivityFetch activityFetch;
    NavigationDrawerAdapter navigationDrawerAdapter;
    LinearLayout bottomSheet;
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


        bottomSheet=findViewById(R.id.bottom_sheet);

    }

    @Override
    public void onMenuItemClick(int position) {
        drawerFragment.getmDrawerLayout().closeDrawer(Gravity.START);

        switch (position)
        {
            case 0:if(this.position!=0)
                   {
                       bottomSheet.setVisibility(View.VISIBLE);
                    showFragment(MainFragment.createFragment());
                       this.position=position;
                       this.previousFragmentPosition=position;
                   }

                   break;
            case 1:if(position!=previousFragmentPosition)
                     {
                         bottomSheet.setVisibility(View.GONE);
                     showFragment(IntentFragment.createFragment());
                     this.position=position;
                         this.previousFragmentPosition=position;
                     }
                   break;
            case 2:if(position!=previousFragmentPosition) {
                bottomSheet.setVisibility(View.GONE);
                showFragment(new PermissionFragment());
                this.position = position;
                this.previousFragmentPosition=position;}
                break;

            case 3:if(position!=previousFragmentPosition) {
                bottomSheet.setVisibility(View.GONE);
                showFragment(new RecyclerViewFragment());
                this.position=position;
                this.previousFragmentPosition=position;
            }
                break;
            case 4:if(position!=previousFragmentPosition) {
                bottomSheet.setVisibility(View.GONE);
                showFragment(BroadCastFragment.createFragment());
                this.position=position;
                this.previousFragmentPosition=position;
            }
                break;
            case 5:if(position!=previousFragmentPosition)
            {
                bottomSheet.setVisibility(View.GONE);

                showFragment(ServiceFragment.createFragment());
                this.position=position;
                this.previousFragmentPosition=position;
            }
                break;
            case 6:if(position!=previousFragmentPosition)
            {

                bottomSheet.setVisibility(View.GONE);
                showFragment(new ViewPagerFragment());
                this.position=position;
                this.previousFragmentPosition=position;
            }
                break;
            case 7:if(position!=previousFragmentPosition)
            {

                bottomSheet.setVisibility(View.GONE);
                showFragment(new styleFragment());
                this.position=position;
                this.previousFragmentPosition=position;
            }
                break;
            case 8:if(position!=previousFragmentPosition)
            {
                bottomSheet.setVisibility(View.GONE);
                showFragment(new AlertDialogFragment());
                this.position=position;
                this.previousFragmentPosition=position;
            }
                break;
            case 9:if(position!=previousFragmentPosition)
            {
                bottomSheet.setVisibility(View.GONE);
                showFragment(new ProgressDialogFragment());
                this.position=position;
                this.previousFragmentPosition=position;
            }
                break;

        }
    }


    void showFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
    }


    @Override
    public void onBackPressed() {
        if(position!=0)
        {
            bottomSheet.setVisibility(View.VISIBLE);
            showFragment(MainFragment.createFragment());
            position=0;
            previousFragmentPosition=0;
        }
        else
        super.onBackPressed();
    }
}
