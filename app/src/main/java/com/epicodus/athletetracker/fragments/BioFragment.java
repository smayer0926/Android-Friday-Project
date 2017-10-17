package com.epicodus.athletetracker.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.athletetracker.R;


public class BioFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_bio, container, false);


        TextView mBioPageText = (TextView) rootView.findViewById(R.id.bio_page_text);
        TextView mBioPageHeader = (TextView) rootView.findViewById(R.id.bio_page_header);

        Typeface bioNewFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Aller_Lt.ttf");
        mBioPageText.setTypeface(bioNewFont);

        Typeface bioHeader = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Bd.ttf");
        mBioPageHeader.setTypeface(bioHeader);

        return rootView;
    }

}
