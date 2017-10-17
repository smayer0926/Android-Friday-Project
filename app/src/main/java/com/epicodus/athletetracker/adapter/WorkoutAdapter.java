package com.epicodus.athletetracker.adapter;


import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;


public class WorkoutAdapter extends ArrayAdapter {
    private Context mContext;
    private String [] mStories;
    private String [] mURL;


    public WorkoutAdapter(Context mContext, int resource, String [] mStories, String [] mURL){
        super(mContext, resource);
        this.mContext = mContext;
        this.mStories = mStories;
        this.mURL = mURL;
    }

    @Override
    public Object getItem(int position){
        String story = mStories[position];
        String url = mURL[position];
        return String.format(story, url);
    }
    @Override
    public int getCount(){

        return mStories.length;

    }
}
