package com.epicodus.athletetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @Bind(R.id.sign_up_Button) Button mSignUpButton;
    @Bind(R.id.email)EditText mEmail;
    @Bind(R.id.passWord) EditText mPassword;
    @Bind(R.id.name) EditText mName;
    @Bind(R.id.age) EditText mAge;

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
                Intent signUpIntent = new Intent(SignUpActivity.this, WelcomePage2.class);
                startActivity(signUpIntent);
            }
        });
    }
}
