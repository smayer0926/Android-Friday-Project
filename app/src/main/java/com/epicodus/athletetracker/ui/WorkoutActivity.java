package com.epicodus.athletetracker.ui;

import com.epicodus.athletetracker.adapter.WorkoutListAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.Services.WorkoutService;
import com.epicodus.athletetracker.adapter.WorkoutPagerAdapter;


import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;




import okhttp3.Response;

public class WorkoutActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecycleView;
    private WorkoutListAdapter mAdapter;

    public ArrayList<Workout> mWorkouts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        ButterKnife.bind(this);

        getWorkout();
    }

    private void getWorkout(){
        final WorkoutService workoutService = new WorkoutService();
        workoutService.findWorkout(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }
            @Override
            public void onResponse(Call call, Response response) {
                mWorkouts = workoutService.processResults(response);

                WorkoutActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new WorkoutListAdapter(getApplicationContext(), mWorkouts);
                        mRecycleView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WorkoutActivity.this);
                        mRecycleView.setLayoutManager(layoutManager);
                        mRecycleView.setHasFixedSize(true);

                    }
                });
            }
        });

    }
}
