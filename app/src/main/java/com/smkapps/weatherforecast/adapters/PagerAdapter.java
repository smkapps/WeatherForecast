package com.smkapps.weatherforecast.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.smkapps.weatherforecast.fragments.CitiesFragment;
import com.smkapps.weatherforecast.fragments.SavedCitiesFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Context mContext;

    public PagerAdapter(FragmentManager fm, int numOfTabs, Context context) {
        super(fm);
        mNumOfTabs = numOfTabs;
        mContext = context;
    }

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CitiesFragment tab1 = new CitiesFragment();
                return tab1;
            case 1:
                SavedCitiesFragment tab2 = new SavedCitiesFragment();
//                SavedCitiesFragment tab2 = SavedCitiesFragment.getInstance(new DBHelper(mContext).getAllSavedCities());
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}