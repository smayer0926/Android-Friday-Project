package com.epicodus.athletetracker;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.enter_button) Button mEnterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mEnterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent enterAppIntent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(enterAppIntent);
            }
        });


    }
}
