package com.epicodus.athletetracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.athletetracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @Bind(R.id.sign_up_Button) Button mSignUpButton;
    @Bind(R.id.Email)EditText mEmail;
    @Bind(R.id.password) EditText mPassword;
    @Bind(R.id.name) EditText mName;
    @Bind(R.id.age) EditText mAge;
    @Bind(R.id.SignUpLast) EditText mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        mSignUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = mEmail.getText().toString();
                String name = mName.getText().toString();
                if(email.equals("") || name.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please fill out all the fields!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent signUpIntent = new Intent(SignUpActivity.this, WelcomePage2.class);
                    signUpIntent.putExtra("name", name);
                    signUpIntent.putExtra("email", email);
                    startActivity(signUpIntent);
                }
            }
        });
    }
}
