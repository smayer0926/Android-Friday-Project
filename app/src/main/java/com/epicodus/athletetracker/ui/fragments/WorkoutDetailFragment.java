package com.epicodus.athletetracker.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.ui.WorkoutDetailActivity;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WorkoutDetailFragment extends Fragment  {
    @Bind(R.id.workoutHeader) TextView mWorkoutHeader;
    @Bind(R.id.descriptionBox) TextView mDecriptionBox;

    private Workout mworkout;

    public static WorkoutDetailFragment newInstance(Workout workout){
        WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("workout", Parcels.wrap(workout));
        workoutDetailFragment.setArguments(args);
        return workoutDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mworkout = Parcels.unwrap(getArguments().getParcelable("workout"));



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);

        ButterKnife.bind(this, view);
        String revise = mworkout.getmDescription();
        String revisedDescription = revise.replaceAll("[^a-zA-Z0-9\\s]", "");

        mDecriptionBox.setText(revisedDescription);

        return view;
    }


}
