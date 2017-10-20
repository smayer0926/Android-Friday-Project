package com.epicodus.athletetracker.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.ui.fragments.WorkoutDetailFragment;

import java.util.ArrayList;

public class WorkoutPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Workout> mWorkouts;

    public WorkoutPagerAdapter(FragmentManager fm, ArrayList<Workout> workouts){
        super(fm);
        mWorkouts = workouts;
    }
    @Override
    public Fragment getItem(int position){
        return WorkoutDetailFragment.newInstance(mWorkouts.get(position));
    }
    @Override
    public int getCount(){
        return mWorkouts.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mWorkouts.get(position).getmName();
    }


}
