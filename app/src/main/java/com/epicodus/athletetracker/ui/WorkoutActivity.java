package com.epicodus.athletetracker.ui;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.epicodus.athletetracker.R;

public class WorkoutActivity extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_workout);
       findViewById(R.id.loadingPanel).setVisibility(View.GONE);
   }
}
