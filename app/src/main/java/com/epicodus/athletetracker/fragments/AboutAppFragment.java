package com.epicodus.athletetracker.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.athletetracker.R;


public class AboutAppFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final View rootView = inflater.inflate(R.layout.fragment_about_app, container, false);


        TextView mAboutAppText = (TextView) rootView.findViewById(R.id.about_app_text);
        TextView mAboutAppHeader = (TextView) rootView.findViewById(R.id.about_app_header);

        Typeface aboutNewFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Aller_Lt.ttf");
        mAboutAppText.setTypeface(aboutNewFont);

        Typeface aboutHeader = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Bd.ttf");
        mAboutAppHeader.setTypeface(aboutHeader);

        return rootView;
    }

}
