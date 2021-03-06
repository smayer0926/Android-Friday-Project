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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.jsoup.Jsoup;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WorkoutDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.workoutHeader) TextView mWorkoutHeader;
    @BindView(R.id.descriptionBox) TextView mDescriptionBox;
    @BindView(R.id.workoutSaved) Button mSaveWorkoutButton;

    private Workout mWorkout;
    private int mPosition;
    private ArrayList<Workout> mWorkouts;


    public static WorkoutDetailFragment newInstance(ArrayList<Workout> workouts, Integer position){
        WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
        Bundle args = new Bundle();


        args.putParcelable(Constants.EXTRA_KEY_WORKOUT, Parcels.wrap(workouts));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        workoutDetailFragment.setArguments(args);
        return workoutDetailFragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mWorkouts = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_WORKOUT));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mWorkout = mWorkouts.get(mPosition);

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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference workoutRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_WORKOUTS_SAVED)
                    .child(uid);
            DatabaseReference pushRef = workoutRef.push();
            String pushId = pushRef.getKey();
            mWorkout.setPushId(pushId);
            pushRef.setValue(mWorkout);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public static String html2text(String html){
        return Jsoup.parse(html).text();
    }
}
