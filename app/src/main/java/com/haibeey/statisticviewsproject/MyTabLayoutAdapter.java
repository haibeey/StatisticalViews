package com.haibeey.statisticviewsproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

/**
 * Created by haibeey on 6/20/2018.
 */

public class MyTabLayoutAdapter extends FragmentPagerAdapter {
    public MyTabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PieChartView.newInstance();
            case 1:
                return HistogramView.newInstance();
            case 2:
                return GraphView.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "PieChart";
            case 1:
                return "Histogram";
            case 2:
                return "Graph";
        }
        return super.getPageTitle(position);
    }
}
