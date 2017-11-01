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
   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_workout);
   }
}
