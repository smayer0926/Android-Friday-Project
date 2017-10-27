package com.epicodus.athletetracker.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.athletetracker.Constants;
import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.adapter.FirebaseWorkoutViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedWorkoutListActivity extends AppCompatActivity {
    private DatabaseReference mWorkoutReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.recycleViewer) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        ButterKnife.bind(this);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//        System.out.println(uid);

        mWorkoutReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_WORKOUTS_SAVED);
//                .child(uid);
        setUpFirebaseAdapter();

    }
    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Workout, FirebaseWorkoutViewHolder>
                (Workout.class, R.layout.workout_list_item, FirebaseWorkoutViewHolder.class,
                        mWorkoutReference) {
            @Override
            protected void populateViewHolder(FirebaseWorkoutViewHolder viewHolder, Workout model, int position){
                viewHolder.bindWorkout(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
