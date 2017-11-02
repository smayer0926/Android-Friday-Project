package com.epicodus.athletetracker.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.athletetracker.Constants;
import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.ui.WorkoutDetailActivity;
import com.epicodus.athletetracker.ui.fragments.WorkoutDetailFragment;
import com.epicodus.athletetracker.util.ItemTouchHelperAdapter;
import com.epicodus.athletetracker.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseWorkoutListAdapter extends FirebaseRecyclerAdapter
        <Workout, FirebaseWorkoutViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Workout> mWorkouts = new ArrayList<>();
    private int mOrientation;



    public FirebaseWorkoutListAdapter(Class<Workout> modelClass, int modelLayout, Class<FirebaseWorkoutViewHolder> viewHolderClass,
                                      Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mWorkouts.add(dataSnapshot.getValue(Workout.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseWorkoutViewHolder viewHolder, Workout model
            , int position){
        viewHolder.bindWorkout(model);
        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;

        if(mOrientation == Configuration.ORIENTATION_LANDSCAPE){
            createDetailFragment(0);
        }

        viewHolder.mWorkoutTextView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, WorkoutDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_WORKOUT, Parcels.wrap(mWorkouts));
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private void createDetailFragment(int position){
        WorkoutDetailFragment detailFragment = WorkoutDetailFragment.newInstance(mWorkouts, position);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.workoutDetailContainer, detailFragment);
        ft.commit();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(mWorkouts, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }
    @Override
    public void onItemDismiss(int position){
        mWorkouts.remove(position);
        getRef(position).removeValue();

    }

    private void setIndexInFirebase(){
        for(Workout workout: mWorkouts){
            int index = mWorkouts.indexOf(workout);
            DatabaseReference ref = getRef(index);
            workout.setIndex(Integer.toString(index));
            ref.setValue(workout);
        }
    }

    @Override
    public void cleanup(){
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }


}
