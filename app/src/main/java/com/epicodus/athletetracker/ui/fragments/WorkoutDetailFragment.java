package com.epicodus.athletetracker.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;


import org.jsoup.Jsoup;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WorkoutDetailFragment extends Fragment  {
    @Bind(R.id.workoutHeader) TextView mWorkoutHeader;
    @Bind(R.id.descriptionBox) TextView mDescriptionBox;

    private Workout mWorkout;



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
        mWorkout = Parcels.unwrap(getArguments().getParcelable("workout"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        ButterKnife.bind(this, view);

        mWorkoutHeader.setText(mWorkout.getmName());

        String changeWorkout = mWorkout.getmDescription();
        String revisedDescription = html2text(changeWorkout);

        mDescriptionBox.setText(revisedDescription);

        return view;
    }

    public static String html2text(String html){
        return Jsoup.parse(html).text();
    }
}
