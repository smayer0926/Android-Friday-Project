package com.epicodus.athletetracker.adapter;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.epicodus.athletetracker.Models.Workout;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.ui.WorkoutDetailActivity;
import com.epicodus.athletetracker.ui.fragments.WorkoutDetailFragment;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutViewHolder> {

    private ArrayList<Workout> mWorkouts = new ArrayList<>();
    private Context mContext;

    public WorkoutListAdapter(Context context, ArrayList<Workout> workouts){
        mContext = context;
        mWorkouts = workouts;
    }



    @Override
    public WorkoutListAdapter.WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_list_item, parent, false);
        WorkoutViewHolder viewHolder = new WorkoutViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(WorkoutListAdapter.WorkoutViewHolder holder, int position){
        holder.bindWorkout(mWorkouts.get(position));
    }

    @Override
    public int getItemCount(){
        return mWorkouts.size();
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.workoutComment)TextView mWorkOutComment;
        @Bind(R.id.workoutDate) TextView mWorkoutDate;

        private Context mContext;

        public WorkoutViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindWorkout(Workout workouts){
            String changeWorkout = workouts.getmName();
            String revisedName = changeWorkout.replaceAll("[^a-zA-Z0-9\\s]", "");

            mWorkOutComment.setText(revisedName);

        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, WorkoutDetailActivity.class);
            intent.putExtra("positon", itemPosition + "");
            intent.putExtra("workout", Parcels.wrap(mWorkouts));
            mContext.startActivity(intent);

        }
    }
}

