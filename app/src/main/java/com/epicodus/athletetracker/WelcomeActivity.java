package com.epicodus.athletetracker;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
//    @Bind (R.id.left_drawer) ListView mListView;
//    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        ButterKnife.bind(this);

//        mListView.setOnClickListener(new DrawerItemClickListener());
    }
}
