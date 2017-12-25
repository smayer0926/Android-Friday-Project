package com.epicodus.athletetracker.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.athletetracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.signup) Button mSignUp;
    @BindView(R.id.main_text) TextView mMainText;
    @BindView(R.id.login) Button mLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mSignUp.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        Typeface newFont = Typeface.createFromAsset(getAssets(), "fonts/Aller_Bd.ttf");
        mMainText.setTypeface(newFont);


    }

        @Override
        public void onClick(View v){
            if(v == mSignUp){
                Intent signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }else if(v == mLogin){
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }

            else {
                Toast errorToast = Toast.makeText(this, "There was an error, try again", Toast.LENGTH_SHORT);
                errorToast.show();
                Intent errorIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(errorIntent);
            }
        }

    }

