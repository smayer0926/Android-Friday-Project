package com.epicodus.athletetracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.enter_button) Button mEnterButton;
    @Bind(R.id.main_text) TextView mMainText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface newFont = Typeface.createFromAsset(getAssets(), "fonts/Aller_Bd.ttf");
        mMainText.setTypeface(newFont);


        mEnterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent enterAppIntent = new Intent(MainActivity.this, WelcomePage2.class);
                startActivity(enterAppIntent);
            }
        });


    }
}
