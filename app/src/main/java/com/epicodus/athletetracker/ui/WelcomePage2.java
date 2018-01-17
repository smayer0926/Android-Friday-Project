package com.epicodus.athletetracker.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.epicodus.athletetracker.BottomNavigation;
import com.epicodus.athletetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import butterknife.BindView;
import butterknife.ButterKnife;




public class WelcomePage2 extends AppCompatActivity
        {
    public Context context;
    @BindView(R.id.toolbar)
    Toolbar mTools;
    @BindView(R.id.welcomePageHeader)
    TextView mWelcomePage;


    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    public void clearFunction() {
        mWelcomePage.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page2);

        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            View hView = navigationView.getHeaderView(0);
//            TextView nav_user = (TextView) hView.findViewgById(R.id.name_on_Nav);
//            TextView nav_email = (TextView) hView.findViewById(R.id.email_on_Nav);

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mWelcomePage.setText("Welcome, " + user.getDisplayName() + "!");
//                    nav_user.setText(user.getDisplayName());
//                    nav_email.setText(user.getEmail());
                } else {

                }
            }
        };
        Typeface welcomePageFont = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
        mWelcomePage.setTypeface(welcomePageFont);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(WelcomePage2.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.home:
                    Intent homeIntent = new Intent(WelcomePage2.this,WelcomePage2.class);
                    startActivity(homeIntent);
                    break;

                case R.id.workout:
                    Intent workoutIntent = new Intent(WelcomePage2.this, WorkoutActivity.class);
                    startActivity(workoutIntent);
                    break;

                case R.id.action_account:
                    Intent accountIntent = new Intent(WelcomePage2.this, WelcomePage2.class);
                    startActivity(accountIntent);
                    break;
            }
            return false;
        }
    };
}
