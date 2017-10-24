package com.epicodus.athletetracker.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.athletetracker.Constants;
import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.jsoup.Jsoup;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WorkoutDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.workoutHeader) TextView mWorkoutHeader;
    @Bind(R.id.descriptionBox) TextView mDescriptionBox;
    @Bind(R.id.workoutSaved) Button mSaveWorkoutButton;

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


        mSaveWorkoutButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if(v == mSaveWorkoutButton){
            DatabaseReference workoutRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_WORKOUTS_SAVED);
            workoutRef.push().setValue(mWorkout);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public static String html2text(String html){
        return Jsoup.parse(html).text();
    }
}
