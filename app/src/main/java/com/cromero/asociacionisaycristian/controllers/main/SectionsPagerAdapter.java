package com.cromero.asociacionisaycristian.controllers.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cromero.asociacionisaycristian.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_stores, R.string.tab_users,R.string.tab_orders};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title=null;
        switch (position){
            case 0:
                title= mContext.getResources().getText(R.string.tab_stores);
                break;
            case 1:
                title=  mContext.getResources().getText(R.string.tab_users);
            break;
            case 2:
                title=  mContext.getResources().getText(R.string.tab_orders);
            break;
        }
        return title;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}