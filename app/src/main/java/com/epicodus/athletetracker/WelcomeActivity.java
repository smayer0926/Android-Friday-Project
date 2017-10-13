package com.epicodus.athletetracker;


import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
//    @Bind (R.id.left_drawer)ListView mDrawerList;
//    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.welcome_page) TextView mWelcomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

       ButterKnife.bind(this);
        Typeface welcomePageFont = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
        mWelcomePage.setTypeface(welcomePageFont);


    }
}
