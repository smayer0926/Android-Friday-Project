package com.epicodus.athletetracker.ui;

import com.epicodus.athletetracker.adapter.WorkoutListAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.Services.WorkoutService;


import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;




import okhttp3.Response;

public class WorkoutDetailActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecycleView;
    private WorkoutListAdapter mListAdapter;
    public ArrayList<Workout> workouts = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        ButterKnife.bind(this);
        getWorkout();

//        workouts = Parcels.unwrap(getIntent().getParcelableExtra("workout"));
//        int startingPosition = getIntent().getIntExtra("position", 0);


    }

    private void getWorkout(){
        final WorkoutService workoutService = new WorkoutService();
        WorkoutService.findWorkout(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }
            @Override
            public void onResponse(Call call, Response response) {
                workouts = workoutService.processResults(response);

                WorkoutDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mListAdapter = new WorkoutListAdapter(getApplicationContext(), workouts);
                        mRecycleView.setAdapter(mListAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WorkoutDetailActivity.this);
                        mRecycleView.setLayoutManager(layoutManager);
                        mRecycleView.setHasFixedSize(true);

                    }
                });
            }
        });

    }
}
