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

public class FirebaseWorkoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    View mView;
    Context mContext;

    public FirebaseWorkoutViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext= itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindWorkout(Workout workout){
        TextView workoutTextView = (TextView) mView.findViewById(R.id.workoutComment);


        workoutTextView.setText(workout.getmName());
    }

    @Override
    public void onClick(View view){
        final ArrayList<Workout> workouts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_WORKOUTS_SAVED);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    workouts.add(snapshot.getValue(Workout.class));
                }
                int itemPosition = getLayoutPosition();

                Intent newIntent = new Intent (mContext, WorkoutDetailActivity.class);
                newIntent.putExtra("position", itemPosition + "");
                newIntent.putExtra("workout", Parcels.wrap(workouts));

                mContext.startActivity(newIntent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
