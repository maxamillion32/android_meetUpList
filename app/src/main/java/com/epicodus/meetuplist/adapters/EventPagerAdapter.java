package com.epicodus.meetuplist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.meetuplist.Meetup;
import com.epicodus.meetuplist.ui.EventDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 7/9/16.
 */
public class EventPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Meetup> mEvents;

    public EventPagerAdapter(FragmentManager fm, ArrayList<Meetup> events) {
        super(fm);
        mEvents = events;
    }

    @Override
    public Fragment getItem(int position) {
        return EventDetailFragment.newInstance(mEvents.get(position));
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mEvents.get(position).getName();
    }
}