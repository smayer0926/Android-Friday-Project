package com.epicodus.athletetracker.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.adapter.WorkoutPagerAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkoutDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private WorkoutPagerAdapter adapterViewPager;
    ArrayList<Workout> mWorkouts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        ButterKnife.bind(this);


        mWorkouts = Parcels.unwrap(getIntent().getParcelableExtra("workout"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new WorkoutPagerAdapter(getSupportFragmentManager(), mWorkouts);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
