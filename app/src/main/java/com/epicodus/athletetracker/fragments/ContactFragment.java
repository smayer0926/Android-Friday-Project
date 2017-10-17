package com.epicodus.athletetracker.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.athletetracker.R;


public class ContactFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_contact, container, false);


        TextView mContactUsHeader = (TextView) rootView.findViewById(R.id.contact_us_header);
        TextView mContactUsText = (TextView) rootView.findViewById(R.id.contact_us_text);
        FloatingActionButton contactUs = (FloatingActionButton) rootView.findViewById(R.id.fab);
        contactUs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               Toast.makeText(getActivity(), "I don't work yet, I'm in progress!", Toast.LENGTH_SHORT).show();
            }
        });

        Typeface contactNewFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Aller_Lt.ttf");
        mContactUsText.setTypeface(contactNewFont);

        Typeface contactHeader = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Bd.ttf");
        mContactUsHeader.setTypeface(contactHeader);


        return rootView;
    }

}
