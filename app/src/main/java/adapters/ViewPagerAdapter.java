package adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.deepak.mydevelopment.DemoFragment;
import com.deepak.mydevelopment.Main2Activity;

public class ViewPagerAdapter extends FragmentPagerAdapter{


    Main2Activity main2Activity;
    public ViewPagerAdapter(FragmentManager fm,Main2Activity main2Activity) {
        super(fm);
        this.main2Activity=main2Activity;
    }

    @Override
    public Fragment getItem(int position) {

        DemoFragment demoFragment=new DemoFragment();

        demoFragment.setTouchListner((DemoFragment.TouchListner) main2Activity);
        return demoFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Fragment "+(position+1);
    }
}
