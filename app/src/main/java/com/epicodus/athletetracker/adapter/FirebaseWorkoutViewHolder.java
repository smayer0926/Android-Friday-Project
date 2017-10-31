package com.epicodus.athletetracker.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.athletetracker.Constants;
import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.ui.WorkoutDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;


import java.util.ArrayList;

public class FirebaseWorkoutViewHolder extends RecyclerView.ViewHolder   {
    View mView;
    Context mContext;
    public TextView mWorkoutTextView;

    public FirebaseWorkoutViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext= itemView.getContext();

    }

    public void bindWorkout(Workout workout){
        mWorkoutTextView = (TextView) mView.findViewById(R.id.workoutComment);
        mWorkoutTextView.setText(workout.getmName());


    }



}
