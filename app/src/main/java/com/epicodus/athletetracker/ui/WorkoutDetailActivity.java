package com.epicodus.athletetracker.ui;

import com.epicodus.athletetracker.adapter.WorkoutListAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.Services.WorkoutService;


import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;




import okhttp3.Response;

public class WorkoutDetailActivity extends AppCompatActivity {


    public ArrayList<Workout> workouts = new ArrayList<>();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private WorkoutListAdapter mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        ButterKnife.bind(this);
        getWorkout();

    }

    private void getWorkout(){
        final WorkoutService workoutService = new WorkoutService();
        WorkoutService.findWorkout(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                workouts = workoutService.processResults(response);

                WorkoutDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mListAdapter = new WorkoutListAdapter(getApplicationContext(), workouts);
                        mRecyclerView.setAdapter(mListAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WorkoutDetailActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });

    }
}
