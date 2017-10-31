package com.epicodus.athletetracker.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.epicodus.athletetracker.Constants;
import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.adapter.FirebaseWorkoutListAdapter;
import com.epicodus.athletetracker.adapter.FirebaseWorkoutViewHolder;
import com.epicodus.athletetracker.util.OnStartDragListener;
import com.epicodus.athletetracker.util.SimpleItemTouchHelperCallback;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedWorkoutListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mWorkoutReference;
    private FirebaseWorkoutListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recycleViewer)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workout);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Log.v("Our user id", uid);

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_WORKOUTS_SAVED)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mWorkoutReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_WORKOUTS_SAVED)
                .child(uid);

        Log.v("here", mWorkoutReference + "");

        mFirebaseAdapter = new FirebaseWorkoutListAdapter(Workout.class,
                R.layout.workout_list_item_drag, FirebaseWorkoutViewHolder.class,
                mWorkoutReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}